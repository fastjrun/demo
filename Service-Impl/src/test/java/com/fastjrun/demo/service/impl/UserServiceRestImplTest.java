package com.fastjrun.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demo.packet.user.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.user.AutoLoginRestResponseBody;
import com.fastjrun.demo.packet.user.LoginRestRequestBody;
import com.fastjrun.demo.packet.user.LoginRestResponseBody;
import com.fastjrun.demo.packet.user.RegistserRestRequestBody;
import com.fastjrun.demo.service.UserServiceRest;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestRequest;
import com.fastjrun.packet.BaseRestRequestHead;
import com.fastjrun.packet.BaseRestResponse;
import com.fastjrun.test.BaseSpringTestNGTest;

import net.sf.json.JSONObject;

public class UserServiceRestImplTest extends BaseSpringTestNGTest {

    @Autowired
    UserServiceRest userServiceRest;

    @Test
    public void testRegister() {
        BaseRestRequest<RegistserRestRequestBody> request = new BaseRestRequest<RegistserRestRequestBody>();
        BaseRestRequestHead head = new BaseRestRequestHead();
        RegistserRestRequestBody body = new RegistserRestRequestBody();
        body.setEmail("test1@sohu.com");
        body.setLoginId("test1");
        body.setLoginpwd("22222");
        body.setMobileNo("199222336441");
        body.setNickName("走起");
        body.setSex("1");
        String deviceId = "servicetest";
        head.setDeviceId(deviceId);
        request.setHead(head);
        request.setBody(body);
        BaseRestResponse<BaseRestDefaultResponseBody> result = userServiceRest.register(request);
        JSONObject jsonObject = JSONObject.fromObject(result);
        log.info(jsonObject);
    }

    @Test
    public void testLogin() {
        BaseRestRequest<LoginRestRequestBody> request = new BaseRestRequest<LoginRestRequestBody>();
        BaseRestRequestHead head = new BaseRestRequestHead();
        LoginRestRequestBody body = new LoginRestRequestBody();
        body.setLoginName("test1");
        body.setLoginpwd("22222");
        String deviceId = "servicetest";
        head.setDeviceId(deviceId);
        request.setHead(head);
        request.setBody(body);
        BaseRestResponse<LoginRestResponseBody> result = userServiceRest.login(request);
        JSONObject jsonObject = JSONObject.fromObject(result);
        log.info(jsonObject);

    }

    @Test
    public void testAutoLogin10() {
        BaseRestRequest<AutoLoginRestRequestBody> request = new BaseRestRequest<AutoLoginRestRequestBody>();
        BaseRestRequestHead head = new BaseRestRequestHead();
        AutoLoginRestRequestBody body = new AutoLoginRestRequestBody();
        body.setUuidOld("ceb848e116674cad8a710070e807e108");
        String deviceId = "servicetest";
        head.setDeviceId(deviceId);
        request.setHead(head);
        request.setBody(body);
        BaseRestResponse<AutoLoginRestResponseBody> result = userServiceRest.autoLogin(request);
        JSONObject jsonObject = JSONObject.fromObject(result);
        log.info(jsonObject);

    }

}
