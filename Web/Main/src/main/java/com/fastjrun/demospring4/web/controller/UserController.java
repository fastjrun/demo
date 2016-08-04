package com.fastjrun.demospring4.web.controller;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.service.BaseUserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    BaseUserService baseUserService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("userName", "zhangsan");
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showUserList(Model model,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "5") int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<User> list = baseUserService.queryForLimitList(rowBounds);
        int totalCount = baseUserService.totalCount();
        model.addAttribute("userList", list);
        model.addAttribute("totalCount", totalCount);
        return "list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") User user,
            RedirectAttributes redirectAttributes) {
        if (user != null) {
            baseUserService.insert(user);
        }
        return "redirect:list.do";
    }
    


    @RequestMapping(value = "/addAjax", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") User user,
            RedirectAttributes redirectAttributes) {
        if (user != null) {
            baseUserService.insert(user);
        }
        return "redirect:list.do";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Model model,
            @RequestParam(required = true) int id) {
        User user=baseUserService.selectById(id);
        model.addAttribute("user", user);
        return "detail";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Model model,
            @RequestParam(required = true) int id) {
        baseUserService.deleteById(id);
        return "redirect:list.do";
    }
}
