
package com.fastjrun.demospring4.service;

import com.fastjrun.demospring4.packet.user.AutoLoginRestRequestBody;
import com.fastjrun.demospring4.packet.user.AutoLoginRestResponseBody;
import com.fastjrun.demospring4.packet.user.LoginRestRequestBody;
import com.fastjrun.demospring4.packet.user.LoginRestResponseBody;
import com.fastjrun.demospring4.packet.user.Loginv1_1RestRequestBody;
import com.fastjrun.demospring4.packet.user.Loginv1_1RestResponseBody;
import com.fastjrun.demospring4.packet.user.RegistserRestRequestBody;
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
