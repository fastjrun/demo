package com.fastjrun.demo.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fastjrun.common.ServiceException;
import com.fastjrun.dao.CommonDao;
import com.fastjrun.demo.bean.User;
import com.fastjrun.demo.bean.UserLogin;
import com.fastjrun.demo.dao.BaseUserDao;
import com.fastjrun.demo.dao.BaseUserLoginDao;
import com.fastjrun.demo.service.CoreUserService;
import com.fastjrun.helper.Check;
import com.fastjrun.helper.TimeHelper;
import com.fastjrun.mybatis.declare.Declare;
import com.fastjrun.service.impl.BaseService;

@Service
public class CoreUserServiceImpl extends BaseService implements CoreUserService {
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    @Autowired
    private BaseUserDao baseUserDao;
    @Autowired
    private BaseUserLoginDao baseUserLoginDao;
    @Autowired
    private CommonDao commonDao;

    @Value("${userService.invalidDays}")
    private int invalidDays = 30;

    @Value("${userService.errLimits}")
    private int errLimits = 5;

    @Value("${userService.clearPwdLockErrIntervalInHours}")
    private int clearPwdLockErrIntervalInHours = 2;

    @Override
    public void checkLoign(String userKey) {
        if (!redisTemplate.hasKey(userKey)) {
            throw new ServiceException("checkLogin01", "用户未登录");
        }

    }

    @Override
    public User login(String loginName, String loginPwd, String deviceId,
            String uuid) {
        StringBuilder c = new StringBuilder();
        c.append("and loginName='").append(loginName).append("'");
        c.append(" and loginPwd='").append(loginPwd).append("'");
        c.append(" and status='1' and loginErrCount<5");
        List<User> users = baseUserDao.queryForListCondition(c.toString());
        if (!Check.isEmpty(users) && users.size() > 0) {
            User user = users.get(0);
            String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
            user.setLastLoginTime(curTime);
            user.setLastRecordLoginErrTime(null);
            user.setLoginErrCount(new Integer(0));
            baseUserDao.updateById(user);
            this.auditLogin(user.getId(), deviceId, uuid);
            this.setRedisUser(user, uuid, deviceId);
            return user;
        } else {
            c = new StringBuilder();
            c.append("and loginName='").append(loginName).append("'");
            List<User> userWithLoginNames = baseUserDao.queryForListCondition(c.toString());
            if (!Check.isEmpty(userWithLoginNames)
                    && userWithLoginNames.size() > 0) {
                User user = userWithLoginNames.get(0);
                String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
                user.setLastRecordLoginErrTime(curTime);
                final String status = user.getStatus();
                if (!"1".equals(status)) {
                    log.warn(loginName
                            + " login error for lock reason status: " + status);
                    throw new ServiceException("USERLOGIN02", "用户已被锁定" + status);
                }
                int loginErrCount = user.getLoginErrCount();
                user.setLoginErrCount(++loginErrCount);
                log.warn(loginName + " login error counts: " + loginErrCount);
                String errCode = "";
                String errMsg = "";
                if (loginErrCount >= this.errLimits) {
                    user.setStatus("2");
                    errCode = "USERLOGIN03";
                    errMsg = "用户名或密码错误输入错误已打上限，请隔"
                            + clearPwdLockErrIntervalInHours + "小时再试";
                } else {
                    errCode = "USERLOGIN04";
                    errMsg = "用户名或密码错误，还剩下" + (this.errLimits - loginErrCount)
                            + "次机会";
                }
                baseUserDao.updateById(user);
                throw new ServiceException(errCode, errMsg);
            } else {
                throw new ServiceException("USERLOGIN01", "用户名或密码错误");
            }
        }
    }

    private void auditLogin(int userId, String deviceId, String uuid) {
        String createTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
        Date inValidateDate = TimeHelper.getOffsetDate(new Date(),
                Calendar.DATE, this.invalidDays);
        String inValidateTime = TimeHelper.getFormatDate(inValidateDate,
                TimeHelper.DF17);
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(userId);
        userLogin.setCreateTime(createTime);
        userLogin.setInValidateTime(inValidateTime);
        userLogin.setLoginCredential(uuid);
        userLogin.setDeviceId(deviceId);
        userLogin.setStatus("1");
        baseUserLoginDao.insert(userLogin);

    }

    @Override
    public void logOut(String uuid, String deviceId) {
        String key = getLoginToken(uuid, deviceId);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        StringBuilder c = new StringBuilder();
        c.append("and loginCredential='").append(uuid).append("'");
        c.append(" and deviceId='").append(deviceId).append("'");
        List<UserLogin> userLogins = baseUserLoginDao.queryForListCondition(c.toString());
        if (!Check.isEmpty(userLogins) && userLogins.size() > 0) {
            UserLogin userLogin = userLogins.get(0);
            String logOutTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
            userLogin.setLogOutTime(logOutTime);
            userLogin.setStatus("2");
            baseUserLoginDao.updateById(userLogin);
        }

    }

    @Override
    public User getRedisUser(String uuid, String deviceId) {
        String key = getLoginToken(uuid, deviceId);
        if (redisTemplate.hasKey(key)) {
            Object value = redisTemplate.opsForValue().get(key);
            return (User) value;
        } else
            return null;
    }

    @Override
    public void setRedisUser(User user, String uuid, String deviceId) {
        String key = getLoginToken(uuid, deviceId);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        redisTemplate.opsForValue().set(key, user);
        Boolean expire = redisTemplate.expire(key,
                24 * this.invalidDays * 60 * 60, TimeUnit.SECONDS);
        log.debug(expire);

    }

    private String getLoginToken(String uuid, String deviceId) {
        return "myLogin_" + deviceId + "_" + uuid;
    }

    @Override
    public User autoLogin(String deviceId, String uuidOld, String uuidNew) {
        StringBuilder c = new StringBuilder();
        c.append("and loginCredential='").append(uuidOld).append("'");
        c.append("and status = '1'");
        c.append(" and deviceId='").append(deviceId).append("'");
        List<UserLogin> userLogins = baseUserLoginDao.queryForListCondition(c.toString());
        if (!Check.isEmpty(userLogins) && userLogins.size() > 0) {
            UserLogin userLogin = userLogins.get(0);
            User user = baseUserDao.selectById(userLogin.getUserId());
            String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
            user.setLastLoginTime(curTime);
            user.setLastRecordLoginErrTime(null);
            user.setLoginErrCount(new Integer(0));
            baseUserDao.updateById(user);
            this.logOut(uuidOld, deviceId);
            this.auditLogin(user.getId(), deviceId, uuidNew);
            this.setRedisUser(user, uuidNew, deviceId);
            return user;
        }
        log.warn(" login error for invalid uuid：" + uuidOld);
        throw new ServiceException("USERAUTOLOGIN01", "用户登录凭证已经失效，请重新登录");
    }

    @Override
    public void unlockUserPwd(Date date) {
        String sql = "update  t_user set loginErrCount =0,status='1' where status='2'";
        int res = commonDao.update(new Declare(sql));
        log.debug(res);

    }

    @Override
    public void inValidUserLoginCredential(Date date) {
        Date invalidDate = TimeHelper.getOffsetDate(date,
                Calendar.DAY_OF_MONTH, this.invalidDays);
        String invalidDateStr = TimeHelper.getFormatDate(invalidDate,
                TimeHelper.DF17);
        String sql = "update  t_user_login set status ='2' where inValidateTime>='"
                + invalidDateStr + "'";
        int res = commonDao.update(new Declare(sql));
        log.debug(res);

    }

}
