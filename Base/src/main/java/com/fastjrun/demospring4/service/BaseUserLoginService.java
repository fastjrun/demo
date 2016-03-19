
package com.fastjrun.demospring4.service;

import java.util.List;
import com.fastjrun.demospring4.bean.UserLogin;
import org.apache.ibatis.session.RowBounds;


/**
 * This is the service to table 用户登录凭证表.
 * 
 * @author fastjrun
 */
public interface BaseUserLoginService {


    public int insert(UserLogin userLogin);

    public UserLogin selectById(int id);

    public int deleteById(int id);

    public int updateById(UserLogin userLogin);

    public int totalCount();

    public List<UserLogin> queryForLimitList(RowBounds rowBounds);

}
