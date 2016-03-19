
package com.fastjrun.demospring4.packet.req;

import javax.validation.constraints.NotNull;


/**
 * This is the packet for req for register:10
 * 
 * @author fastjrun
 */
public class Register10Request {

    /**
     * 密码
     * 
     */
    @NotNull(message = "loginpwd is null")
    private String loginpwd;
    /**
     * 昵称
     * 
     */
    private String nickName;
    /**
     * 登录名
     * 
     */
    @NotNull(message = "loginName is null")
    private String loginName;
    /**
     * 性别
     * 
     */
    private Integer sex;
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

    public String getLoginpwd() {
        return this.loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
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

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
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
