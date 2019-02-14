/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.executor;

import javax.annotation.Resource;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fastjrun.executor.BaseSimpleJobExecutor;
import com.fastjrun.test.AbstractAdVancedTestNGSpringContextTest;

public class InvalidLoginCredentialExecutorTest extends AbstractAdVancedTestNGSpringContextTest {

    @Resource(name = "invalidLoginCredentialExecutor")
    BaseSimpleJobExecutor invalidLoginCredentialExecutor;

    @BeforeTest
    @org.testng.annotations.Parameters({
            "envName"
    })
    protected void init(String envName) {
        this.initParam(envName);
    }

    @Test(priority = 1, dataProvider = "loadParam")
    public void testExecute(String reqParamsJsonStrAndAssert) {
        this.invalidLoginCredentialExecutor.execute();
    }
}
