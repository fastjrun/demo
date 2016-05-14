package com.fastjrun.demospring4.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastjrun.helper.RestResponseHelper;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestResponse;
import com.fastjrun.web.controller.BaseController;

@Controller
@RequestMapping("/hello/")
public class HelloController extends BaseController {
	@RequestMapping(value = "world", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public Object add(
        @RequestBody
        @Valid
        String hello, javax.servlet.http.HttpServletRequest httpServletRequest) {
		log.info(hello);
        BaseRestResponse<BaseRestDefaultResponseBody> response = RestResponseHelper.getSuccessResult();
        return response;
    }
}
