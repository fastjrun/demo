package com.fastjrun.demo.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demo.packet.app.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.app.LoginRestRequestBody;
import com.fastjrun.demo.packet.app.LoginRestResponseBody;
import com.fastjrun.demo.packet.app.RegistserRestRequestBody;
import com.fastjrun.demo.service.UserServiceRest;
import com.fastjrun.packet.BaseAppRequest;
import com.fastjrun.packet.BaseAppRequestHead;
import com.fastjrun.packet.BaseDefaultResponseBody;
import com.fastjrun.packet.BaseResponse;
import com.fastjrun.test.BaseSpringTestNGTest;

public class UserServiceRestImplTest extends BaseSpringTestNGTest {

    @Autowired
    UserServiceRest userServiceRest;

    @Test
    public void testRegister() {
        BaseAppRequest<RegistserRestRequestBody> request = new BaseAppRequest<RegistserRestRequestBody>();
        BaseAppRequestHead head = new BaseAppRequestHead();
        RegistserRestRequestBody body = new RegistserRestRequestBody();
        body.setEmail("test1@sohu.com");
        body.setLoginId("test12");
        body.setLoginpwd("22222");
        body.setMobileNo("199222336452");
        body.setNickName("走起");
        body.setSex("1");
        String deviceId = "servicetest";
        head.setDeviceId(deviceId);
        request.setHead(head);
        request.setBody(body);
        BaseResponse<BaseDefaultResponseBody> result = userServiceRest
                .register(request);
        JSONObject jsonObject = JSONObject.fromObject(result);
        log.info(jsonObject);
    }

    @Test
    public void testLogin() {
        BaseAppRequest<LoginRestRequestBody> request = new BaseAppRequest<LoginRestRequestBody>();
        BaseAppRequestHead head = new BaseAppRequestHead();
        LoginRestRequestBody body = new LoginRestRequestBody();
        body.setLoginName("test12");
        body.setLoginpwd("22222");
        String deviceId = "servicetest";
        head.setDeviceId(deviceId);
        request.setHead(head);
        request.setBody(body);
        BaseResponse<LoginRestResponseBody> result = userServiceRest
                .login(request);
        JSONObject jsonObject = JSONObject.fromObject(result);
        log.info(jsonObject);

    }

    @Test
    public void testAutoLogin() {
        BaseAppRequest<AutoLoginRestRequestBody> request = new BaseAppRequest<AutoLoginRestRequestBody>();
        BaseAppRequestHead head = new BaseAppRequestHead();
        AutoLoginRestRequestBody body = new AutoLoginRestRequestBody();
        body.setUuidOld("aaa0d66c2de446bca5079fc343d829be");
        String deviceId = "servicetest";
        head.setDeviceId(deviceId);
        request.setHead(head);
        request.setBody(body);
        BaseResponse<LoginRestResponseBody> result = userServiceRest
                .autoLogin(request);
        JSONObject jsonObject = JSONObject.fromObject(result);
        log.info(jsonObject);

    }

}
