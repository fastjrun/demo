package com.fastjrun.demospring4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demospring4.packet.user.AutoLoginRestRequestBody;
import com.fastjrun.demospring4.packet.user.AutoLoginRestResponseBody;
import com.fastjrun.demospring4.packet.user.LoginRestRequestBody;
import com.fastjrun.demospring4.packet.user.LoginRestResponseBody;
import com.fastjrun.demospring4.packet.user.RegistserRestRequestBody;
import com.fastjrun.demospring4.service.UserServiceRest;
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
        body.setEmail("test3@sohu.com");
        body.setLoginId("test9");
        body.setLoginpwd("22222");
        body.setMobileNo("199222336444");
        body.setNickName("走起");
        body.setSex("0");
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
        body.setLoginName("test9");
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
        body.setUuidOld("97740a1f03c54bb7b6d849a1feaa3dbd");
        String deviceId = "servicetest";
        head.setDeviceId(deviceId);
        request.setHead(head);
        request.setBody(body);
        BaseRestResponse<AutoLoginRestResponseBody> result = userServiceRest.autoLogin(request);
        JSONObject jsonObject = JSONObject.fromObject(result);
        log.info(jsonObject);

    }

}
