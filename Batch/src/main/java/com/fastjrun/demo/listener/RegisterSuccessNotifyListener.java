package com.fastjrun.demo.listener;

import com.fastjrun.demo.bean.User;

public class RegisterSuccessNotifyListener extends BaseListener {
    public void processUser(User user) {
        log.info(" user :" + user);
    }

}
