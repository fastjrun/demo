
package com.fastjrun.enhanced.mock.web.controller;

import javax.validation.Valid;
import com.fastjrun.demo.packet.app.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.app.RegistserRestRequestBody;
import com.fastjrun.demo.service.UserServiceRest;
import com.fastjrun.packet.BaseAppRequest;
import com.fastjrun.packet.BaseAppRequestHead;
import com.fastjrun.packet.BaseDefaultResponseBody;
import com.fastjrun.packet.BaseResponse;
import com.fastjrun.web.controller.BaseAppController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "\u6d4b\u8bd5App\u63a5\u53e3", tags = "")
public class UserRestController
    extends BaseAppController
{

    @Autowired
    private UserServiceRest userServiceRest;

    @RequestMapping(value = "register/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "\u6ce8\u518c", notes = "\u6ce8\u518c")
    public BaseResponse<BaseDefaultResponseBody> register(
        @PathVariable("appKey")
        @ApiParam(name = "appKey", value = "app\u5206\u914d\u7684key", required = true)
        String appKey,
        @PathVariable("appVersion")
        @ApiParam(name = "appVersion", value = "\u5f53\u524dapp\u7248\u672c\u53f7", required = true)
        String appVersion,
        @PathVariable("appSource")
        @ApiParam(name = "appSource", value = "\u5f53\u524dapp\u6e20\u9053\uff1aios,android", required = true)
        String appSource,
        @PathVariable("deviceId")
        @ApiParam(name = "deviceId", value = "\u8bbe\u5907Id", required = true)
        String deviceId,
        @PathVariable("txTime")
        @ApiParam(name = "txTime", value = "\u63a5\u53e3\u8bf7\u6c42\u65f6\u95f4\u6233", required = true)
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
    @ApiOperation(value = "\u767b\u5f55", notes = "\u767b\u5f55")
    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> login(
        @PathVariable("appKey")
        @ApiParam(name = "appKey", value = "app\u5206\u914d\u7684key", required = true)
        String appKey,
        @PathVariable("appVersion")
        @ApiParam(name = "appVersion", value = "\u5f53\u524dapp\u7248\u672c\u53f7", required = true)
        String appVersion,
        @PathVariable("appSource")
        @ApiParam(name = "appSource", value = "\u5f53\u524dapp\u6e20\u9053\uff1aios,android", required = true)
        String appSource,
        @PathVariable("deviceId")
        @ApiParam(name = "deviceId", value = "\u8bbe\u5907Id", required = true)
        String deviceId,
        @PathVariable("txTime")
        @ApiParam(name = "txTime", value = "\u63a5\u53e3\u8bf7\u6c42\u65f6\u95f4\u6233", required = true)
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
    @ApiOperation(value = "\u767b\u5f55v1.1", notes = "\u767b\u5f55v1.1")
    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> loginv1_1(
        @PathVariable("appKey")
        @ApiParam(name = "appKey", value = "app\u5206\u914d\u7684key", required = true)
        String appKey,
        @PathVariable("appVersion")
        @ApiParam(name = "appVersion", value = "\u5f53\u524dapp\u7248\u672c\u53f7", required = true)
        String appVersion,
        @PathVariable("appSource")
        @ApiParam(name = "appSource", value = "\u5f53\u524dapp\u6e20\u9053\uff1aios,android", required = true)
        String appSource,
        @PathVariable("deviceId")
        @ApiParam(name = "deviceId", value = "\u8bbe\u5907Id", required = true)
        String deviceId,
        @PathVariable("txTime")
        @ApiParam(name = "txTime", value = "\u63a5\u53e3\u8bf7\u6c42\u65f6\u95f4\u6233", required = true)
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
    @ApiOperation(value = "\u81ea\u52a8\u767b\u5f55", notes = "\u81ea\u52a8\u767b\u5f55")
    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> autoLogin(
        @PathVariable("appKey")
        @ApiParam(name = "appKey", value = "app\u5206\u914d\u7684key", required = true)
        String appKey,
        @PathVariable("appVersion")
        @ApiParam(name = "appVersion", value = "\u5f53\u524dapp\u7248\u672c\u53f7", required = true)
        String appVersion,
        @PathVariable("appSource")
        @ApiParam(name = "appSource", value = "\u5f53\u524dapp\u6e20\u9053\uff1aios,android", required = true)
        String appSource,
        @PathVariable("deviceId")
        @ApiParam(name = "deviceId", value = "\u8bbe\u5907Id", required = true)
        String deviceId,
        @PathVariable("txTime")
        @ApiParam(name = "txTime", value = "\u63a5\u53e3\u8bf7\u6c42\u65f6\u95f4\u6233", required = true)
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
