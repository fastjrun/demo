package com.fastjrun.demospring4.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RegisterSuccessNotifyListener extends BaseListener implements
        MessageListener {

    @Override
    public void onMessage(Message message) {
        log.info(" data :" + new String(message.getBody()));
    }

}
