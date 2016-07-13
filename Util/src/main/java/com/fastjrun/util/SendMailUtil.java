package com.fastjrun.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailUtil {
    private Log log = LogFactory.getLog(this.getClass());

    private JavaMailSender javaMailSender;
    private String from;

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * 发送的文本测试邮件
     * 
     * @param fromNickName
     * @param to
     * @param mailSubject
     * @param mailBody
     */
    public void sendTextMail(String fromNickName, String to, String mailSubject, String mailBody) {
        SimpleMailMessage mail = new SimpleMailMessage();
        String nickNameEncode = fromNickName;
        try {
            nickNameEncode = MimeUtility.encodeText(fromNickName);
        } catch (UnsupportedEncodingException e) {
            log.warn(fromNickName + " can not be encoded", e);
        }

        mail.setFrom(nickNameEncode + this.from);
        mail.setTo(to);// 收件人邮箱
        mail.setSubject(mailSubject);// 邮件主题
        mail.setSentDate(new Date());// 邮件发送时间
        mail.setText(mailBody);

        // 群发
        SimpleMailMessage[] mailMessages = { mail };
        this.javaMailSender.send(mailMessages);

        if (log.isDebugEnabled())
            log.debug("文本形式的邮件发送成功！！！");
    }

    /**
     * 发送html的邮件
     * 
     * 
     * @param fromNickName
     * @param to
     * @param mailSubject
     * @param mailBody
     * @throws MessagingException
     */
    public void sendHtmlMail(String fromNickName, String to, String mailSubject, String mailBody)
            throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        String nickNameEncode = fromNickName;
        try {
            nickNameEncode = MimeUtility.encodeText(fromNickName);
        } catch (UnsupportedEncodingException e) {
            log.warn(fromNickName + " can not be encoded", e);
        }

        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
        helper.setFrom(nickNameEncode + this.from);
        helper.setTo(to);
        helper.setText(mailBody, true);
        helper.setSubject(mailSubject);
        helper.setSentDate(new Date());
        javaMailSender.send(msg);
    }

    /**
     * 发送添加附件的邮件
     * 
     * 
     * @param fromNickName
     * @param to
     * @param mailSubject
     * @param mailBody
     * @param attachMentFilePath
     *            附件（绝对路径）
     * @throws MessagingException
     */
    public void sendAttachMentMail(String fromNickName, String to, String mailSubject, String mailBody,
            String attachMentFilePath) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        String nickNameEncode = fromNickName;
        try {
            nickNameEncode = MimeUtility.encodeText(fromNickName);
        } catch (UnsupportedEncodingException e) {
            log.warn(fromNickName + " can not be encoded", e);
        }
        // 夹带附件
        File attachMentFile = new File(attachMentFilePath);
        FileSystemResource file = new FileSystemResource(attachMentFile);

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(nickNameEncode + this.from);
        helper.setTo(to);
        helper.setText(mailBody);
        helper.setSubject(mailSubject);
        helper.setSentDate(new Date());
        helper.addAttachment(attachMentFile.getName(), file);
        javaMailSender.send(msg);
    }
}
