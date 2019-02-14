/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fastjrun.common.ServiceException;
import com.fastjrun.share.demo.packet.app.AutoLoginRestRequestBody;
import com.fastjrun.share.demo.packet.app.LoginRestRequestBody;
import com.fastjrun.share.demo.packet.app.LoginRestResponseBody;
import com.fastjrun.share.demo.packet.app.RegistserRestRequestBody;
import com.fastjrun.test.AbstractAdVancedTestNGSpringContextTest;
import com.fastjrun.utils.JacksonUtils;

public class UserServiceTest extends AbstractAdVancedTestNGSpringContextTest {

    @Autowired
    UserServiceRest userService;

    @BeforeTest
    @org.testng.annotations.Parameters({
            "envName"
    })
    protected void init(@Optional("unitTest") String envName) {
        this.initParam(envName);
    }

    @Test(dataProvider = "loadParam")
    public void testRegister(String reqParamsJsonStrAndAssert) {
        String[] reqParamsJsonStrAndAssertArray = reqParamsJsonStrAndAssert.split(",assert=");
        String reqParamsJsonStr = reqParamsJsonStrAndAssertArray[0];
        log.debug(reqParamsJsonStr);
        JsonNode reqParamsJson = JacksonUtils.toJsonNode(reqParamsJsonStr);

        RegistserRestRequestBody requestBody = null;
        JsonNode requestBodyjSon = reqParamsJson.get("requestBody");
        if (requestBodyjSon != null) {
            requestBody = JacksonUtils.readValue(requestBodyjSon.toString(), RegistserRestRequestBody.class);
        }
        JsonNode assertJson = null;
        if (reqParamsJsonStrAndAssertArray.length == 2) {
            assertJson = JacksonUtils.toJsonNode(reqParamsJsonStrAndAssertArray[1]);
        }

        if (assertJson != null) {
            JsonNode codeNode = assertJson.get("code");
            if (codeNode != null) {
                try {
                    userService.register(requestBody);
                } catch (ServiceException e) {
                    org.testng.Assert.assertEquals(e.getCode(), codeNode.asText(),
                            ("返回消息码不是指定消息码:" + codeNode
                                    .asText()));
                }
            } else {
                userService.register(requestBody);
            }
        } else {
            userService.register(requestBody);
        }
    }

    @Test(dataProvider = "loadParam")
    public void testAutoLogin(String reqParamsJsonStrAndAssert) {
        String[] reqParamsJsonStrAndAssertArray = reqParamsJsonStrAndAssert.split(",assert=");
        String reqParamsJsonStr = reqParamsJsonStrAndAssertArray[0];
        log.debug(reqParamsJsonStr);
        JsonNode reqParamsJson = JacksonUtils.toJsonNode(reqParamsJsonStr);
        String deviceId = null;
        JsonNode deviceIdjSon = reqParamsJson.get("deviceId");
        if (deviceIdjSon != null) {
            deviceId = deviceIdjSon.asText();
        }
        AutoLoginRestRequestBody requestBody = null;
        JsonNode requestBodyjSon = reqParamsJson.get("requestBody");
        if (requestBodyjSon != null) {
            requestBody = JacksonUtils.readValue(requestBodyjSon.toString(), AutoLoginRestRequestBody.class);
        }
        JsonNode assertJson = null;
        if (reqParamsJsonStrAndAssertArray.length == 2) {
            assertJson = JacksonUtils.toJsonNode(reqParamsJsonStrAndAssertArray[1]);
        }
        LoginRestResponseBody response = null;
        if (assertJson != null) {
            JsonNode codeNode = assertJson.get("code");
            if (codeNode != null) {
                try {
                    response = userService.autoLogin(requestBody, deviceId);
                } catch (ServiceException e) {
                    org.testng.Assert.assertEquals(e.getCode(), codeNode.asText(),
                            ("返回消息码不是指定消息码:" + codeNode
                                    .asText()));
                }
            } else {
                response = userService.autoLogin(requestBody, deviceId);
            }
        } else {
            response = userService.autoLogin(requestBody, deviceId);
        }
        log.info(response);
    }

    @Test(dataProvider = "loadParam")
    public void testLogin(String reqParamsJsonStrAndAssert) {
        String[] reqParamsJsonStrAndAssertArray = reqParamsJsonStrAndAssert.split(",assert=");
        String reqParamsJsonStr = reqParamsJsonStrAndAssertArray[0];
        log.debug(reqParamsJsonStr);
        JsonNode reqParamsJson = JacksonUtils.toJsonNode(reqParamsJsonStr);
        String deviceId = null;
        JsonNode deviceIdjSon = reqParamsJson.get("deviceId");
        if (deviceIdjSon != null) {
            deviceId = deviceIdjSon.asText();
        }
        LoginRestRequestBody requestBody = null;
        JsonNode requestBodyjSon = reqParamsJson.get("requestBody");
        if (requestBodyjSon != null) {
            requestBody = JacksonUtils.readValue(requestBodyjSon.toString(), LoginRestRequestBody.class);
        }
        JsonNode assertJson = null;
        if (reqParamsJsonStrAndAssertArray.length == 2) {
            assertJson = JacksonUtils.toJsonNode(reqParamsJsonStrAndAssertArray[1]);
        }
        LoginRestResponseBody response = null;
        if (assertJson != null) {
            JsonNode codeNode = assertJson.get("code");
            if (codeNode != null) {
                try {
                    userService.login(requestBody, deviceId);
                } catch (ServiceException e) {
                    org.testng.Assert.assertEquals(e.getCode(), codeNode.asText(),
                            ("返回消息码不是指定消息码:" + codeNode
                                    .asText()));
                }
            } else {
                response = userService.login(requestBody, deviceId);
            }
        } else {
            response = userService.login(requestBody, deviceId);
        }
        log.info(response);
    }
}
