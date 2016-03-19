
package com.fastjrun.demospring4.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.fastjrun.demospring4.packet.req.Autologin10Request;
import com.fastjrun.demospring4.packet.req.Login10Request;
import com.fastjrun.demospring4.packet.req.Register10Request;
import com.fastjrun.demospring4.service.UserServiceRest;
import com.fastjrun.packet.req.RequestHead;
import com.fastjrun.packet.res.RestResult;
import com.fastjrun.web.controller.BaseController;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * This is the controller for User
 * 
 * @author fastjrun
 */
@RestController
@RequestMapping("/user/")
public class UserController
    extends BaseController
{

    @Autowired
    private UserServiceRest userServiceRest;

    @RequestMapping(value = "register/10/{deviceId}/{reqTime}", method = RequestMethod.POST)
    public Object register10(
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        String reqTime,
        @RequestBody
        @Valid
        Register10Request request, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String txType = pathS[ 3 ];
        String methodName = pathS[ 4 ];
        String ver = pathS[ 5 ];
        RequestHead head = new RequestHead();
        head.setTxType(txType);
        head.setMethodName(methodName);
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        RestResult response = this.userServiceRest.register10(request, deviceId);
        JSONObject object = JSONObject.fromObject(response);
        java.lang.String reqStr = object.toString();
        log.info(reqStr);
        return response;
    }

    @RequestMapping(value = "login/10/{deviceId}/{reqTime}", method = RequestMethod.POST)
    public Object login10(
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        String reqTime,
        @RequestBody
        @Valid
        Login10Request request, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String txType = pathS[ 3 ];
        String methodName = pathS[ 4 ];
        String ver = pathS[ 5 ];
        RequestHead head = new RequestHead();
        head.setTxType(txType);
        head.setMethodName(methodName);
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        RestResult response = this.userServiceRest.login10(request, deviceId);
        JSONObject object = JSONObject.fromObject(response);
        java.lang.String reqStr = object.toString();
        log.info(reqStr);
        return response;
    }

    @RequestMapping(value = "autologin/10/{deviceId}/{reqTime}", method = RequestMethod.POST)
    public Object autologin10(
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        String reqTime,
        @RequestBody
        @Valid
        Autologin10Request request, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String txType = pathS[ 3 ];
        String methodName = pathS[ 4 ];
        String ver = pathS[ 5 ];
        RequestHead head = new RequestHead();
        head.setTxType(txType);
        head.setMethodName(methodName);
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        RestResult response = this.userServiceRest.autologin10(request, deviceId);
        JSONObject object = JSONObject.fromObject(response);
        java.lang.String reqStr = object.toString();
        log.info(reqStr);
        return response;
    }

}
