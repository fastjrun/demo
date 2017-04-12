
package com.fastjrun.demo.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.fastjrun.demo.packet.user.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.user.AutoLoginRestResponseBody;
import com.fastjrun.demo.packet.user.LoginRestRequestBody;
import com.fastjrun.demo.packet.user.LoginRestResponseBody;
import com.fastjrun.demo.packet.user.Loginv1_1RestRequestBody;
import com.fastjrun.demo.packet.user.Loginv1_1RestResponseBody;
import com.fastjrun.demo.packet.user.RegistserRestRequestBody;
import com.fastjrun.demo.service.UserServiceRest;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestRequest;
import com.fastjrun.packet.BaseRestRequestHead;
import com.fastjrun.packet.BaseRestResponse;
import com.fastjrun.web.controller.BaseController;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author robin
 */
@RestController
@RequestMapping("/api/user/")
public class UserRestController
    extends BaseController
{

    @Autowired
    private UserServiceRest userServiceRest;

    @RequestMapping(value = "register/{deviceId}/{reqTime}", method = RequestMethod.POST)
    public Object register(
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        Long reqTime,
        @RequestBody
        @Valid
        RegistserRestRequestBody requestBody, HttpServletRequest httpServletRequest) {
        BaseRestRequest<RegistserRestRequestBody> request = new BaseRestRequest<RegistserRestRequestBody>();
        BaseRestRequestHead requestHead = new BaseRestRequestHead();
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(reqTime);
        request.setHead(requestHead);
        request.setBody(requestBody);
        BaseRestResponse<BaseRestDefaultResponseBody> response = this.userServiceRest.register(request);
        JSONObject object = JSONObject.fromObject(response);
        java.lang.String reqStr = object.toString();
        log.debug(reqStr);
        return response;
    }

    @RequestMapping(value = "login/{deviceId}/{reqTime}", method = RequestMethod.POST)
    public Object login(
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        Long reqTime,
        @RequestBody
        @Valid
        LoginRestRequestBody requestBody, HttpServletRequest httpServletRequest) {
        BaseRestRequest<LoginRestRequestBody> request = new BaseRestRequest<LoginRestRequestBody>();
        BaseRestRequestHead requestHead = new BaseRestRequestHead();
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(reqTime);
        request.setHead(requestHead);
        request.setBody(requestBody);
        BaseRestResponse<LoginRestResponseBody> response = this.userServiceRest.login(request);
        JSONObject object = JSONObject.fromObject(response);
        java.lang.String reqStr = object.toString();
        log.debug(reqStr);
        return response;
    }

    @RequestMapping(value = "login/v1_1/{deviceId}/{reqTime}", method = RequestMethod.POST)
    public Object loginv1_1(
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        Long reqTime,
        @RequestBody
        @Valid
        Loginv1_1RestRequestBody requestBody, HttpServletRequest httpServletRequest) {
        BaseRestRequest<Loginv1_1RestRequestBody> request = new BaseRestRequest<Loginv1_1RestRequestBody>();
        BaseRestRequestHead requestHead = new BaseRestRequestHead();
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(reqTime);
        request.setHead(requestHead);
        request.setBody(requestBody);
        BaseRestResponse<Loginv1_1RestResponseBody> response = this.userServiceRest.loginv1_1(request);
        JSONObject object = JSONObject.fromObject(response);
        java.lang.String reqStr = object.toString();
        log.debug(reqStr);
        return response;
    }

    @RequestMapping(value = "autoLogin/{deviceId}/{reqTime}", method = RequestMethod.POST)
    public Object autoLogin(
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        Long reqTime,
        @RequestBody
        @Valid
        AutoLoginRestRequestBody requestBody, HttpServletRequest httpServletRequest) {
        BaseRestRequest<AutoLoginRestRequestBody> request = new BaseRestRequest<AutoLoginRestRequestBody>();
        BaseRestRequestHead requestHead = new BaseRestRequestHead();
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(reqTime);
        request.setHead(requestHead);
        request.setBody(requestBody);
        BaseRestResponse<AutoLoginRestResponseBody> response = this.userServiceRest.autoLogin(request);
        JSONObject object = JSONObject.fromObject(response);
        java.lang.String reqStr = object.toString();
        log.debug(reqStr);
        return response;
    }

}
