package com.fastjrun.demospring4.web.controller;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.service.BaseUserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    BaseUserService baseUserService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showUserList(@ModelAttribute("model") ModelMap model) {
        RowBounds rowBounds = new RowBounds(0, 10);
        List<User> list = baseUserService.queryForLimitList(rowBounds);
        Integer totalCount = new Integer(baseUserService.totalCount());
        model.addAttribute("userList", list);
        model.addAttribute("vpiym", totalCount);
        return "list";
    }

    @RequestMapping(value = "initadd", method = RequestMethod.GET)
    public String init2add(@ModelAttribute("user") User user) {

        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {

        if (user != null) {

            baseUserService.insert(user);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/success.do";
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String showCustomer(@ModelAttribute("user") User user) {
        return "success";
    }
}
