
package com.fastjrun.demo.web.controller;

import javax.validation.Valid;
import com.fastjrun.demo.packet.app.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.app.RegistserRestRequestBody;
import com.fastjrun.demo.service.UserServiceRest;
import com.fastjrun.packet.BaseAppRequest;
import com.fastjrun.packet.BaseAppRequestHead;
import com.fastjrun.packet.BaseDefaultResponseBody;
import com.fastjrun.packet.BaseResponse;
import com.fastjrun.web.controller.BaseAppController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author fastjrun
 */
@RestController
@RequestMapping("/app/user/")
public class UserRestController
    extends BaseAppController
{

    @Autowired
    private UserServiceRest userServiceRest;

    @RequestMapping(value = "register/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<BaseDefaultResponseBody> register(
        @PathVariable("appKey")
        String appKey,
        @PathVariable("appVersion")
        String appVersion,
        @PathVariable("appSource")
        String appSource,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("txTime")
        Long txTime,
        @RequestBody
        @Valid
        RegistserRestRequestBody requestBody) {
        BaseAppRequest<RegistserRestRequestBody> request = new BaseAppRequest<RegistserRestRequestBody>();
        BaseAppRequestHead requestHead = new BaseAppRequestHead();
        requestHead.setAppKey(appKey);
        requestHead.setAppVersion(appVersion);
        requestHead.setAppSource(appSource);
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(txTime);
        this.processHead(requestHead);
        request.setHead(requestHead);
        request.setBody(requestBody);
        BaseResponse<BaseDefaultResponseBody> response = this.userServiceRest.register(request);
        log.debug(response);
        return response;
    }

    @RequestMapping(value = "login/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> login(
        @PathVariable("appKey")
        String appKey,
        @PathVariable("appVersion")
        String appVersion,
        @PathVariable("appSource")
        String appSource,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("txTime")
        Long txTime,
        @RequestBody
        @Valid
        com.fastjrun.demo.packet.app.LoginRestRequestBody requestBody) {
        BaseAppRequest<com.fastjrun.demo.packet.app.LoginRestRequestBody> request = new BaseAppRequest<com.fastjrun.demo.packet.app.LoginRestRequestBody>();
        BaseAppRequestHead requestHead = new BaseAppRequestHead();
        requestHead.setAppKey(appKey);
        requestHead.setAppVersion(appVersion);
        requestHead.setAppSource(appSource);
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(txTime);
        this.processHead(requestHead);
        request.setHead(requestHead);
        request.setBody(requestBody);
        BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> response = this.userServiceRest.login(request);
        log.debug(response);
        return response;
    }

    @RequestMapping(value = "login/v1_1/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> loginv1_1(
        @PathVariable("appKey")
        String appKey,
        @PathVariable("appVersion")
        String appVersion,
        @PathVariable("appSource")
        String appSource,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("txTime")
        Long txTime,
        @RequestBody
        @Valid
        com.fastjrun.demo.packet.app.LoginRestRequestBody requestBody) {
        BaseAppRequest<com.fastjrun.demo.packet.app.LoginRestRequestBody> request = new BaseAppRequest<com.fastjrun.demo.packet.app.LoginRestRequestBody>();
        BaseAppRequestHead requestHead = new BaseAppRequestHead();
        requestHead.setAppKey(appKey);
        requestHead.setAppVersion(appVersion);
        requestHead.setAppSource(appSource);
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(txTime);
        this.processHead(requestHead);
        request.setHead(requestHead);
        request.setBody(requestBody);
        BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> response = this.userServiceRest.loginv1_1(request);
        log.debug(response);
        return response;
    }

    @RequestMapping(value = "autoLogin/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> autoLogin(
        @PathVariable("appKey")
        String appKey,
        @PathVariable("appVersion")
        String appVersion,
        @PathVariable("appSource")
        String appSource,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("txTime")
        Long txTime,
        @RequestBody
        @Valid
        AutoLoginRestRequestBody requestBody) {
        BaseAppRequest<AutoLoginRestRequestBody> request = new BaseAppRequest<AutoLoginRestRequestBody>();
        BaseAppRequestHead requestHead = new BaseAppRequestHead();
        requestHead.setAppKey(appKey);
        requestHead.setAppVersion(appVersion);
        requestHead.setAppSource(appSource);
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(txTime);
        this.processHead(requestHead);
        request.setHead(requestHead);
        request.setBody(requestBody);
        BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> response = this.userServiceRest.autoLogin(request);
        log.debug(response);
        return response;
    }

}
