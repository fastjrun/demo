
package com.fastjrun.demo.packet.app;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRequestBody;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * @author fastjrun
 */
public class LoginRestRequestBody
    extends BaseRequestBody
    implements Serializable
{

    /**
     * 密码
     * 
     */
    @NotNull(message = "loginpwd is null")
    @ApiModelProperty(value = "\u5bc6\u7801", required = true)
    private String loginpwd;
    /**
     * 登录名
     * 
     */
    @NotNull(message = "loginName is null")
    @ApiModelProperty(value = "\u767b\u5f55\u540d", required = true)
    private String loginName;
    private final static long serialVersionUID = 450711736L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(("LoginRestRequestBody"+" ["));
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
