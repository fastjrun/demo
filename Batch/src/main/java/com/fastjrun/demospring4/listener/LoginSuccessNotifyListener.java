package com.fastjrun.demospring4.listener;

import com.fastjrun.demospring4.bean.User;

public class LoginSuccessNotifyListener extends BaseListener {

    public void process(User user) {
        log.info(" user :" + user);
    }

}
