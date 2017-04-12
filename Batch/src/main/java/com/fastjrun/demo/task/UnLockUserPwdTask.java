package com.fastjrun.demo.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.fastjrun.common.TaskException;
import com.fastjrun.demo.service.UserServiceTask;
import com.fastjrun.task.BaseTask;

public class UnLockUserPwdTask extends BaseTask {

    @Autowired
    UserServiceTask userServiceTest;

    @Override
    public void process() throws TaskException {
        log.info("UnLockUserPwd Task starts");
        Date now = new Date();
        this.userServiceTest.unlockUserPwd(now);
        log.info("UnLockUserPwd Task ends");

    }

}
