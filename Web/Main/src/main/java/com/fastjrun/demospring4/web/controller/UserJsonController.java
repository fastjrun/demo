
package com.fastjrun.demospring4.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.service.BaseUserService;
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

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Object queryForList(@RequestParam(required = false, defaultValue = "1") Integer offset,
            @RequestParam(required = false, defaultValue = "10") Integer limit) {
        int totalCount = baseUserService.totalCount();
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<User> list = baseUserService.queryForLimitList(rowBounds);
        Map<String, Object> restMap = new HashMap<String, Object>();
        restMap.put("total", totalCount);
        restMap.put("rows", list);
        return restMap;
    }

}
