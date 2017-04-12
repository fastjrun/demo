package com.fastjrun.demo.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fastjrun.demo.bean.User;
import com.fastjrun.demo.service.BaseUserService;
import com.fastjrun.helper.RestResponseHelper;
import com.fastjrun.packet.BaseRestBeanListResponseBody;
import com.fastjrun.packet.BaseRestBeanResponseBody;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestResponse;
import com.fastjrun.packet.BaseRestResponseHead;
import com.fastjrun.packet.req.CommonRequestId;
import com.fastjrun.packet.req.RequestHead;
import com.fastjrun.web.controller.BaseController;

@RestController
@RequestMapping("/spring/4/user/{deviceId}/{reqTime}/")
public class GreetingController extends BaseController {
    @Autowired
    private BaseUserService baseUserService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object add(@RequestBody @Valid User user, @PathVariable("deviceId") String deviceId,
            @PathVariable("reqTime") String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[3];
        this.log.info(user);
        String beanName = pathS[4];
        RequestHead head = new RequestHead();
        head.setTxType((beanName + "add"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        baseUserService.insert(user);
        BaseRestResponse<BaseRestDefaultResponseBody> response = new BaseRestResponse<BaseRestDefaultResponseBody>();
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        BaseRestDefaultResponseBody responseBody = new BaseRestDefaultResponseBody();
        responseHead.setCode("0000");
        responseHead.setMsg("ok");
        response.setHead(responseHead);
        response.setBody(responseBody);
        return response;
    }

    @RequestMapping(value = "view", method = RequestMethod.POST)
    public Object selectById(@RequestBody @Valid CommonRequestId requestId, @PathVariable("deviceId") String deviceId,
            @PathVariable("reqTime") String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[3];
        String beanName = pathS[4];
        RequestHead head = new RequestHead();
        head.setTxType((beanName + "view"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        User user = baseUserService.selectById(requestId.getId());
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        BaseRestBeanResponseBody responseBody = new BaseRestBeanResponseBody();
        BaseRestResponse<BaseRestBeanResponseBody> response = new BaseRestResponse<BaseRestBeanResponseBody>();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        responseBody.setAbstractEntity(user);
        response.setBody(responseBody);
        return response;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public Object updateById(@RequestBody @Valid User user, @PathVariable("deviceId") String deviceId,
            @PathVariable("reqTime") String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[3];
        String beanName = pathS[4];
        RequestHead head = new RequestHead();
        head.setTxType((beanName + "edit"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        baseUserService.updateById(user);
        BaseRestResponse<BaseRestDefaultResponseBody> response = RestResponseHelper.getSuccessResult();
        return response;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Object deleteById(@RequestBody @Valid CommonRequestId requestId, @PathVariable("deviceId") String deviceId,
            @PathVariable("reqTime") String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[3];
        String beanName = pathS[4];
        RequestHead head = new RequestHead();
        head.setTxType((beanName + "delete"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        baseUserService.deleteById(requestId.getId());
        BaseRestResponse<BaseRestDefaultResponseBody> response = RestResponseHelper.getSuccessResult();
        return response;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Object queryForList(@RequestBody @Valid RowBounds rowBounds, @PathVariable("deviceId") String deviceId,
            @PathVariable("reqTime") String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[3];
        String beanName = pathS[4];
        RequestHead head = new RequestHead();
        head.setTxType((beanName + "list"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        int totalCount = baseUserService.totalCount();
        List<User> list = baseUserService.queryForLimitList(rowBounds);
        BaseRestResponseHead responseHead = new BaseRestResponseHead();
        BaseRestBeanListResponseBody<User> responseBody = new BaseRestBeanListResponseBody<User>();
        BaseRestResponse<BaseRestBeanListResponseBody<User>> response = new BaseRestResponse<BaseRestBeanListResponseBody<User>>();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        responseBody.setTotalCount(totalCount);
        if (list != null) {
            responseBody.setList(list);
        }
        response.setBody(responseBody);
        return response;
    }

}
