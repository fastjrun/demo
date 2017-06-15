package com.fastjrun.demo.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fastjrun.common.ServiceException;
import com.fastjrun.dao.CommonDao;
import com.fastjrun.demo.entity.User;
import com.fastjrun.demo.entity.UserExample;
import com.fastjrun.demo.entity.UserExample.Criteria;
import com.fastjrun.demo.entity.UserLogin;
import com.fastjrun.demo.entity.UserLoginExample;
import com.fastjrun.demo.mapper.UserLoginMapper;
import com.fastjrun.demo.mapper.UserMapper;
import com.fastjrun.demo.service.CoreUserService;
import com.fastjrun.helper.Check;
import com.fastjrun.helper.TimeHelper;
import com.fastjrun.mybatis.declare.Declare;
import com.fastjrun.service.impl.BaseService;

@Service
public class CoreUserServiceImpl extends BaseService implements CoreUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private CommonDao commonDao;

    @Value("${userService.invalidDays}")
    private int invalidDays = 30;

    @Value("${userService.errLimits}")
    private short errLimits = (short) 5;

    @Value("${userService.clearPwdLockErrIntervalInHours}")
    private int clearPwdLockErrIntervalInHours = 2;

    private final static String USER_NOT_LOGON = "0002";

    private final static String LOGIN_CREDENTIAL_INVALID = "0003";

    private final static String USER_LOCKED = "0004";

    private final static String PWDERR_INPUT_EXCEED_LIMIT = "0005";

    private final static String PWDERR_INPUT_REMAIN = "0006";

    private final static String PWDERR_WARNNING = "0007";

    @Override
    public void checkLoign(String uuid, String deviceId) {
        UserLoginExample example = new UserLoginExample();
        com.fastjrun.demo.entity.UserLoginExample.Criteria criteria = example
                .createCriteria();
        criteria.andLogincredentialEqualTo(uuid);
        criteria.andDeviceidEqualTo(deviceId);
        criteria.andStatusEqualTo("1");

        List<UserLogin> userLogins = userLoginMapper.selectByExample(example);
        if (!Check.isEmpty(userLogins) && userLogins.size() > 0) {
            throw new ServiceException(USER_NOT_LOGON,
                    this.serviceMessageSource.getMessage(USER_NOT_LOGON, null,
                            null));
        }
    }

    @Override
    public User login(String loginName, String loginPwd, String deviceId,
            String uuid) {
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(loginName);
        criteria.andLoginpwdEqualTo(loginPwd);
        criteria.andStatusEqualTo("1");
        criteria.andLoginerrcountLessThan(Short.valueOf((short) 5));
        List<User> users = userMapper.selectByExample(example);
        if (!Check.isEmpty(users) && users.size() > 0) {
            User user = users.get(0);
            String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
            user.setLastlogintime(curTime);
            user.setLastrecordloginerrtime(null);
            user.setLoginerrcount(Short.valueOf((short) 0));
            userMapper.updateByPrimaryKey(user);
            this.auditLogin(user.getId(), deviceId, uuid);
            return user;
        } else {
            example = new UserExample();
            criteria = example.createCriteria();
            criteria.andLoginnameEqualTo(loginName);
            List<User> userWithLoginNames = userMapper.selectByExample(example);
            if (!Check.isEmpty(userWithLoginNames)
                    && userWithLoginNames.size() > 0) {
                User user = userWithLoginNames.get(0);
                String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
                user.setLastrecordloginerrtime(curTime);
                final String status = user.getStatus();
                if (!"1".equals(status)) {
                    log.warn(loginName
                            + " login error for lock reason status: " + status);
                    throw new ServiceException(USER_LOCKED,
                            this.serviceMessageSource.getMessage(USER_LOCKED,
                                    new Object[] { status }, null));
                }
                short loginErrCount = user.getLoginerrcount().shortValue();
                user.setLoginerrcount(Short.valueOf((short) (++loginErrCount)));
                log.warn(loginName + " login error counts: " + loginErrCount);
                if (loginErrCount >= this.errLimits) {
                    user.setStatus("2");
                    userMapper.updateByPrimaryKey(user);
                    throw new ServiceException(
                            PWDERR_INPUT_EXCEED_LIMIT,
                            this.serviceMessageSource
                                    .getMessage(
                                            PWDERR_INPUT_EXCEED_LIMIT,
                                            new Object[] { clearPwdLockErrIntervalInHours },
                                            null));
                } else {
                    userMapper.updateByPrimaryKey(user);
                    throw new ServiceException(PWDERR_INPUT_REMAIN,
                            this.serviceMessageSource.getMessage(
                                    PWDERR_INPUT_REMAIN,
                                    new Object[] { this.errLimits
                                            - loginErrCount }, null));
                }

            } else {
                throw new ServiceException(PWDERR_WARNNING,
                        this.serviceMessageSource.getMessage(PWDERR_WARNNING,
                                null, null));
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
        userLogin.setUserid(userId);
        userLogin.setCreatetime(createTime);
        userLogin.setInvalidatetime(inValidateTime);
        userLogin.setLogincredential(uuid);
        userLogin.setDeviceid(deviceId);
        userLogin.setStatus("1");
        userLoginMapper.insert(userLogin);

    }

    @Override
    public void logOut(String uuid, String deviceId) {
        String key = getLoginToken(uuid, deviceId);

        UserLoginExample example = new UserLoginExample();
        com.fastjrun.demo.entity.UserLoginExample.Criteria criteria = example
                .createCriteria();
        criteria.andLogincredentialEqualTo(uuid);
        criteria.andDeviceidEqualTo(deviceId);

        List<UserLogin> userLogins = userLoginMapper.selectByExample(example);
        if (!Check.isEmpty(userLogins) && userLogins.size() > 0) {
            UserLogin userLogin = userLogins.get(0);
            String logOutTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
            userLogin.setLogouttime(logOutTime);
            userLogin.setStatus("2");
            userLoginMapper.updateByPrimaryKey(userLogin);
        }

    }

    private String getLoginToken(String uuid, String deviceId) {
        return "myLogin_" + deviceId + "_" + uuid;
    }

    @Override
    public User autoLogin(String deviceId, String uuidOld, String uuidNew) {

        UserLoginExample example = new UserLoginExample();
        com.fastjrun.demo.entity.UserLoginExample.Criteria criteria = example
                .createCriteria();
        criteria.andLogincredentialEqualTo(uuidOld);
        criteria.andStatusEqualTo("1");
        criteria.andDeviceidEqualTo(deviceId);

        List<UserLogin> userLogins = userLoginMapper.selectByExample(example);
        if (!Check.isEmpty(userLogins) && userLogins.size() > 0) {
            UserLogin userLogin = userLogins.get(0);
            User user = userMapper.selectByPrimaryKey(userLogin.getUserid());
            String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
            user.setLastlogintime(curTime);
            user.setLastrecordloginerrtime(null);
            user.setLoginerrcount(Short.valueOf((short) 0));
            userMapper.updateByPrimaryKey(user);
            this.logOut(uuidOld, deviceId);
            this.auditLogin(user.getId(), deviceId, uuidNew);
            return user;
        }
        log.warn(" login error for invalid uuid：" + uuidOld);
        throw new ServiceException(LOGIN_CREDENTIAL_INVALID,
                this.serviceMessageSource.getMessage(LOGIN_CREDENTIAL_INVALID,
                        null, null));
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
                + invalidDateStr + "' and status='1'";
        int res = commonDao.update(new Declare(sql));
        log.debug(res);

    }

}
