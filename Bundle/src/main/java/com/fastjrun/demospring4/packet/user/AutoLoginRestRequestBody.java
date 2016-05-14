
package com.fastjrun.demospring4.packet.user;

import javax.validation.constraints.NotNull;
import com.fastjrun.packet.BaseRestRequestBody;


/**
 * 
 * @author robin
 */
public class AutoLoginRestRequestBody
    extends BaseRestRequestBody
{

    /**
     * 旧登录凭证
     * 
     */
    @NotNull(message = "uuidOld is null")
    private String uuidOld;

    public String getUuidOld() {
        return this.uuidOld;
    }

    public void setUuidOld(String uuidOld) {
        this.uuidOld = uuidOld;
    }

}
