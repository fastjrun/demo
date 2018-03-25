
package com.fastjrun.enhanced.mock;

import com.fastjrun.demo.packet.app.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.app.RegistserRestRequestBody;
import com.fastjrun.demo.service.UserServiceRest;
import com.fastjrun.helper.BaseResponseHelper;
import com.fastjrun.helper.MockHelper;
import com.fastjrun.packet.BaseAppRequest;
import com.fastjrun.packet.BaseDefaultResponseBody;
import com.fastjrun.packet.BaseResponse;
import com.fastjrun.packet.BaseResponseHead;
import org.springframework.stereotype.Service;

@Service
public class UserServiceRestMock
    implements UserServiceRest
{


    @Override
    public BaseResponse<BaseDefaultResponseBody> register(BaseAppRequest<RegistserRestRequestBody> request) {
        BaseResponse<BaseDefaultResponseBody> response = BaseResponseHelper.getSuccessResult();
        return response;
    }

    @Override
    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> login(BaseAppRequest<com.fastjrun.demo.packet.app.LoginRestRequestBody> request) {
        BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> response = new BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody>();
        BaseResponseHead responseHead = new BaseResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("Mock.");
        response.setHead(responseHead);
        com.fastjrun.demo.packet.app.LoginRestResponseBody loginRestResponseBody = new com.fastjrun.demo.packet.app.LoginRestResponseBody();
        loginRestResponseBody.setNickName(MockHelper.geStringWithAscii(30));
        loginRestResponseBody.setSex(MockHelper.geStringWithAscii(1));
        loginRestResponseBody.setMobileNo(MockHelper.geStringWithAscii(20));
        loginRestResponseBody.setUuid(MockHelper.geStringWithAscii(64));
        loginRestResponseBody.setEmail(MockHelper.geStringWithAscii(30));
        response.setBody(loginRestResponseBody);
        return response;
    }

    @Override
    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> loginv1_1(BaseAppRequest<com.fastjrun.demo.packet.app.LoginRestRequestBody> request) {
        BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> response = new BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody>();
        BaseResponseHead responseHead = new BaseResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("Mock.");
        response.setHead(responseHead);
        com.fastjrun.demo.packet.app.LoginRestResponseBody loginRestResponseBody = new com.fastjrun.demo.packet.app.LoginRestResponseBody();
        loginRestResponseBody.setNickName(MockHelper.geStringWithAscii(30));
        loginRestResponseBody.setSex(MockHelper.geStringWithAscii(1));
        loginRestResponseBody.setMobileNo(MockHelper.geStringWithAscii(20));
        loginRestResponseBody.setUuid(MockHelper.geStringWithAscii(64));
        loginRestResponseBody.setEmail(MockHelper.geStringWithAscii(30));
        response.setBody(loginRestResponseBody);
        return response;
    }

    @Override
    public BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> autoLogin(BaseAppRequest<AutoLoginRestRequestBody> request) {
        BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody> response = new BaseResponse<com.fastjrun.demo.packet.app.LoginRestResponseBody>();
        BaseResponseHead responseHead = new BaseResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("Mock.");
        response.setHead(responseHead);
        com.fastjrun.demo.packet.app.LoginRestResponseBody loginRestResponseBody = new com.fastjrun.demo.packet.app.LoginRestResponseBody();
        loginRestResponseBody.setNickName(MockHelper.geStringWithAscii(30));
        loginRestResponseBody.setSex(MockHelper.geStringWithAscii(1));
        loginRestResponseBody.setMobileNo(MockHelper.geStringWithAscii(20));
        loginRestResponseBody.setUuid(MockHelper.geStringWithAscii(64));
        loginRestResponseBody.setEmail(MockHelper.geStringWithAscii(30));
        response.setBody(loginRestResponseBody);
        return response;
    }

}
