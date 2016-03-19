package com.fastjrun.demospring4.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demospring4.packet.req.Autologin10Request;
import com.fastjrun.demospring4.packet.req.Login10Request;
import com.fastjrun.demospring4.packet.req.Register10Request;
import com.fastjrun.demospring4.service.UserServiceRest;
import com.fastjrun.packet.res.RestResult;
import com.fastjrun.test.BaseSpringTestNGTest;

public class UserServiceRestImplTest extends BaseSpringTestNGTest{

    @Autowired
    UserServiceRest userServiceRest;

    @Test
    public void testRegister10() {
        Register10Request request=new Register10Request();
        request.setEmail("test3@sohu.com");
        request.setLoginName("test4");
        request.setLoginpwd("22222");
        request.setMobileNo("199222336444");
        request.setNickName("nickName4");
        request.setSex(new Integer(0));
        String deviceId="servicetest";
        RestResult result=userServiceRest.register10(request, deviceId);
        JSONObject jsonObject=JSONObject.fromObject(result);
        log.info(jsonObject);
    }

    @Test
    public void testLogin10() {
        Login10Request request=new Login10Request();
        request.setLoginName("test4");
        request.setLoginpwd("22222");
        String deviceId="servicetest";
        RestResult result=userServiceRest.login10(request, deviceId);
        JSONObject jsonObject=JSONObject.fromObject(result);
        log.info(jsonObject);
        
    }

    @Test
    public void testAutoLogin10() {
        Autologin10Request request=new Autologin10Request();
        request.setUuidOld("26024de14f0b496c8b0a4282c1d9b897");
        String deviceId="servicetest";
        RestResult result=userServiceRest.autologin10(request, deviceId);
        JSONObject jsonObject=JSONObject.fromObject(result);
        log.info(jsonObject);
        
    }

}
