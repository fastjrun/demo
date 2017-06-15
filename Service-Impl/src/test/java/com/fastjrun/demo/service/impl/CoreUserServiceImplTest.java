package com.fastjrun.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demo.service.CoreUserService;
import com.fastjrun.test.BaseSpringTestNGTest;

public class CoreUserServiceImplTest extends BaseSpringTestNGTest{

    @Autowired
    CoreUserService coreUserService;

    @Test
    public void testGetRedisUser() {
        String uuid="9894f39b5cad4981afe2928f31ae72c1";
        String deviceId="servicetest";
        
    }

}
