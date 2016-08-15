package com.fastjrun.demospring4.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.ibatis.session.RowBounds;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.dao.BaseUserDao;
import com.fastjrun.demospring4.dao.UserDao;
import com.fastjrun.demospring4.packet.user.AutoLoginRestRequestBody;
import com.fastjrun.demospring4.packet.user.AutoLoginRestResponseBody;
import com.fastjrun.demospring4.packet.user.LoginRestRequestBody;
import com.fastjrun.demospring4.packet.user.LoginRestResponseBody;
import com.fastjrun.demospring4.packet.user.Loginv1_1RestRequestBody;
import com.fastjrun.demospring4.packet.user.Loginv1_1RestResponseBody;
import com.fastjrun.demospring4.packet.user.RegistserRestRequestBody;
import com.fastjrun.demospring4.service.CoreUserService;
import com.fastjrun.demospring4.service.UserServiceAjax;
import com.fastjrun.demospring4.service.UserServiceRest;
import com.fastjrun.demospring4.service.UserServiceTask;
import com.fastjrun.helper.RestResponseHelper;
import com.fastjrun.helper.UUID;
import com.fastjrun.mybatis.ConditionHelper;
import com.fastjrun.mybatis.declare.Condition;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestRequest;
import com.fastjrun.packet.BaseRestResponse;
import com.fastjrun.packet.BaseRestResponseHead;
import com.fastjrun.service.impl.BaseService;

