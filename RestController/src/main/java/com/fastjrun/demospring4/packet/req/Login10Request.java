
package com.fastjrun.demospring4.packet.req;

import javax.validation.constraints.NotNull;


/**
 * This is the packet for req for login:10
 * 
 * @author fastjrun
 */
public class Login10Request {

    /**
     * 密码
     * 
     */
    @NotNull(message = "loginpwd is null")
    private String loginpwd;
    /**
     * 登录名
     * 
     */
    @NotNull(message = "loginName is null")
    private String loginName;

    public String getLoginpwd() {
        return this.loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

}
