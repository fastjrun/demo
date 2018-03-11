package com.fastjrun.demo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demo.bean.User;
import com.fastjrun.test.BaseSpringTestNGTest;

public class BaseUserDaoTest extends BaseSpringTestNGTest {

    @Autowired
    BaseUserDao baseUserDao;

    @Test
    public void insert() {
        User user = new User();
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setEmail("email");
        user.setLastLoginTime("20170104124578123");
        user.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
        user.setLoginErrCount(0);
        user.setLoginName("loginName");
        user.setLoginPwd("2psds");
        user.setMobileNo("19945644444");
        user.setNickName("nickName");
        user.setSex(1);
        user.setStatus("1");
        int res = baseUserDao.insert(user);
        log.info(res);
    }

    @Test
    public void selectByPK() {
        User res = baseUserDao.selectByPK(2);
        log.info(res);

    }

    @Test
    public void deleteByPK() {
        User res = baseUserDao.selectByPK(12);
        log.info(res);
        baseUserDao.deleteByPK(12);
        User res2 = baseUserDao.selectByPK(12);
        log.info(res2);

    }

    @Test
    public void updateByPK() {
        User res = baseUserDao.selectByPK(2);
        res.setEmail("helloM");
        baseUserDao.updateByPK(res);
        User res2 = baseUserDao.selectByPK(2);
        log.info(res2);

    }

    @Test
    public void totalCount() {
        int res = baseUserDao.totalCount();
        log.info(res);

    }

    @Test
    public void queryForList() {
        List<User> list = baseUserDao.queryForList();
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i));
        }

    }

    @Test
    public void queryForLimitList() {
        RowBounds rowBounds = new RowBounds(2, 3);
        List<User> list = baseUserDao.queryForLimitList(rowBounds);
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i));
        }

    }

    @Test
    public void totalCountCondition() {
        String condition = " and `mobileNo`='16600000053'";
        int res = baseUserDao.totalCountCondition(condition);
        log.info(res);

    }

    @Test
    public void selectOneCondition() {
        String condition = " and `mobileNo`='19945644444'";
        User res = baseUserDao.selectOneCondition(condition);
        log.info(res);

    }

    @Test
    public void queryForListCondition() {
        String condition = " and `id`>'0'";
        List<User> list = baseUserDao.queryForListCondition(condition);
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i));
        }

    }

    @Test
    public void queryForLimitListCondition() {
        RowBounds rowBounds = new RowBounds(2, 3);
        String condition = " and `email`like 'email%'";
        List<User> list = baseUserDao.queryForLimitListCondition(condition,
                rowBounds);
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i));
        }

    }

    @Test
    public void insertAll() {
        List<User> list = new ArrayList<User>();
        for (int i = 53; i < 63; i++) {
            User user = new User();
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            user.setEmail("ema" + i);
            user.setLastLoginTime("20170104124578" + i);
            user.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
            user.setLoginErrCount(0);
            user.setLoginName("loginName" + i);
            user.setLoginPwd("password" + i);
            user.setMobileNo("166000000" + i);
            user.setNickName("nickName" + i);
            user.setSex(1);
            user.setStatus("1");
            list.add(user);
        }
        int res = baseUserDao.insertAll(list);
        log.info(res);

    }

}
