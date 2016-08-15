package com.fastjrun.demospring4.web.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.dao.UserDao;
import com.fastjrun.demospring4.service.BaseUserService;
import com.fastjrun.demospring4.service.UserServiceAjax;
import com.fastjrun.web.controller.BaseController;

/**
 * 
 * @author robin
 */
@RestController
@RequestMapping("/json/user/")
public class UserJsonController extends BaseController {

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    private UserServiceAjax userServiceAjax;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "list")
    public Object queryForList(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "5") Integer limit) {
        int totalCount = baseUserService.totalCount();
        Integer offsetPage = offset / limit + 1;
        offsetPage = offsetPage > 1 ? offsetPage : 1;
        RowBounds rowBounds = new RowBounds(offsetPage, limit);
        List<Map<String, Object>> list = userServiceAjax
                .queryForLimitList(rowBounds);
        Map<String, Object> restMap = new HashMap<String, Object>();
        restMap.put("total", totalCount);
        restMap.put("rows", list);
        return restMap;
    }

    @RequestMapping(value = "doAdd")
    public Object doAdd(@RequestParam(required = true) String loginName,
            @RequestParam(required = true) String loginPwd,
            @RequestParam(required = true) String nickName,
            @RequestParam(required = true) Integer sex,
            @RequestParam(required = true) String mobileNo,
            @RequestParam(required = true) String email) {

        Timestamp curTimestamp = new Timestamp(System.currentTimeMillis());
        User user = new User();
        user.setCreateTime(curTimestamp);
        user.setEmail(email);
        user.setLastModifyTime(curTimestamp);
        user.setLoginName(loginName);
        user.setLoginPwd(loginPwd);
        user.setMobileNo(mobileNo);
        user.setNickName(nickName);
        user.setSex(sex);
        user.setStatus("1");
        user.setLastRecordLoginErrTime(null);
        user.setLoginErrCount(new Integer(0));
        int res = userServiceAjax.insert(user);
        Map<String, Object> restMap = new HashMap<String, Object>();
        if (res > 0) {
            restMap.put("status", "success");
        } else {
            restMap.put("status", "fail");
        }
        return restMap;
    }

    @RequestMapping(value = "doUpdate")
    public Object doUpdate(@RequestParam(required = true) String loginName,
            @RequestParam(required = true) String loginPwd,
            @RequestParam(required = true) String nickName,
            @RequestParam(required = true) Integer sex,
            @RequestParam(required = true) String mobileNo,
            @RequestParam(required = true) String email,
            @RequestParam(required = true) Integer id) {
        Timestamp curTimestamp = new Timestamp(System.currentTimeMillis());
        User user = new User();
        user.setEmail(email);
        user.setLastModifyTime(curTimestamp);
        user.setLoginName(loginName);
        user.setLoginPwd(loginPwd);
        user.setMobileNo(mobileNo);
        user.setNickName(nickName);
        user.setSex(sex);
        user.setId(id);
        int res = userServiceAjax.updateById(user);
        Map<String, Object> restMap = new HashMap<String, Object>();
        if (res > 0) {
            restMap.put("status", "success");
        } else {
            restMap.put("status", "fail");
        }
        return restMap;
    }

    @RequestMapping(value = "view")
    public Object view(@RequestParam(required = true) Integer id) {
        User data = userServiceAjax.selectById(id);
        return data;
    }

    @RequestMapping(value = "doDelete")
    public Object doDelete(@RequestParam(required = true) Integer id) {
        int res = userServiceAjax.deleteById(id);
        Map<String, Object> restMap = new HashMap<String, Object>();
        if (res > 0) {
            restMap.put("status", "success");
        } else {
            restMap.put("status", "fail");
        }

        return restMap;
    }

}
