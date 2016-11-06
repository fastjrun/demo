package com.fastjrun.demospring4.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastjrun.helper.RestResponseHelper;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestResponse;
import com.fastjrun.web.controller.BaseController;

@Controller
@RequestMapping("/json/")
public class JsonParamController extends BaseController {
	

    @RequestMapping(value = "view", method = RequestMethod.POST)
    @ResponseBody
    public Object view(@RequestParam(required = true) String hello) {
        log.info(hello);
        BaseRestResponse<BaseRestDefaultResponseBody> response = RestResponseHelper
                .getSuccessResult();
        return response;
    }
}
