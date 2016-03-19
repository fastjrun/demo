package com.fastjrun.demospring4.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.fastjrun.common.TaskException;
import com.fastjrun.demospring4.service.UserServiceTask;
import com.fastjrun.task.BaseTask;

public class InvalidLoginCredentialTask extends BaseTask {

    @Autowired
    UserServiceTask userService;

    @Override
    public void process() throws TaskException {
        log.info("UnLockUserPwd Task starts");
        Date now = new Date();
        this.userService.inValidUserLoginCredential(now);
        log.info("UnLockUserPwd Task ends");

    }

}
