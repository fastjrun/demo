package com.fastjrun.demo.service;

import java.util.Date;

import com.fastjrun.demo.bean.User;

public interface CoreUserService {

    public void checkLoign(String uuid,String deviceId);

    public User autoLogin(String deviceId,String uuidOld,String uuidNew);

    public User login(String loginName, String loginPwd, String deviceId,String uuid);

    public void logOut(String uuid, String deviceId);
    
    public void unlockUserPwd(Date date);

    public void inValidUserLoginCredential(Date date);

}