@Service
public class UserServiceImpl extends BaseService implements UserServiceRest,
        UserServiceTask, UserServiceAjax {
    @Autowired
    private BaseUserDao baseUserDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CoreUserService coreUserService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    protected Destination destination;

    @Override
    public void unlockUserPwd(Date date) {
        this.coreUserService.unlockUserPwd(date);

    }

    @Override
    public void inValidUserLoginCredential(Date date) {
        this.coreUserService.inValidUserLoginCredential(date);

    }

    @Override
    public BaseRestResponse<BaseRestDefaultResponseBody> register(
            BaseRestRequest<RegistserRestRequestBody> request) {
        String loginPwd = request.getBody().getLoginpwd();
        String loginName = request.getBody().getLoginId();
        String nickName = request.getBody().getNickName();
        Integer sex = Integer.parseInt(request.getBody().getSex());
        String mobileNo = request.getBody().getMobileNo();
        String email = request.getBody().getEmail();
        Condition c = new Condition().and("loginName=? and mobileNo = ?",
                loginName, mobileNo);
        ConditionHelper.condition(c.toString());
        List<User> list = baseUserDao.queryForListCondition();
        if (!list.isEmpty()) {
            return RestResponseHelper.getFailResult("USERREGISTER01", "用户已存在");
        } else {
            Timestamp curTimestamp = new Timestamp(System.currentTimeMillis());
            User user = new User();
            user.setCreateTime(curTimestamp);
            user.setEmail(email);
            user.setLastModifyTime(curTimestamp);
            user.setLoginName(loginName);
            user.setLoginPwd(loginPwd);
            user.setMobileNo(mobileNo);
            user.setNickName(nickName);
            user.setSex(sex);
            user.setStatus("1");
            user.setLastRecordLoginErrTime(null);
            user.setLoginErrCount(new Integer(0));
            baseUserDao.insert(user);
            try {
                rabbitTemplate.convertAndSend(user);
            } catch (Exception e) {
                log.warn("", e);
            }
            return RestResponseHelper.getSuccessResult();
        }
    }

    @Override
    public BaseRestResponse<LoginRestResponseBody> login(
            BaseRestRequest<LoginRestRequestBody> request) {
        String loginName = request.getBody().getLoginName();
        String loginPwd = request.getBody().getLoginpwd();
        String deviceId = request.getHead().getDeviceId();
        String uuid = UUID.getUUID();
        final User user = this.coreUserService.login(loginName, loginPwd,
                deviceId, uuid);
        try {
            MessageCreator messageCreator = new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                        throws JMSException {
                    return session.createObjectMessage(user);
                }
            };
            this.jmsTemplate.send(this.destination, messageCreator);
        } catch (Exception e) {
            log.warn("", e);
        }
        BaseRestResponse<LoginRestResponseBody> response = new BaseRestResponse<LoginRestResponseBody>();
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("ok");
        response.setHead(responseHead);
        LoginRestResponseBody responseBody = new LoginRestResponseBody();
        responseBody.setUuid(uuid);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        response.setBody(responseBody);
        return response;
    }

    @Override
    public BaseRestResponse<Loginv1_1RestResponseBody> loginv1_1(
            BaseRestRequest<Loginv1_1RestRequestBody> request) {
        String loginName = request.getBody().getLoginName();
        String loginPwd = request.getBody().getLoginpwd();
        String deviceId = request.getHead().getDeviceId();
        String uuid = UUID.getUUID();
        final User user = this.coreUserService.login(loginName, loginPwd,
                deviceId, uuid);
        try {
            MessageCreator messageCreator = new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                        throws JMSException {
                    return session.createObjectMessage(user);
                }
            };
            this.jmsTemplate.send(this.destination, messageCreator);
        } catch (Exception e) {
            log.warn("", e);
        }
        BaseRestResponse<Loginv1_1RestResponseBody> response = new BaseRestResponse<Loginv1_1RestResponseBody>();
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("ok");
        response.setHead(responseHead);
        Loginv1_1RestResponseBody responseBody = new Loginv1_1RestResponseBody();
        responseBody.setUuid(uuid);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        response.setBody(responseBody);
        return response;
    }

    @Override
    public BaseRestResponse<AutoLoginRestResponseBody> autoLogin(
            BaseRestRequest<AutoLoginRestRequestBody> request) {
        String uuidOld = request.getBody().getUuidOld();
        String deviceId = request.getHead().getDeviceId();
        String uuidNew = UUID.getUUID();
        final User user = this.coreUserService.autoLogin(deviceId, uuidOld,
                uuidNew);
        try {
            MessageCreator messageCreator = new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                        throws JMSException {
                    return session.createObjectMessage(user);
                }
            };
            this.jmsTemplate.send(this.destination, messageCreator);
        } catch (Exception e) {
            log.warn("", e);
        }
        BaseRestResponse<AutoLoginRestResponseBody> response = new BaseRestResponse<AutoLoginRestResponseBody>();
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("ok");
        response.setHead(responseHead);
        AutoLoginRestResponseBody responseBody = new AutoLoginRestResponseBody();
        responseBody.setUuidNew(uuidNew);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        response.setBody(responseBody);
        return response;
    }

    @Override
    public int updateById(User user) {
        return userDao.updateUserInfoById(user);

    }

    @Override
    public int insert(User user) {
        return baseUserDao.insert(user);
    }

    @Override
    public User selectById(Integer id) {
        return baseUserDao.selectById(id.intValue());
    }

    @Override
    public int deleteById(Integer id) {
        return baseUserDao.deleteById(id.intValue());
    }

    @Override
    public List<Map<String, Object>> queryForLimitList(RowBounds rowBounds) {
        List<User> list = baseUserDao.queryForLimitList(rowBounds);
        List<Map<String, Object>> restList = new ArrayList<Map<String, Object>>();
        Iterator<User> itera = list.iterator();
        while (itera.hasNext()) {
            User user = itera.next();
            Map<String, Object> restMap = new HashMap<String, Object>();
            restMap.put("loginPwd", user.getLoginPwd());
            restMap.put("nickName", user.getNickName());
            restMap.put("sex", user.getSex());
            restMap.put("mobileNo", user.getMobileNo());
            restMap.put("loginErrCount", user.getLoginErrCount());
            restMap.put("lastLoginTime", user.getLastLoginTime());
            restMap.put("createTime", user.getCreateTime());
            restMap.put("loginName", user.getLoginName());
            restMap.put("lastModifyTime", user.getLastModifyTime());
            restMap.put("id", user.getId());
            restMap.put("email", user.getEmail());
            restMap.put("lastRecordLoginErrTime",
                    user.getLastRecordLoginErrTime());
            restMap.put("status", user.getStatus());
            restList.add(restMap);
        }

        return restList;
    }
}
