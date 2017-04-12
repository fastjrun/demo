
package com.fastjrun.demo.packet.user;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRestResponseBody;


/**
 * 
 * @author robin
 */
public class AutoLoginRestResponseBody
    extends BaseRestResponseBody
    implements Serializable
{

    /**
     * 新登录凭证
     * 
     */
    @NotNull(message = "uuidNew is null")
    private java.lang.String uuidNew;
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
     * 邮箱
     * 
     */
    @NotNull(message = "email is null")
    private java.lang.String email;
    private final static long serialVersionUID = 3222681896L;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("packet.user.AutoLoginRestResponseBody"+" ["));
        sb.append("field [");
        sb.append("uuidNew");
        sb.append("=");
        sb.append(this.uuidNew);
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

    public java.lang.String getUuidNew() {
        return this.uuidNew;
    }

    public void setUuidNew(java.lang.String uuidNew) {
        this.uuidNew = uuidNew;
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
