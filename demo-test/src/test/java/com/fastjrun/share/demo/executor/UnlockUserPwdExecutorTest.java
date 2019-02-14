/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.executor;

import javax.annotation.Resource;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fastjrun.executor.BaseSimpleJobExecutor;
import com.fastjrun.test.AbstractAdVancedTestNGSpringContextTest;

public class UnlockUserPwdExecutorTest extends AbstractAdVancedTestNGSpringContextTest {
    @Resource(name = "unlockUserPwdExecutor")
    BaseSimpleJobExecutor unlockUserPwdExecutor;

    @BeforeTest
    @org.testng.annotations.Parameters({
            "envName"
    })
    protected void init(String envName) {
        this.initParam(envName);
    }

    @Test(dataProvider = "loadParam",priority = 2)
    public void testExecute(String reqParamsJsonStrAndAssert) {
        this.unlockUserPwdExecutor.execute();
    }
}
