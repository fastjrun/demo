package com.fastjrun.demo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.fastjrun.demo.bean.User;

public interface UserServiceAjax {

    public List<Map<String, Object>> queryForLimitList(RowBounds rowBounds);

    public int updateById(User user);

    public int insert(User user);

    public User selectById(Integer id);

    public int deleteById(Integer id);
}
