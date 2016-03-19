package com.fastjrun.demospring4.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.dao.BaseUserDao;
import com.fastjrun.demospring4.packet.req.Autologin10Request;
import com.fastjrun.demospring4.packet.req.Login10Request;
import com.fastjrun.demospring4.packet.req.Register10Request;
import com.fastjrun.demospring4.service.CoreUserService;
import com.fastjrun.demospring4.service.UserServiceRest;
import com.fastjrun.demospring4.service.UserServiceTask;
import com.fastjrun.helper.RestHelper;
import com.fastjrun.helper.UUID;
import com.fastjrun.mybatis.ConditionHelper;
import com.fastjrun.mybatis.declare.Condition;
import com.fastjrun.packet.res.RestResult;
import com.fastjrun.service.impl.BaseService;

@Service
public class UserServiceImpl extends BaseService implements UserServiceRest,
        UserServiceTask {
    @Autowired
    private BaseUserDao baseUserDao;
    @Autowired
    private CoreUserService coreUserService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    protected Destination destination;

    @Override
    public RestResult register10(Register10Request request, String deviceId) {
        String loginPwd = request.getLoginpwd();
        String loginName = request.getLoginName();
        String nickName = request.getNickName();
        Integer sex = request.getSex();
        String mobileNo = request.getMobileNo();
        String email = request.getEmail();
        Condition c = new Condition().and("loginName=? and mobileNo = ?",
                loginName, mobileNo);
        ConditionHelper.condition(c.toString());
        List<User> list = baseUserDao.queryForListCondition();
        if (list.size() > 0) {
            return RestHelper.getFailResult("USERREGISTER01", "用户已存在");
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
            RestResult rr = RestHelper.getSuccessResult();
            return rr;
        }
    }

    @Override
    public RestResult login10(Login10Request request, String deviceId) {
        String loginName = request.getLoginName();
        String loginPwd = request.getLoginpwd();
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
        RestResult rr = RestHelper.getSuccessResult();
        rr.bput("uuid", uuid);
        rr.bput("loginName", user.getLoginName());
        rr.bput("sex", user.getSex());
        rr.bput("nickName", user.getNickName());
        rr.bput("mobileNo", user.getMobileNo());
        rr.bput("email", user.getEmail());
        return rr;
    }

    @Override
    public void unlockUserPwd(Date date) {
        this.coreUserService.unlockUserPwd(date);

    }

    @Override
    public void inValidUserLoginCredential(Date date) {
        this.coreUserService.inValidUserLoginCredential(date);

    }

    @Override
    public RestResult autologin10(Autologin10Request request, String deviceId) {
        String uuidOld = request.getUuidOld();
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
        RestResult rr = RestHelper.getSuccessResult();
        rr.bput("uuid", uuidNew);
        rr.bput("loginName", user.getLoginName());
        rr.bput("sex", user.getSex());
        rr.bput("nickName", user.getNickName());
        rr.bput("mobileNo", user.getMobileNo());
        rr.bput("email", user.getEmail());
        return rr;
    }

}
