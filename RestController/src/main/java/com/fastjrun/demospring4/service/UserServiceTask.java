package com.fastjrun.demospring4.service;

import java.util.Date;


public interface UserServiceTask {

    /** 密码锁定解锁（普通用户） */
    public void unlockUserPwd(Date date);

    /** 设置过期登录凭证失效 */
    public void inValidUserLoginCredential(Date date);

}
