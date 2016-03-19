
package com.fastjrun.demospring4.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.fastjrun.demospring4.bean.UserLogin;
import com.fastjrun.demospring4.service.BaseUserLoginService;
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
        UserLogin userLogin = baseUserLoginService.selectById(requestId.getId());
        RestResult restResult = RestHelper.getSuccessResult();
        restResult.bput("bean", userLogin);
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
        baseUserLoginService.deleteById(requestId.getId());
        RestResult restResult = RestHelper.getSuccessResult();
        return restResult;
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
        int totalCount = baseUserLoginService.totalCount();
        List<UserLogin> list = baseUserLoginService.queryForLimitList(rowBounds);
        RestResult restResult = RestHelper.getSuccessResult();
        restResult.bput("totalCount", totalCount);
        if (list!= null) {
            restResult.bput("list", list);
        }
        return restResult;
    }

}
