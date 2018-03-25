
package com.fastjrun.demo.web.controller;

import java.util.List;
import javax.validation.Valid;
import com.fastjrun.demo.bean.User;
import com.fastjrun.demo.service.BaseUserService;
import com.fastjrun.packet.BaseAppRequestHead;
import com.fastjrun.packet.BaseDefaultResponseBody;
import com.fastjrun.packet.BaseResponse;
import com.fastjrun.packet.BaseResponseBodyBean;
import com.fastjrun.packet.BaseResponseBodyBeanList;
import com.fastjrun.packet.BaseResponseHead;
import com.fastjrun.web.controller.BaseAppController;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * This is the controller to table 用户表.
 * 
 * @author fastjrun
 */
@RestController
@RequestMapping("/base/user/")
public class BaseUserController
    extends BaseAppController
{

    @Autowired
    private BaseUserService baseUserService;

    @RequestMapping(value = "add/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<BaseDefaultResponseBody> add(
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
        User user) {
        BaseAppRequestHead requestHead = new BaseAppRequestHead();
        requestHead.setAppKey(appKey);
        requestHead.setAppVersion(appVersion);
        requestHead.setAppSource(appSource);
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(txTime);
        this.processHead(requestHead);
        baseUserService.insert(user);
        BaseResponseHead responseHead = new BaseResponseHead();
        BaseResponse<BaseDefaultResponseBody> response = new BaseResponse<BaseDefaultResponseBody>();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        return response;
    }

    @RequestMapping(value = "view/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<BaseResponseBodyBean> selectByPK(
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
        JSONObject requestPK) {
        BaseAppRequestHead requestHead = new BaseAppRequestHead();
        requestHead.setAppKey(appKey);
        requestHead.setAppVersion(appVersion);
        requestHead.setAppSource(appSource);
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(txTime);
        this.processHead(requestHead);
        Integer id = Integer.valueOf(requestPK.getInt("id"));
        User user = baseUserService.selectByPK(id);
        BaseResponseBodyBean responseBody = new BaseResponseBodyBean();
        responseBody.setAbstractEntity(user);
        BaseResponseHead responseHead = new BaseResponseHead();
        BaseResponse<BaseResponseBodyBean> response = new BaseResponse<BaseResponseBodyBean>();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        response.setBody(responseBody);
        return response;
    }

    @RequestMapping(value = "delete/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<BaseDefaultResponseBody> deleteByPK(
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
        JSONObject requestPK) {
        BaseAppRequestHead requestHead = new BaseAppRequestHead();
        requestHead.setAppKey(appKey);
        requestHead.setAppVersion(appVersion);
        requestHead.setAppSource(appSource);
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(txTime);
        this.processHead(requestHead);
        Integer id = Integer.valueOf(requestPK.getInt("id"));
        int res = baseUserService.deleteByPK(id);
        BaseResponseHead responseHead = new BaseResponseHead();
        BaseResponse<BaseDefaultResponseBody> response = new BaseResponse<BaseDefaultResponseBody>();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        return response;
    }

    @RequestMapping(value = "edit/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<BaseDefaultResponseBody> updateByPK(
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
        User user) {
        BaseAppRequestHead requestHead = new BaseAppRequestHead();
        requestHead.setAppKey(appKey);
        requestHead.setAppVersion(appVersion);
        requestHead.setAppSource(appSource);
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(txTime);
        this.processHead(requestHead);
        baseUserService.updateByPK(user);
        BaseResponseHead responseHead = new BaseResponseHead();
        BaseResponse<BaseDefaultResponseBody> response = new BaseResponse<BaseDefaultResponseBody>();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        return response;
    }

    @RequestMapping(value = "list/{appKey}/{appVersion}/{appSource}/{deviceId}/{txTime}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<BaseResponseBodyBeanList<User>> queryForList(
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
        RowBounds rowBounds) {
        BaseAppRequestHead requestHead = new BaseAppRequestHead();
        requestHead.setAppKey(appKey);
        requestHead.setAppVersion(appVersion);
        requestHead.setAppSource(appSource);
        requestHead.setDeviceId(deviceId);
        requestHead.setTxTime(txTime);
        this.processHead(requestHead);
        int totalCount = baseUserService.totalCount();
        List<User> list = baseUserService.queryForLimitList(rowBounds);
        BaseResponseBodyBeanList<User> responseBody = new BaseResponseBodyBeanList<User>();
        responseBody.setTotalCount(totalCount);
        if (list!= null) {
            responseBody.setList(list);
        }
        BaseResponseHead responseHead = new BaseResponseHead();
        BaseResponse<BaseResponseBodyBeanList<User>> response = new BaseResponse<BaseResponseBodyBeanList<User>>();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        response.setBody(responseBody);
        return response;
    }

}
