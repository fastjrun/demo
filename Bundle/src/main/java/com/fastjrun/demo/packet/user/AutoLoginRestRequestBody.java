
package com.fastjrun.demo.packet.user;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRestRequestBody;


/**
 * 
 * @author robin
 */
public class AutoLoginRestRequestBody
    extends BaseRestRequestBody
    implements Serializable
{

    /**
     * 旧登录凭证
     * 
     */
    @NotNull(message = "uuidOld is null")
    private java.lang.String uuidOld;
    private final static long serialVersionUID = -93568252L;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(("packet.user.AutoLoginRestRequestBody"+" ["));
        sb.append("field [");
        sb.append("uuidOld");
        sb.append("=");
        sb.append(this.uuidOld);
        sb.append("]");
        sb.append("]");
        return sb.toString();
    }

    public java.lang.String getUuidOld() {
        return this.uuidOld;
    }

    public void setUuidOld(java.lang.String uuidOld) {
        this.uuidOld = uuidOld;
    }

}
