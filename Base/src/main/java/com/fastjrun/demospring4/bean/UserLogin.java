
package com.fastjrun.demospring4.bean;

import java.io.Serializable;
import com.fastjrun.common.AbstractEntity;


/**
 * This is the entity to table t_user_login.
 * 
 * @author fastjrun
 */
public class UserLogin
    extends AbstractEntity
    implements Serializable
{

    /**
     * 创建时间
     * 
     */
    private String createTime;
    /**
     * 凭证注销时间<br/>
     * 凭证注销时间
     * 
     */
    private String logOutTime;
    /**
     * 登录凭证
     * 
     */
    private String loginCredential;
    /**
     * 凭证失效时间<br/>
     * 凭证失效时间
     * 
     */
    private String inValidateTime;
    /**
     * id<br/>
     * id
     * 
     */
    private Integer id;
    /**
     * 用户ID
     * 
     */
    private Integer userId;
    /**
     * 设备ID
     * 
     */
    private String deviceId;
    /**
     * 状态<br/>
     * 状态
     * 
     */
    private String status;
    private final static long serialVersionUID = 15579013036L;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("UserLogin"+" ["));
        sb.append("createTime");
        sb.append("=");
        sb.append(this.createTime);
        sb.append(",");
        sb.append("logOutTime");
        sb.append("=");
        sb.append(this.logOutTime);
        sb.append(",");
        sb.append("loginCredential");
        sb.append("=");
        sb.append(this.loginCredential);
        sb.append(",");
        sb.append("inValidateTime");
        sb.append("=");
        sb.append(this.inValidateTime);
        sb.append(",");
        sb.append("id");
        sb.append("=");
        sb.append(this.id);
        sb.append(",");
        sb.append("userId");
        sb.append("=");
        sb.append(this.userId);
        sb.append(",");
        sb.append("deviceId");
        sb.append("=");
        sb.append(this.deviceId);
        sb.append(",");
        sb.append("status");
        sb.append("=");
        sb.append(this.status);
        sb.append("]");
        return sb.toString();
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLogOutTime() {
        return this.logOutTime;
    }

    public void setLogOutTime(String logOutTime) {
        this.logOutTime = logOutTime;
    }

    public String getLoginCredential() {
        return this.loginCredential;
    }

    public void setLoginCredential(String loginCredential) {
        this.loginCredential = loginCredential;
    }

    public String getInValidateTime() {
        return this.inValidateTime;
    }

    public void setInValidateTime(String inValidateTime) {
        this.inValidateTime = inValidateTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
