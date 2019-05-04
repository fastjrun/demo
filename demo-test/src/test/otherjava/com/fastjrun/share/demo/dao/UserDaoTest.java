/*
 * Copyright (C) 2018 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fastjrun.share.demo.entity.User;
import com.fastjrun.test.AbstractAdVancedTestNGSpringContextTest;
import com.fastjrun.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class UserDaoTest extends AbstractAdVancedTestNGSpringContextTest {

    @Autowired
    UserDao userDao;

    @Test(dataProvider = "loadParam")
    @org.testng.annotations.Parameters({ "reqParamsJsonStrAndAssert" })
    public void testInsertNoStatusAndLastRecordLoginErrTime(String reqParamsJsonStrAndAssert) {
        JsonNode[] jsonNodes = parseStr2JsonArray(reqParamsJsonStrAndAssert);
        JsonNode paramsJson = jsonNodes[0];
        User user = null;
        if (paramsJson != null) {
            JsonNode userJson = paramsJson.get("user");
            user = JacksonUtils.readValue(userJson.toString(), User.class);
        }
        int res = userDao.insertNoStatusAndLastRecordLoginErrTime(user);
        log.info("res={}" + res);

    }

}
