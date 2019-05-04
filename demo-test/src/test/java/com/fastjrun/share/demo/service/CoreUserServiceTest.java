/*
 * Copyright (C) 2018 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fastjrun.common.ServiceException;
import com.fastjrun.share.demo.entity.User;
import com.fastjrun.test.AbstractAdVancedTestNGSpringContextTest;
import com.fastjrun.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class CoreUserServiceTest extends AbstractAdVancedTestNGSpringContextTest {

    @Autowired
    CoreUserService coreUserService;

    @Test(dataProvider = "loadParam")
    public void testCheckLogin(String reqParamsJsonStrAndAssert) {
        JsonNode[] jsonNodes = parseStr2JsonArray(reqParamsJsonStrAndAssert);
        JsonNode reqParamsJson = jsonNodes[ 0 ];
        JsonNode assertJson = jsonNodes[ 1 ];
        String uuid = null;
        JsonNode uuidjSon = reqParamsJson.get("uuid");
        if (uuidjSon != null) {
            uuid = uuidjSon.asText();
        }
        String deviceId = null;
        JsonNode deviceIdjSon = reqParamsJson.get("deviceId");
        if (deviceIdjSon != null) {
            deviceId = deviceIdjSon.asText();
        }
        if (assertJson != null) {
            JsonNode codeNode = assertJson.get("code");
            if (codeNode != null) {
                try {
                    coreUserService.checkLogin(uuid, deviceId);
                } catch (ServiceException e) {
                    org.testng.Assert.assertEquals(e.getCode(), codeNode.asText(),
                      ("返回消息码不是指定消息码:" + codeNode.asText()));
                }
            } else {
                coreUserService.checkLogin(uuid, deviceId);
            }
        } else {
            coreUserService.checkLogin(uuid, deviceId);
        }

    }

    @Test(dataProvider = "loadParam")
    public void testAutoLogin(String reqParamsJsonStrAndAssert) {
        JsonNode[] jsonNodes = parseStr2JsonArray(reqParamsJsonStrAndAssert);
        JsonNode reqParamsJson = jsonNodes[ 0 ];
        JsonNode assertJson = jsonNodes[ 1 ];
        String deviceId = null;
        JsonNode deviceIdjSon = reqParamsJson.get("deviceId");
        if (deviceIdjSon != null) {
            deviceId = deviceIdjSon.asText();
        }
        String uuidOld = null;
        JsonNode uuidOldjSon = reqParamsJson.get("uuidOld");
        if (uuidOldjSon != null) {
            uuidOld = uuidOldjSon.asText();
        }
        String uuidNew = null;
        JsonNode uuidNewjSon = reqParamsJson.get("uuidNew");
        if (uuidNewjSon != null) {
            uuidNew = uuidNewjSon.asText();
        }
        User response = null;
        if (assertJson != null) {
            JsonNode codeNode = assertJson.get("code");
            if (codeNode != null) {
                try {
                    coreUserService.autoLogin(deviceId, uuidOld, uuidNew);
                } catch (ServiceException e) {
                    org.testng.Assert.assertEquals(e.getCode(), codeNode.asText(),
                      ("返回消息码不是指定消息码:" + codeNode.asText()));
                }
            } else {
                response = coreUserService.autoLogin(deviceId, uuidOld, uuidNew);
            }
        } else {
            response = coreUserService.autoLogin(deviceId, uuidOld, uuidNew);
        }
        log.info("response={}" + response);
    }

    @Test(dataProvider = "loadParam")
    public void testLogin(String reqParamsJsonStrAndAssert) {
        JsonNode[] jsonNodes = parseStr2JsonArray(reqParamsJsonStrAndAssert);
        JsonNode reqParamsJson = jsonNodes[ 0 ];
        JsonNode assertJson = jsonNodes[ 1 ];
        String deviceId = null;
        JsonNode deviceIdjSon = reqParamsJson.get("deviceId");
        if (deviceIdjSon != null) {
            deviceId = deviceIdjSon.asText();
        }
        String loginName = null;
        JsonNode loginNamejSon = reqParamsJson.get("loginName");
        if (loginNamejSon != null) {
            loginName = loginNamejSon.asText();
        }
        String loginPwd = null;
        JsonNode loginPwdjSon = reqParamsJson.get("loginPwd");
        if (loginPwdjSon != null) {
            loginPwd = loginPwdjSon.asText();
        }
        String uuid = null;
        JsonNode uuidjSon = reqParamsJson.get("uuid");
        if (uuidjSon != null) {
            uuid = uuidjSon.asText();
        }
        User response = null;
        if (assertJson != null) {
            JsonNode codeNode = assertJson.get("code");
            if (codeNode != null) {
                try {
                    coreUserService.login(loginName, loginPwd, deviceId, uuid);
                } catch (ServiceException e) {
                    org.testng.Assert.assertEquals(e.getCode(), codeNode.asText(),
                      ("返回消息码不是指定消息码:" + codeNode.asText()));
                }
            } else {
                response = coreUserService.login(loginName, loginPwd, deviceId, uuid);
            }
        } else {
            response = coreUserService.login(loginName, loginPwd, deviceId, uuid);
        }
        log.info("response={}" + response);
    }
}
