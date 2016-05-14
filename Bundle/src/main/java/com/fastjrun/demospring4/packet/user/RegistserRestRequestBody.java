
package com.fastjrun.demospring4.packet.user;

import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRestRequestBody;


/**
 * 
 * @author robin
 */
public class RegistserRestRequestBody
    extends BaseRestRequestBody
{

    /**
     * 登录名
     * 
     */
    @NotNull(message = "loginId is null")
    private String loginId;
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
    @NotNull(message = "nickName is null")
    private String nickName;
    /**
     * 性别
     * 
     */
    @NotNull(message = "sex is null")
    private String sex;
    /**
     * 手机号
     * 
     */
    @NotNull(message = "mobileNo is null")
    private String mobileNo;
    /**
     * 手机号
     * 
     */
    @NotNull(message = "email is null")
    private String email;

    public String getLoginId() {
        return this.loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

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
