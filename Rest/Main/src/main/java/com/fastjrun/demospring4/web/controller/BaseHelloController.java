package com.fastjrun.demospring4.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastjrun.helper.RestResponseHelper;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestResponse;
import com.fastjrun.packet.req.CommonRequestId;
import com.fastjrun.web.controller.BaseController;

@Controller
@RequestMapping("base/10/hello/{deviceId}/{reqTime}/")
public class BaseHelloController extends BaseController {
    @RequestMapping(value = "view", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody @Valid CommonRequestId requestId, @PathVariable("deviceId") String deviceId,
            @PathVariable("reqTime") String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[3];
        String beanName = pathS[4];
        log.info(requestId);
        log.info(deviceId);
        log.info(reqTime);
        BaseRestResponse<BaseRestDefaultResponseBody> response = RestResponseHelper.getSuccessResult();
        return response;
    }
}
