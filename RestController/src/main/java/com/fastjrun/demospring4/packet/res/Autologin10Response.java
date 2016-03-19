
package com.fastjrun.demospring4.packet.res;



/**
 * This is the packet for res for autologin:10
 * 
 * @author fastjrun
 */
public class Autologin10Response {

    /**
     * 新登录凭证
     * 
     */
    private String uuidNew;
    /**
     * 昵称
     * 
     */
    private String nickName;
    /**
     * 登录名
     * 
     */
    private String loginName;
    /**
     * 性别
     * 
     */
    private String sex;
    /**
     * 手机号
     * 
     */
    private String mobileNo;
    /**
     * 邮箱
     * 
     */
    private String email;

    public String getUuidNew() {
        return this.uuidNew;
    }

    public void setUuidNew(String uuidNew) {
        this.uuidNew = uuidNew;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
