
package com.fastjrun.demo.service;

import com.fastjrun.demo.packet.user.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.user.AutoLoginRestResponseBody;
import com.fastjrun.demo.packet.user.LoginRestRequestBody;
import com.fastjrun.demo.packet.user.LoginRestResponseBody;
import com.fastjrun.demo.packet.user.Loginv1_1RestRequestBody;
import com.fastjrun.demo.packet.user.Loginv1_1RestResponseBody;
import com.fastjrun.demo.packet.user.RegistserRestRequestBody;
import com.fastjrun.packet.BaseRestDefaultResponseBody;
import com.fastjrun.packet.BaseRestRequest;
import com.fastjrun.packet.BaseRestResponse;


/**
 * 
 * @author robin
 */
public interface UserServiceRest {


    public BaseRestResponse<BaseRestDefaultResponseBody> register(BaseRestRequest<RegistserRestRequestBody> request);

    public BaseRestResponse<LoginRestResponseBody> login(BaseRestRequest<LoginRestRequestBody> request);

    public BaseRestResponse<Loginv1_1RestResponseBody> loginv1_1(BaseRestRequest<Loginv1_1RestRequestBody> request);

    public BaseRestResponse<AutoLoginRestResponseBody> autoLogin(BaseRestRequest<AutoLoginRestRequestBody> request);

}
