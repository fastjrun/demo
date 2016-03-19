
package com.fastjrun.demospring4.service;

import com.fastjrun.demospring4.packet.req.Autologin10Request;
import com.fastjrun.demospring4.packet.req.Login10Request;
import com.fastjrun.demospring4.packet.req.Register10Request;
import com.fastjrun.packet.res.RestResult;


/**
 * This is the service for UserServiceRest.
 * 
 * @author fastjrun
 */
public interface UserServiceRest {


    public RestResult register10(Register10Request request, String deviceId);

    public RestResult login10(Login10Request request, String deviceId);

    public RestResult autologin10(Autologin10Request request, String deviceId);

}
