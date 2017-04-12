
package com.fastjrun.demo.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.fastjrun.demo.bean.UserLogin;
import com.fastjrun.demo.service.BaseUserLoginService;
import com.fastjrun.helper.RestResponseHelper;
import com.fastjrun.packet.BaseRestBeanListResponseBody;
import com.fastjrun.packet.BaseRestBeanResponseBody;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestResponse;
import com.fastjrun.packet.BaseRestResponseHead;
import com.fastjrun.packet.req.CommonRequestId;
import com.fastjrun.packet.req.RequestHead;
import com.fastjrun.web.controller.BaseController;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * This is the controller to table 用户登录凭证表.
 * 
 * @author fastjrun
 */
@RestController
@RequestMapping("/base/userLogin/")
public class BaseUserLoginController
    extends BaseController
{

    @Autowired
    private BaseUserLoginService baseUserLoginService;

    @RequestMapping(value = "add/10/{deviceId}/{reqTime}/", method = RequestMethod.POST)
    public Object add(
        @RequestBody
        @Valid
        UserLogin userLogin,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[ 3 ];
        this.log.info(userLogin);
        String beanName = pathS[ 4 ];
        RequestHead head = new RequestHead();
        head.setTxType((beanName +"add"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        baseUserLoginService.insert(userLogin);
        BaseRestResponse<BaseRestDefaultResponseBody> response = RestResponseHelper.getSuccessResult();
        return response;
    }

    @RequestMapping(value = "view/10/{deviceId}/{reqTime}/", method = RequestMethod.GET)
    public Object selectById(
        @RequestBody
        @Valid
        CommonRequestId requestId,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[ 3 ];
        String beanName = pathS[ 4 ];
        RequestHead head = new RequestHead();
        head.setTxType((beanName +"view"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        UserLogin userLogin = baseUserLoginService.selectById(requestId.getId());
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        BaseRestBeanResponseBody responseBody = new BaseRestBeanResponseBody();
        BaseRestResponse<BaseRestBeanResponseBody> response = new BaseRestResponse<BaseRestBeanResponseBody>();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        responseBody.setAbstractEntity(userLogin);
        response.setBody(responseBody);
        return response;
    }

    @RequestMapping(value = "delete/10/{deviceId}/{reqTime}/", method = RequestMethod.POST)
    public Object deleteById(
        @RequestBody
        @Valid
        CommonRequestId requestId,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[ 3 ];
        String beanName = pathS[ 4 ];
        RequestHead head = new RequestHead();
        head.setTxType((beanName +"delete"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        baseUserLoginService.deleteById(requestId.getId());
        BaseRestResponse<BaseRestDefaultResponseBody> response = RestResponseHelper.getSuccessResult();
        return response;
    }

    @RequestMapping(value = "edit/10/{deviceId}/{reqTime}/", method = RequestMethod.POST)
    public Object updateById(
        @RequestBody
        @Valid
        UserLogin userLogin,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[ 3 ];
        String beanName = pathS[ 4 ];
        RequestHead head = new RequestHead();
        head.setTxType((beanName +"edit"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        baseUserLoginService.updateById(userLogin);
        BaseRestResponse<BaseRestDefaultResponseBody> response = RestResponseHelper.getSuccessResult();
        return response;
    }

    @RequestMapping(value = "list/10/{deviceId}/{reqTime}/", method = RequestMethod.POST)
    public Object queryForList(
        @RequestBody
        @Valid
        RowBounds rowBounds,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[ 3 ];
        String beanName = pathS[ 4 ];
        RequestHead head = new RequestHead();
        head.setTxType((beanName +"list"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        int totalCount = baseUserLoginService.totalCount();
        List<UserLogin> list = baseUserLoginService.queryForLimitList(rowBounds);
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        BaseRestBeanListResponseBody<UserLogin> responseBody = new BaseRestBeanListResponseBody<UserLogin>();
        BaseRestResponse<BaseRestBeanListResponseBody<UserLogin>> response = new BaseRestResponse<BaseRestBeanListResponseBody<UserLogin>>();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        responseBody.setTotalCount(totalCount);
        if (list!= null) {
            responseBody.setList(list);
        }
        response.setBody(responseBody);
        return response;
    }

}
