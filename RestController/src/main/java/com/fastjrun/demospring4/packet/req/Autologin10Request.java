
package com.fastjrun.demospring4.packet.req;

import javax.validation.constraints.NotNull;


/**
 * This is the packet for req for autologin:10
 * 
 * @author fastjrun
 */
public class Autologin10Request {

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
