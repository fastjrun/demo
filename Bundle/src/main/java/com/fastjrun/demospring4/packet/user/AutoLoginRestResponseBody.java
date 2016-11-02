
package com.fastjrun.demospring4.packet.user;

import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRestResponseBody;


/**
 * 
 * @author robin
 */
public class AutoLoginRestResponseBody
    extends BaseRestResponseBody
{

    /**
     * 新登录凭证
     * 
     */
    @NotNull(message = "uuidNew is null")
    private String uuidNew;
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
     * 邮箱
     * 
     */
    @NotNull(message = "email is null")
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
