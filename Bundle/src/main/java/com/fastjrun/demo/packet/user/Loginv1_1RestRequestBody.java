
package com.fastjrun.demo.packet.user;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRestRequestBody;


/**
 * 
 * @author robin
 */
public class Loginv1_1RestRequestBody
    extends BaseRestRequestBody
    implements Serializable
{

    /**
     * 密码
     * 
     */
    @NotNull(message = "loginpwd is null")
    private java.lang.String loginpwd;
    /**
     * 登录名
     * 
     */
    @NotNull(message = "loginName is null")
    private java.lang.String loginName;
    private final static long serialVersionUID = -1632292336L;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("packet.user.Loginv1_1RestRequestBody"+" ["));
        sb.append("field [");
        sb.append("loginpwd");
        sb.append("=");
        sb.append(this.loginpwd);
        sb.append(",");
        sb.append("loginName");
        sb.append("=");
        sb.append(this.loginName);
        sb.append("]");
        sb.append("]");
        return sb.toString();
    }

    public java.lang.String getLoginpwd() {
        return this.loginpwd;
    }

    public void setLoginpwd(java.lang.String loginpwd) {
        this.loginpwd = loginpwd;
    }

    public java.lang.String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(java.lang.String loginName) {
        this.loginName = loginName;
    }

}
