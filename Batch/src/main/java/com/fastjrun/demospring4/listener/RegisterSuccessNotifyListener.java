package com.fastjrun.demospring4.listener;

import com.fastjrun.demospring4.bean.User;

public class RegisterSuccessNotifyListener extends BaseListener {
    public void processUser(User user) {
        log.info(" user :" + user);
    }

}
