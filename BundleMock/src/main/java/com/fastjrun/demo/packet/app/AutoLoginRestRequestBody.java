
package com.fastjrun.demo.packet.app;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRequestBody;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * @author fastjrun
 */
public class AutoLoginRestRequestBody
    extends BaseRequestBody
    implements Serializable
{

    /**
     * 旧登录凭证
     * 
     */
    @NotNull(message = "uuidOld is null")
    @ApiModelProperty(value = "\u65e7\u767b\u5f55\u51ed\u8bc1", required = true)
    private String uuidOld;
    private final static long serialVersionUID = 450711736L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(("AutoLoginRestRequestBody"+" ["));
        sb.append("field [");
        sb.append("uuidOld");
        sb.append("=");
        sb.append(this.uuidOld);
        sb.append("]");
        sb.append("]");
        return sb.toString();
    }

    public String getUuidOld() {
        return this.uuidOld;
    }

    public void setUuidOld(String uuidOld) {
        this.uuidOld = uuidOld;
    }

}
