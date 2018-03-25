
package com.fastjrun.demo.service;

import java.util.List;
import com.fastjrun.demo.bean.UserLogin;
import org.apache.ibatis.session.RowBounds;


/**
 * This is the service to table 用户登录凭证表.
 * 
 * @author fastjrun
 */
public interface BaseUserLoginService {


    public int insert(UserLogin userLogin);

    public UserLogin selectByPK(Integer id);

    public int deleteByPK(Integer id);

    public int updateByPK(UserLogin userLogin);

    public int totalCount();

    public List<UserLogin> queryForLimitList(RowBounds rowBounds);

}
