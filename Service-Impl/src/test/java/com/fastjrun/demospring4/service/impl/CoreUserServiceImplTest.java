package com.fastjrun.demospring4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.service.CoreUserService;
import com.fastjrun.test.BaseSpringTestNGTest;

public class CoreUserServiceImplTest extends BaseSpringTestNGTest{

    @Autowired
    CoreUserService coreUserService;

    @Test
    public void testGetRedisUser() {
        String uuid="9ce89edf15644a17b50a9e54103af7a5";
        String deviceId="servicetest";
        User user=coreUserService.getRedisUser(uuid, deviceId);
        log.info(user);
        
    }

}
