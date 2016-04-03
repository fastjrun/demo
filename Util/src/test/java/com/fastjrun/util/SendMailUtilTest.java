package com.fastjrun.util;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.fastjrun.test.BaseSpringTestNGTest;

public class SendMailUtilTest extends BaseSpringTestNGTest {

    @Autowired
    SendMailUtil sendMailUtil;

    @Test
    public void sendTextMail() {
        String to = "test2@qq.com";
        String mailSubject = "test";
        String mailBody = "正文";
        sendMailUtil.sendTextMail("你好", to, mailSubject, mailBody);
    }

    @Test
    public void sendAttachMentMail() {
        String to = "test2@qq.com";
        String mailSubject = "test";
        String mailBody = "maven正文";
        String from = "正文";
        try {
            sendMailUtil.sendAttachMentMail(from, to, mailSubject, mailBody,
                    "pom.xml");
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
