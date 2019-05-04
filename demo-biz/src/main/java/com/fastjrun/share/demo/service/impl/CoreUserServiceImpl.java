/*
 * Copyright (C) 2018 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.service.impl;

import com.fastjrun.common.ServiceException;
import com.fastjrun.dao.CommonDao;
import com.fastjrun.demo.share.common.CodeMsgConstants;
import com.fastjrun.helper.Check;
import com.fastjrun.helper.TimeHelper;
import com.fastjrun.mybatis.declare.Declare;
import com.fastjrun.service.BaseService;
import com.fastjrun.share.demo.dao.BaseUserDao;
import com.fastjrun.share.demo.dao.BaseUserLoginDao;
import com.fastjrun.share.demo.entity.User;
import com.fastjrun.share.demo.entity.UserLogin;
import com.fastjrun.share.demo.service.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("coreUserService")
public class CoreUserServiceImpl extends BaseService implements CoreUserService {
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    @Autowired
    private BaseUserDao                 baseUserDao;
    @Autowired
    private BaseUserLoginDao            baseUserLoginDao;
    @Autowired
    private CommonDao                   commonDao;

    @Value("${userService.invalidDays}")
    private int invalidDays = 30;

    @Value("${userService.errLimits}")
    private short errLimits = (short) 5;

    @Value("${userService.clearPwdLockErrIntervalInHours}")
    private int clearPwdLockErrIntervalInHours = 2;

    @Override
    public void checkLogin(String uuid, String deviceId) {
        String userKey = getLoginToken(uuid, deviceId);
        if (!redisTemplate.hasKey(userKey)) {
            throw new ServiceException(CodeMsgConstants.USER_NOT_LOGON,
              this.serviceMessageSource.getMessage(CodeMsgConstants.USER_NOT_LOGON, null, null));
        }
    }

    @Override
    public User login(String loginName, String loginPwd, String deviceId, String uuid) {
        String condition =
          " and `loginName`='".concat(loginName).concat("' and `loginPwd`='").concat(
            loginPwd).concat("' and `status`='1' and `loginErrCount`<5");
        List<User> users = baseUserDao.queryForListCondition(condition);
        if (!Check.isEmpty(users) && users.size() > 0) {
            User user = users.get(0);
            String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
            user.setLastLoginTime(curTime);
            user.setLastRecordLoginErrTime(null);
            user.setLoginErrCount(Integer.valueOf(0));
            baseUserDao.updateByPK(user);
            this.auditLogin(user.getId(), deviceId, uuid);
            this.setRedisUser(user, uuid, deviceId);
            return user;
        } else {
            condition = " and `loginName`='".concat(loginName).concat("'");
            List<User> userWithLoginNames = baseUserDao.queryForListCondition(condition);
            if (!Check.isEmpty(userWithLoginNames) && userWithLoginNames.size() > 0) {
                User user = userWithLoginNames.get(0);
                String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
                user.setLastRecordLoginErrTime(curTime);
                final String status = user.getStatus();
                if (!"1".equals(status)) {
                    log.warn(
                      loginName.concat(" login error for lock reason status: ").concat(status));
                    throw new ServiceException(CodeMsgConstants.USER_LOCKED,
                      this.serviceMessageSource.getMessage(CodeMsgConstants.USER_LOCKED,
                        new Object[] { status }, null));
                }
                int loginErrCount = user.getLoginErrCount().intValue();
                user.setLoginErrCount(Integer.valueOf(++loginErrCount));
                log.warn(
                  loginName.concat(" login error counts: ").concat(String.valueOf(loginErrCount)));
                if (loginErrCount >= this.errLimits) {
                    user.setStatus("2");
                    baseUserDao.updateByPK(user);
                    throw new ServiceException(CodeMsgConstants.PWDERR_INPUT_EXCEED_LIMIT,
                      this.serviceMessageSource.getMessage(
                        CodeMsgConstants.PWDERR_INPUT_EXCEED_LIMIT,
                        new Object[] { clearPwdLockErrIntervalInHours }, null));
                } else {
                    baseUserDao.updateByPK(user);
                    throw new ServiceException(CodeMsgConstants.PWDERR_INPUT_REMAIN,
                      this.serviceMessageSource.getMessage(CodeMsgConstants.PWDERR_INPUT_REMAIN,
                        new Object[] { this.errLimits - loginErrCount }, null));
                }

            } else {
                throw new ServiceException(CodeMsgConstants.PWDERR_WARNNING,
                  this.serviceMessageSource.getMessage(CodeMsgConstants.PWDERR_WARNNING, null,
                    null));
            }
        }
    }

    private void auditLogin(int userId, String deviceId, String uuid) {
        String createTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
        Date inValidateDate = TimeHelper.getOffsetDate(new Date(), Calendar.DATE, this.invalidDays);
        String inValidateTime = TimeHelper.getFormatDate(inValidateDate, TimeHelper.DF17);
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
        this.deleteRedisUser(uuid,deviceId);
        String condition =
          " and `loginCredential`='".concat(uuid).concat("' and `deviceId`='").concat(
            deviceId).concat("'");

        List<UserLogin> userLogins = baseUserLoginDao.queryForListCondition(condition);
        if (!Check.isEmpty(userLogins) && userLogins.size() > 0) {
            UserLogin userLogin = userLogins.get(0);
            String logOutTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
            userLogin.setLogOutTime(logOutTime);
            userLogin.setStatus("2");
            baseUserLoginDao.updateByPK(userLogin);
        }

    }

    @Override
    public User autoLogin(String deviceId, String uuidOld, String uuidNew) {
        User user = this.getRedisUser(uuidOld, deviceId);
        if (user != null) {
            this.logOut(uuidOld, deviceId);
            String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
            user.setLastLoginTime(curTime);
            user.setLastRecordLoginErrTime(null);
            user.setLoginErrCount(Integer.valueOf(0));
            baseUserDao.updateByPK(user);
            this.auditLogin(user.getId(), deviceId, uuidNew);
            this.setRedisUser(user, uuidNew, deviceId);
            return user;
        }
        log.warn(" login error for invalid uuid：".concat(uuidOld));
        throw new ServiceException(CodeMsgConstants.LOGIN_CREDENTIAL_INVALID,
          this.serviceMessageSource.getMessage(CodeMsgConstants.LOGIN_CREDENTIAL_INVALID, null,
            null));
    }

    @Override
    public void unlockUserPwd(Date date) {
        String sql = "update t_user set loginErrCount =0,status='1' where status='2'";
        int res = commonDao.update(new Declare(sql));
        log.debug("res={}", res);

    }

    @Override
    public void inValidUserLoginCredential(Date date) {
        Date invalidDate = TimeHelper.getOffsetDate(date, Calendar.DAY_OF_MONTH, this.invalidDays);
        String invalidDateStr = TimeHelper.getFormatDate(invalidDate, TimeHelper.DF17);
        String sql = "update t_user_login set status ='2' where inValidateTime <'".concat(
          invalidDateStr).concat("' and status='1'");
        int res = commonDao.update(new Declare(sql));
        log.debug("res={}", res);

    }

    private User getRedisUser(String uuid, String deviceId) {
        String key = getLoginToken(uuid, deviceId);
        if (redisTemplate.hasKey(key)) {
            Object value = redisTemplate.opsForValue().get(key);
            return (User) value;
        } else {
            return null;
        }
    }

    private void setRedisUser(User user, String uuid, String deviceId) {
        String key = getLoginToken(uuid, deviceId);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        redisTemplate.opsForValue().set(key, user);
        Boolean expire =
          redisTemplate.expire(key, 24 * this.invalidDays * 60 * 60, TimeUnit.SECONDS);
        log.debug("expire={}", expire);

    }

    private void deleteRedisUser(String uuid, String deviceId) {
        String key = getLoginToken(uuid, deviceId);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
    }

    private String getLoginToken(String uuid, String deviceId) {
        return "myLogin_".concat(deviceId).concat("_").concat(uuid);
    }

}
