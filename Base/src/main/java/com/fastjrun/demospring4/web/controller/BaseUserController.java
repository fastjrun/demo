
package com.fastjrun.demospring4.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.fastjrun.demospring4.bean.User;
import com.fastjrun.demospring4.service.BaseUserService;
import com.fastjrun.helper.RestHelper;
import com.fastjrun.packet.req.CommonRequestId;
import com.fastjrun.packet.req.RequestHead;
import com.fastjrun.packet.res.RestResult;
import com.fastjrun.web.controller.BaseController;
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
    extends BaseController
{

    @Autowired
    private BaseUserService baseUserService;

    @RequestMapping(value = "add/10/{deviceId}/{reqTime}/", method = RequestMethod.POST)
    public Object add(
        @RequestBody
        @Valid
        User user,
        @PathVariable("deviceId")
        String deviceId,
        @PathVariable("reqTime")
        String reqTime, HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathS = requestURI.split("/");
        String ver = pathS[ 3 ];
        this.log.info(user);
        String beanName = pathS[ 4 ];
        RequestHead head = new RequestHead();
        head.setTxType((beanName +"add"));
        head.setVersion(ver);
        head.setDeviceId(deviceId);
        head.setReqTime(reqTime);
        this.processHead(head);
        baseUserService.insert(user);
        RestResult restResult = RestHelper.getSuccessResult();
        return restResult;
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
        User user = baseUserService.selectById(requestId.getId());
        RestResult restResult = RestHelper.getSuccessResult();
        restResult.bput("bean", user);
        return restResult;
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
        baseUserService.deleteById(requestId.getId());
        RestResult restResult = RestHelper.getSuccessResult();
        return restResult;
    }

    @RequestMapping(value = "edit/10/{deviceId}/{reqTime}/", method = RequestMethod.POST)
    public Object updateById(
        @RequestBody
        @Valid
        User user,
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
        baseUserService.updateById(user);
        RestResult restResult = RestHelper.getSuccessResult();
        return restResult;
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
        int totalCount = baseUserService.totalCount();
        List<User> list = baseUserService.queryForLimitList(rowBounds);
        RestResult restResult = RestHelper.getSuccessResult();
        restResult.bput("totalCount", totalCount);
        if (list!= null) {
            restResult.bput("list", list);
        }
        return restResult;
    }

}
