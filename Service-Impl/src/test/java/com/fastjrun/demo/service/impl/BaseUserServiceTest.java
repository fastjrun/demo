package com.fastjrun.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.testng.annotations.Test;

import com.fastjrun.demo.bean.User;
import com.fastjrun.demo.service.BaseUserService;
import com.fastjrun.test.BaseSpringTestNGTest;
import com.fastjrun.util.FreeMarkerUtil;

public class BaseUserServiceTest extends BaseSpringTestNGTest {

    @Autowired
    BaseUserService baseUserService;
    @Autowired
    FreeMarkerUtil freeMarkerUtil;

    @Test
    public void testshowUserList() {
        Map<String, Object> map = new HashMap<String, Object>();
        ModelMap model = new ModelMap();
        RowBounds rowBounds = new RowBounds(0, 10);
        List<User> list = baseUserService.queryForLimitList(rowBounds);
        Integer totalCount = new Integer(baseUserService.totalCount());
        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        map.put("list", list);
        map.put("totalCount", totalCount);

        try {
            String body = this.freeMarkerUtil.renderFile(model, "user/list.html");
            log.info(body);
        } catch (Exception e) {
            log.error("",e);
        }
    }

}
