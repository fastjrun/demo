package com.fastjrun.demospring4.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fastjrun.common.CodeMsg;
import com.fastjrun.common.ServiceException;
import com.fastjrun.dao.CommonDao;
import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.bean.UserLogin;
import com.fastjrun.demospring4.dao.BaseUserDao;
import com.fastjrun.demospring4.dao.BaseUserLoginDao;
import com.fastjrun.demospring4.service.CoreUserService;
import com.fastjrun.helper.Check;
import com.fastjrun.helper.TimeHelper;
import com.fastjrun.mybatis.ConditionHelper;
import com.fastjrun.mybatis.declare.Condition;
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
            CodeMsg codeMsg = new CodeMsg() {
                @Override
                public String getCode() {
                    return "checkLogin01";
                }

                @Override
                public String getMsg() {
                    // TODO Auto-generated method stub
                    return "用户未登录";
                }

            };
            throw new ServiceException(codeMsg);
        }

    }

    @Override
    public User login(String loginName, String loginPwd, String deviceId,
            String uuid) {
        Condition c = new Condition();
        c.and("(loginName=? or mobileNo=?) and loginPwd=? and status='1' and loginErrCount<5",
                loginName, loginName, loginPwd);
        ConditionHelper.condition(c.toString());
        List<User> users = baseUserDao.queryForListCondition();
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
            c = new Condition();
            c.and("(loginName=? or mobileNo=?)", loginName, loginName);
            ConditionHelper.condition(c.toString());
            List<User> userWithLoginNames = baseUserDao.queryForListCondition();
            if (!Check.isEmpty(userWithLoginNames)
                    && userWithLoginNames.size() > 0) {
                User user = userWithLoginNames.get(0);
                String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
                user.setLastRecordLoginErrTime(curTime);
                final String status = user.getStatus();
                if (!"1".equals(status)) {
                    log.warn(loginName
                            + " login error for lock reason status: " + status);
                    CodeMsg codeMsg = new CodeMsg() {
                        @Override
                        public String getCode() {
                            return "USERLOGIN02";
                        }

                        @Override
                        public String getMsg() {
                            return "用户已被锁定" + status;
                        }

                    };
                    throw new ServiceException(codeMsg);
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
                final String errCode2 = errCode;
                final String errMsg2 = errMsg;
                CodeMsg codeMsg = new CodeMsg() {
                    @Override
                    public String getCode() {
                        return errCode2;
                    }

                    @Override
                    public String getMsg() {
                        return errMsg2;
                    }

                };
                throw new ServiceException(codeMsg);
            } else {
                CodeMsg codeMsg = new CodeMsg() {
                    @Override
                    public String getCode() {
                        return "USERLOGIN01";
                    }

                    @Override
                    public String getMsg() {
                        // TODO Auto-generated method stub
                        return "用户名或密码错误";
                    }
                };
                throw new ServiceException(codeMsg);
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
        Condition cLogin = new Condition();
        cLogin.and("loginCredential=? and deviceId=?", uuid, deviceId);
        ConditionHelper.condition(cLogin.toString());
        List<UserLogin> userLogins = baseUserLoginDao.queryForListCondition();
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
        Condition c = new Condition().and("loginCredential = ? and status = '1'",
                uuidOld).and("deviceId = ?", deviceId);
        ConditionHelper.condition(c.toString());
        List<UserLogin> userLogins = baseUserLoginDao.queryForListCondition();
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
        CodeMsg codeMsg = new CodeMsg() {
            @Override
            public String getCode() {
                return "USERAUTOLOGIN01";
            }

            @Override
            public String getMsg() {
                return "用户登录凭证已经失效，请重新登录";
            }

        };
        throw new ServiceException(codeMsg);
    }

    @Override
    public void unlockUserPwd(Date date) {
        String sql = "update  t_user set loginErrCount =0 and status='1' where status='2'";
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
