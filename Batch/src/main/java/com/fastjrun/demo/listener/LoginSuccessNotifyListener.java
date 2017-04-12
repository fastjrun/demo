package com.fastjrun.demo.listener;

import com.fastjrun.demo.bean.User;

public class LoginSuccessNotifyListener extends BaseListener {

    public void process(User user) {
        log.info(" user :" + user);
    }

}
