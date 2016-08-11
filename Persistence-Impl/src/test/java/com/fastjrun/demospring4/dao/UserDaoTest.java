package com.fastjrun.demospring4.dao;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demospring4.bean.User;
import com.fastjrun.helper.TimeHelper;
import com.fastjrun.test.BaseSpringTestNGTest;

public class UserDaoTest extends BaseSpringTestNGTest {

    @Autowired
    UserDao userDao;

    @Test
    public void testInsert() {
        User user = new User();
        String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        user.setCreateTime(curDate);
        user.setEmail("sa4@fastjrun.org");
        user.setLastLoginTime(curTime);
        user.setLastModifyTime(curDate);
        user.setLoginErrCount(0);
        user.setLoginName("sa3");
        user.setLoginPwd("1234566777");
        user.setMobileNo("03211111112");
        user.setNickName("sa3");
        user.setSex(2);
        user.setStatus("1");
        userDao.insertNoStatusAndLastRecordLoginErrTime(user);
    }

}
