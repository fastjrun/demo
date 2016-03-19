
package com.fastjrun.demospring4.bean;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * This is the entity to table t_user.
 * 
 * @author fastjrun
 */
public class User
    implements Serializable
{

    /**
     * 密码
     * 
     */
    private String loginPwd;
    /**
     * 昵称<br/>
     * 昵称
     * 
     */
    private String nickName;
    /**
     * 性别<br/>
     * 性别
     * 
     */
    private Integer sex;
    /**
     * 手机号<br/>
     * 手机号
     * 
     */
    private String mobileNo;
    /**
     * 登陆错误次数
     * 
     */
    private Integer loginErrCount;
    /**
     * 上次登录时间
     * 
     */
    private String lastLoginTime;
    /**
     * 创建时间
     * 
     */
    private Timestamp createTime;
    /**
     * 登录账号
     * 
     */
    private String loginName;
    /**
     * 上次修改时间
     * 
     */
    private Timestamp lastModifyTime;
    /**
     * id<br/>
     * id
     * 
     */
    private Integer id;
    /**
     * 邮件<br/>
     * 邮件
     * 
     */
    private String email;
    /**
     * 上次记录登录错误时间
     * 
     */
    private String lastRecordLoginErrTime;
    /**
     * 状态<br/>
     * 状态
     * 
     */
    private String status;
    private final static long serialVersionUID = 10902199795L;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("User"+" ["));
        sb.append("loginPwd");
        sb.append("=");
        sb.append(this.loginPwd);
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
        sb.append("loginErrCount");
        sb.append("=");
        sb.append(this.loginErrCount);
        sb.append(",");
        sb.append("lastLoginTime");
        sb.append("=");
        sb.append(this.lastLoginTime);
        sb.append(",");
        sb.append("createTime");
        sb.append("=");
        sb.append(this.createTime);
        sb.append(",");
        sb.append("loginName");
        sb.append("=");
        sb.append(this.loginName);
        sb.append(",");
        sb.append("lastModifyTime");
        sb.append("=");
        sb.append(this.lastModifyTime);
        sb.append(",");
        sb.append("id");
        sb.append("=");
        sb.append(this.id);
        sb.append(",");
        sb.append("email");
        sb.append("=");
        sb.append(this.email);
        sb.append(",");
        sb.append("lastRecordLoginErrTime");
        sb.append("=");
        sb.append(this.lastRecordLoginErrTime);
        sb.append(",");
        sb.append("status");
        sb.append("=");
        sb.append(this.status);
        sb.append("]");
        return sb.toString();
    }

    public String getLoginPwd() {
        return this.loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Integer getLoginErrCount() {
        return this.loginErrCount;
    }

    public void setLoginErrCount(Integer loginErrCount) {
        this.loginErrCount = loginErrCount;
    }

    public String getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Timestamp getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(Timestamp lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastRecordLoginErrTime() {
        return this.lastRecordLoginErrTime;
    }

    public void setLastRecordLoginErrTime(String lastRecordLoginErrTime) {
        this.lastRecordLoginErrTime = lastRecordLoginErrTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
