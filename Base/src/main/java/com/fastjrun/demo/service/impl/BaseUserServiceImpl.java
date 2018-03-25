
package com.fastjrun.demo.service.impl;

import java.util.List;
import com.fastjrun.demo.bean.User;
import com.fastjrun.demo.dao.BaseUserDao;
import com.fastjrun.demo.service.BaseUserService;
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

    public User selectByPK(Integer id) {
        return baseUserDao.selectByPK(id);
    }

    public int deleteByPK(Integer id) {
        return baseUserDao.deleteByPK(id);
    }

    public int updateByPK(User user) {
        return baseUserDao.updateByPK(user);
    }

    public int totalCount() {
        return baseUserDao.totalCount();
    }

    public List<User> queryForLimitList(RowBounds rowBounds) {
        return baseUserDao.queryForLimitList(rowBounds);
    }

}
