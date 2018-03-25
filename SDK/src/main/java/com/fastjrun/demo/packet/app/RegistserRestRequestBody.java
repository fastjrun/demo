
package com.fastjrun.demo.packet.app;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRequestBody;


/**
 * 
 * @author fastjrun
 */
public class RegistserRestRequestBody
    extends BaseRequestBody
    implements Serializable
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
    private String nickName;
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
     * 手机号
     * 
     */
    private String email;
    private final static long serialVersionUID = 450711736L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(("RegistserRestRequestBody"+" ["));
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
