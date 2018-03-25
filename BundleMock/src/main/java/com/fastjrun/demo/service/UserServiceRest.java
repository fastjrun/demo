
package com.fastjrun.demo.service;

import com.fastjrun.demo.packet.app.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.app.RegistserRestRequestBody;
import com.fastjrun.packet.BaseAppRequest;
import com.fastjrun.packet.BaseDefaultResponseBody;
import com.fastjrun.packet.BaseResponse;


/**
 * 
 * @author fastjrun
 */
public interface UserServiceRest {


    public BaseResponse<BaseDefaultResponseBody> register(BaseAppRequest<RegistserRestRequestBody> request);

    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> login(BaseAppRequest<com.fastjrun.demo.packet.app.LoginRestRequestBody> request);

    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> loginv1_1(BaseAppRequest<com.fastjrun.demo.packet.app.LoginRestRequestBody> request);

    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> autoLogin(BaseAppRequest<AutoLoginRestRequestBody> request);

}
