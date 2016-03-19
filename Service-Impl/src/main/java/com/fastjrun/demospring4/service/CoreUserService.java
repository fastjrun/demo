package com.fastjrun.demospring4.service;

import java.util.Date;

import com.fastjrun.demospring4.bean.User;

public interface CoreUserService {

    public void checkLoign(String userKey);

    public User autoLogin(String deviceId,String uuidOld,String uuidNew);

    public User login(String loginName, String loginPwd, String deviceId,String uuid);

    public void logOut(String uuid, String deviceId);

    public User getRedisUser(String uuid,String deviceId);

    public void setRedisUser(User user, String uuid,String deviceId);    
    
    public void unlockUserPwd(Date date);

    public void inValidUserLoginCredential(Date date);

}
