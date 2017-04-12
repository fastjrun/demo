
package com.fastjrun.demo.service.impl;

import java.util.List;
import com.fastjrun.demo.bean.UserLogin;
import com.fastjrun.demo.dao.BaseUserLoginDao;
import com.fastjrun.demo.service.BaseUserLoginService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * This is the service implemention to table 用户登录凭证表.
 * 
 * @author fastjrun
 */
@Service("baseUserLoginService")
public class BaseUserLoginServiceImpl
    implements BaseUserLoginService
{

    @Autowired
    private BaseUserLoginDao baseUserLoginDao;

    public int insert(UserLogin userLogin) {
        return baseUserLoginDao.insert(userLogin);
    }

    public UserLogin selectById(int id) {
        return baseUserLoginDao.selectById(id);
    }

    public int deleteById(int id) {
        return baseUserLoginDao.deleteById(id);
    }

    public int updateById(UserLogin userLogin) {
        return baseUserLoginDao.updateById(userLogin);
    }

    public int totalCount() {
        return baseUserLoginDao.totalCount();
    }

    public List<UserLogin> queryForLimitList(RowBounds rowBounds) {
        return baseUserLoginDao.queryForLimitList(rowBounds);
    }

}
