
package com.fastjrun.demo.packet.user;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRestRequestBody;


/**
 * 
 * @author robin
 */
public class RegistserRestRequestBody
    extends BaseRestRequestBody
    implements Serializable
{

    /**
     * 登录名
     * 
     */
    @NotNull(message = "loginId is null")
    private java.lang.String loginId;
    /**
     * 密码
     * 
     */
    @NotNull(message = "loginpwd is null")
    private java.lang.String loginpwd;
    /**
     * 昵称
     * 
     */
    @NotNull(message = "nickName is null")
    private java.lang.String nickName;
    /**
     * 性别
     * 
     */
    @NotNull(message = "sex is null")
    private java.lang.String sex;
    /**
     * 手机号
     * 
     */
    @NotNull(message = "mobileNo is null")
    private java.lang.String mobileNo;
    /**
     * 手机号
     * 
     */
    @NotNull(message = "email is null")
    private java.lang.String email;
    private final static long serialVersionUID = 6055172665L;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("packet.user.RegistserRestRequestBody"+" ["));
        sb.append("field [");
        sb.append("loginId");
        sb.append("=");
        sb.append(this.loginId);
        sb.append(",");
        sb.append("loginpwd");
        sb.append("=");
        sb.append(this.loginpwd);
        sb.append(",");
        sb.append("nickName");
        sb.append("=");
        sb.append(this.nickName);
        sb.append(",");
        sb.append("sex");
        sb.append("=");
        sb.append(this.sex);
        sb.append(",");
        sb.append("mobileNo");
        sb.append("=");
        sb.append(this.mobileNo);
        sb.append(",");
        sb.append("email");
        sb.append("=");
        sb.append(this.email);
        sb.append("]");
        sb.append("]");
        return sb.toString();
    }

    public java.lang.String getLoginId() {
        return this.loginId;
    }

    public void setLoginId(java.lang.String loginId) {
        this.loginId = loginId;
    }

    public java.lang.String getLoginpwd() {
        return this.loginpwd;
    }

    public void setLoginpwd(java.lang.String loginpwd) {
        this.loginpwd = loginpwd;
    }

    public java.lang.String getNickName() {
        return this.nickName;
    }

    public void setNickName(java.lang.String nickName) {
        this.nickName = nickName;
    }

    public java.lang.String getSex() {
        return this.sex;
    }

    public void setSex(java.lang.String sex) {
        this.sex = sex;
    }

    public java.lang.String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(java.lang.String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public java.lang.String getEmail() {
        return this.email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

}
