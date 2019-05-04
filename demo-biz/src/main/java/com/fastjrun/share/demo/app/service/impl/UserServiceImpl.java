/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.app.service.impl;

import com.fastjrun.common.ServiceException;
import com.fastjrun.demo.share.common.CodeMsgConstants;
import com.fastjrun.demo.share.utils.MD5Utils;
import com.fastjrun.helper.UUID;
import com.fastjrun.service.BaseService;
import com.fastjrun.share.demo.dao.BaseUserDao;
import com.fastjrun.share.demo.entity.User;
import com.fastjrun.share.demo.packet.app.AutoLoginRestRequestBody;
import com.fastjrun.share.demo.packet.app.LoginRestRequestBody;
import com.fastjrun.share.demo.packet.app.LoginRestResponseBody;
import com.fastjrun.share.demo.packet.app.RegistserRestRequestBody;
import com.fastjrun.share.demo.service.CoreUserService;
import com.fastjrun.share.demo.service.UserServiceRest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("userServiceRest")
public class UserServiceImpl extends BaseService implements UserServiceRest {
    @Autowired
    private BaseUserDao     baseUserDao;
    @Autowired
    private RabbitTemplate  rabbitTemplate;
    @Autowired
    private CoreUserService coreUserService;

    @Override
    public void register(RegistserRestRequestBody request) {
        String loginPwd = request.getLoginpwd();
        String loginName = request.getLoginId();
        String nickName = request.getNickName();
        Integer sex = Integer.valueOf(request.getSex());
        String mobileNo = request.getMobileNo();
        String email = request.getEmail();
        String condition =
          " and (`loginName`='" + loginName + "' or `mobileNo`='" + mobileNo + "')";
        List<User> users = baseUserDao.queryForListCondition(condition);
        if (!users.isEmpty()) {
            throw new ServiceException(CodeMsgConstants.USER_ALREADY_EXISTS,
              serviceMessageSource.getMessage(CodeMsgConstants.USER_ALREADY_EXISTS, null, null));
        } else {
            String md5pwd = "";
            try {
                md5pwd = MD5Utils.md5Encode(loginPwd, "UTF-8");
            } catch (Exception e) {
                throw new ServiceException(CodeMsgConstants.USER_REGISTER_FAIL,
                  serviceMessageSource.getMessage(CodeMsgConstants.USER_REGISTER_FAIL, null, null));
            }
            Timestamp curTimestamp = new Timestamp(System.currentTimeMillis());
            User user = new User();
            user.setCreateTime(curTimestamp);
            user.setEmail(email);
            user.setLastModifyTime(curTimestamp);
            user.setLoginName(loginName);
            user.setLoginPwd(md5pwd);
            user.setMobileNo(mobileNo);
            user.setNickName(nickName);
            user.setSex(sex);
            user.setStatus("1");
            user.setLastRecordLoginErrTime(null);
            user.setLoginErrCount(new Integer("0"));
            baseUserDao.insert(user);
            try {
                rabbitTemplate.convertAndSend(user);
            } catch (Exception e) {
                log.warn("", e);
            }
        }
    }

    @Override
    public LoginRestResponseBody login(LoginRestRequestBody request) {
        String loginName = request.getLoginName();
        String loginPwd = request.getLoginpwd();
        String md5pwd = "";
        try {
            md5pwd = MD5Utils.md5Encode(loginPwd, "UTF-8");
        } catch (Exception e) {
            throw new ServiceException(CodeMsgConstants.PWDERR_WARNNING,
              this.serviceMessageSource.getMessage(CodeMsgConstants.PWDERR_WARNNING, null, null));
        }
        String uuid = UUID.getUUID();
        String deviceId = request.getDeviceId();
        User user = this.coreUserService.login(loginName, md5pwd, deviceId, uuid);
        LoginRestResponseBody responseBody = new LoginRestResponseBody();
        responseBody.setUuid(uuid);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        return responseBody;
    }

    @Override
    public LoginRestResponseBody loginv1_1(LoginRestRequestBody request) {
        return this.login(request);
    }

    @Override
    public LoginRestResponseBody autoLogin(AutoLoginRestRequestBody request) {
        String deviceId = request.getDeviceId();
        String uuidOld = request.getUuidOld();
        String uuidNew = UUID.getUUID();
        final User user = this.coreUserService.autoLogin(deviceId, uuidOld, uuidNew);

        LoginRestResponseBody responseBody = new LoginRestResponseBody();
        responseBody.setUuid(uuidNew);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        return responseBody;
    }
}
