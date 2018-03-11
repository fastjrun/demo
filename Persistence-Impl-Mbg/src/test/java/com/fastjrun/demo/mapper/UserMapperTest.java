package com.fastjrun.demo.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.demo.entity.User;
import com.fastjrun.demo.entity.UserExample;
import com.fastjrun.helper.TimeHelper;
import com.fastjrun.test.BaseSpringTestNGTest;

public class UserMapperTest extends BaseSpringTestNGTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        String curTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
        Date curDate = new Date();
        user.setCreatetime(curDate);
        user.setEmail("sa4@fastjrun.org");
        user.setLastlogintime(curTime);
        user.setLastmodifytime(curDate);
        user.setLoginerrcount(Short.valueOf("0"));
        user.setLoginname("sa3");
        user.setLoginpwd("1234566777");
        user.setMobileno("03211111112");
        user.setNickname("sa3");
        user.setSex(Short.valueOf("2"));
        user.setStatus("1");
        userMapper.insert(user);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(38));
        log.info(user);
    }

    @Test
    public void testUpdateById() {
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(38));
        log.info(user);
        Date curDate = new Date();
        user.setCreatetime(curDate);
        user.setLastmodifytime(curDate);
        userMapper.updateByPrimaryKey(user);
        User user2 = userMapper.selectByPrimaryKey(Integer.valueOf(38));
        log.info(user2);
    }

    @Test
    public void testDeleteById() {
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(38));
        log.info(user);
        int res = userMapper.deleteByPrimaryKey(Integer.valueOf(38));
        log.info(res);
        User user2 = userMapper.selectByPrimaryKey(Integer.valueOf(38));
        log.info(user2);
    }

    @Test
    public void testTotalCount() {
        UserExample example=new UserExample();
        long res = userMapper.countByExample(example);
        log.info(res);
    }

    @Test
    public void testQueryForList() {
        UserExample example=new UserExample();
        List<User> list = userMapper.selectByExample(example);
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(JSONObject.fromObject(itera.next()));
            }
        }
    }

    @Test
    public void testQueryForLimitList() {
        RowBounds rowBounds = new RowBounds(2, 4);
        UserExample example=new UserExample();
        List<User> list = userMapper.selectByExampleWithRowbounds(example,rowBounds);
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(JSONObject.fromObject(itera.next()));
            }
        }
    }

    @Test
    public void testTotalCountCondition() {
        UserExample example=new UserExample();
        example.createCriteria().andSexEqualTo(Short.valueOf("1"));
        long res = userMapper.countByExample(example);
        log.info(res);
    }

    @Test
    public void testQueryForListCondition() {
        UserExample example=new UserExample();
        example.createCriteria().andSexEqualTo(Short.valueOf("0"));
        List<User> list = userMapper.selectByExample(example);
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(itera.next());
            }
        }
    }

    @Test
    public void testQueryForLimitListCondition() {
        RowBounds rowBounds = new RowBounds(2, 4);
        UserExample example=new UserExample();
        example.createCriteria().andSexEqualTo(Short.valueOf("1"));
        List<User> list = userMapper.selectByExampleWithRowbounds(example,rowBounds);
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(itera.next());
            }
        }
        example.clear();
        example.createCriteria().andSexEqualTo(Short.valueOf("1"));
        list =  userMapper.selectByExampleWithRowbounds(example,rowBounds);
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
        final Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Boolean>> resultList = new ArrayList<Future<Boolean>>();
        for (int i = 0; i < 1000; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e1) {
                log.warn("can't acquire semaphore", e1);
                continue;
            }
            Callable<Boolean> run = new Callable<Boolean>() {
                public Boolean call() {
                    try {
                        testCondition(rowBounds);
                    } catch (Exception e) {
                        log.warn("BatchInsert Task for", e);
                    } finally {
                        // 访问完后，释放
                        semaphore.release();
                    }
                    return true;
                }
            };
            Future<Boolean> future = executorService.submit(run);
            resultList.add(future);
        }
        Boolean isFinished = false;
        while (!isFinished) {
            for (Future<Boolean> fs : resultList) {
                try {
                    isFinished = !isFinished && fs.get();
                    log.debug(isFinished);
                    if (!isFinished) {
                        resultList.remove(fs);
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void testCondition(RowBounds rowBounds) {
        UserExample example=new UserExample();
        example.createCriteria().andSexEqualTo(Short.valueOf("1"));
        List<User> list = userMapper.selectByExampleWithRowbounds(example,rowBounds);
        if (list != null) {
            Iterator<User> itera = list.iterator();
            while (itera.hasNext()) {
                log.info(itera.next());
            }
        }
    }

}
