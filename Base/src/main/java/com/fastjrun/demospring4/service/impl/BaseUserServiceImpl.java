
package com.fastjrun.demospring4.service.impl;

import java.util.List;
import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.dao.BaseUserDao;
import com.fastjrun.demospring4.service.BaseUserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * This is the service implemention to table 用户表.
 * 
 * @author fastjrun
 */
@Service("baseUserService")
public class BaseUserServiceImpl
    implements BaseUserService
{

    @Autowired
    private BaseUserDao baseUserDao;

    public int insert(User user) {
        return baseUserDao.insert(user);
    }

    public User selectById(int id) {
        return baseUserDao.selectById(id);
    }

    public int deleteById(int id) {
        return baseUserDao.deleteById(id);
    }

    public int updateById(User user) {
        return baseUserDao.updateById(user);
    }

    public int totalCount() {
        return baseUserDao.totalCount();
    }

    public List<User> queryForLimitList(RowBounds rowBounds) {
        return baseUserDao.queryForLimitList(rowBounds);
    }

}
