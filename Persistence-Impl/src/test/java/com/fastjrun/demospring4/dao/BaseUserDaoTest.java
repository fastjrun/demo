package com.fastjrun.demospring4.dao;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demospring4.bean.User;
import com.fastjrun.helper.TimeHelper;
import com.fastjrun.mybatis.ConditionHelper;
import com.fastjrun.test.BaseSpringTestNGTest;

public class BaseUserDaoTest extends BaseSpringTestNGTest {

    @Autowired
    BaseUserDao baseUserDao;

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
        baseUserDao.insert(user);
    }

    @Test
    public void testSelectById() {
        User user = baseUserDao.selectById(2);
        log.info(user);
    }

    @Test
    public void testUpdateById() {
        User user = baseUserDao.selectById(1);
        log.info(user);
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        user.setCreateTime(curDate);
        user.setLastModifyTime(curDate);
        baseUserDao.updateById(user);
        User user2 = baseUserDao.selectById(1);
        log.info(user2);
    }

    @Test
    public void testDeleteById() {
        User user = baseUserDao.selectById(1);
        log.info(user);
        int res = baseUserDao.deleteById(1);
        log.info(res);
        User user2 = baseUserDao.selectById(1);
        log.info(user2);
    }

    @Test
    public void testTotalCount() {
        int res = baseUserDao.totalCount();
        log.info(res);
    }

    @Test
    public void testQueryForList() {
        List<User> list = baseUserDao.queryForList();
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(itera.next());
            }
        }
    }

    @Test
    public void testQueryForLimitList() {
        RowBounds rowBounds = new RowBounds(2, 4);
        List<User> list = baseUserDao.queryForLimitList(rowBounds);
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(itera.next());
            }
        }
    }

    @Test
    public void testTotalCountCondition() {
        ConditionHelper.condition(" and sex =1");
        int res = baseUserDao.totalCountCondition();
        log.info(res);
    }

    @Test
    public void testQueryForListCondition() {
        ConditionHelper.condition(" and sex =1");
        List<User> list = baseUserDao.queryForListCondition();
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(itera.next());
            }
        }
    }

    @Test
    public void testQueryForLimitListCondition() {
        ConditionHelper.condition(" and sex =1");
        RowBounds rowBounds = new RowBounds(2, 4);
        List<User> list = baseUserDao.queryForLimitListCondition(rowBounds);
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(itera.next());
            }
        }
    }

    @Test
    public void testQueryForListConditionMul() {
        final RowBounds rowBounds = new RowBounds(2, 4);
        final Semaphore semaphore = new Semaphore(100);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e1) {
                log.warn("can't acquire semaphore", e1);
                continue;
            }
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        testCondition(rowBounds);
                    } catch (Exception e) {
                        log.warn("BatchInsertYongle Task for", e);
                    } finally {
                        // 访问完后，释放
                        semaphore.release();
                    }

                }
            };
            executorService.submit(run);

        }
    }

    private void testCondition(RowBounds rowBounds) {
        ConditionHelper.condition(" and sex =1");
        List<User> list = baseUserDao.queryForLimitListCondition(rowBounds);
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(itera.next());
            }
        }
    }

}
