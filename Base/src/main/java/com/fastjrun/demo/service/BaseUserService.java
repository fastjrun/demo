
package com.fastjrun.demo.service;

import java.util.List;
import com.fastjrun.demo.bean.User;
import org.apache.ibatis.session.RowBounds;


/**
 * This is the service to table 用户表.
 * 
 * @author fastjrun
 */
public interface BaseUserService {


    public int insert(User user);

    public User selectById(int id);

    public int deleteById(int id);

    public int updateById(User user);

    public int totalCount();

    public List<User> queryForLimitList(RowBounds rowBounds);

}
