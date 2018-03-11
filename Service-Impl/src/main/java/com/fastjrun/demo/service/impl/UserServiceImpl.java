package com.fastjrun.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastjrun.common.ServiceException;
import com.fastjrun.demo.bean.User;
import com.fastjrun.demo.dao.BaseUserDao;
import com.fastjrun.demo.dao.UserDao;
import com.fastjrun.demo.packet.app.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.app.LoginRestRequestBody;
import com.fastjrun.demo.packet.app.LoginRestResponseBody;
import com.fastjrun.demo.packet.app.RegistserRestRequestBody;
import com.fastjrun.demo.service.CoreUserService;
import com.fastjrun.demo.service.UserServiceAjax;
import com.fastjrun.demo.service.UserServiceRest;
import com.fastjrun.demo.service.UserServiceTask;
import com.fastjrun.helper.BaseResponseHelper;
import com.fastjrun.helper.UUID;
import com.fastjrun.packet.BaseAppRequest;
import com.fastjrun.packet.BaseDefaultResponseBody;
import com.fastjrun.packet.BaseResponse;
import com.fastjrun.packet.BaseResponseHead;
import com.fastjrun.service.impl.BaseService;

@Service
public class UserServiceImpl extends BaseService implements UserServiceRest,
        UserServiceTask, UserServiceAjax {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BaseUserDao baseUserDao;
    @Autowired
    private CoreUserService coreUserService;

    private final static String USER_ALREADY_EXISTS = "0001";

    @Override
    public void unlockUserPwd(Date date) {
        this.coreUserService.unlockUserPwd(date);

    }

    @Override
    public void inValidUserLoginCredential(Date date) {
        this.coreUserService.inValidUserLoginCredential(date);

    }

    @Override
    public BaseResponse<BaseDefaultResponseBody> register(
            BaseAppRequest<RegistserRestRequestBody> request) {
        String loginPwd = request.getBody().getLoginpwd();
        String loginName = request.getBody().getLoginId();
        String nickName = request.getBody().getNickName();
        Integer sex = Integer.valueOf(request.getBody().getSex());
        String mobileNo = request.getBody().getMobileNo();
        String email = request.getBody().getEmail();
        String condition = " and `loginName`='" + loginName + "' and `mobileNo`='"
                + mobileNo + "'";
        List<User> users = baseUserDao.queryForListCondition(condition);
        if (!users.isEmpty()) {
            throw new ServiceException(USER_ALREADY_EXISTS,
                    serviceMessageSource.getMessage(USER_ALREADY_EXISTS, null,
                            null));
        } else {
            Timestamp curTimestamp = new Timestamp(System.currentTimeMillis());
            User user = new User();
            user.setCreateTime(curTimestamp);
            user.setEmail(email);
            user.setLastModifyTime(curTimestamp);
            user.setLoginName(loginName);
            user.setLoginPwd(loginPwd);
            user.setMobileNo(mobileNo);
            user.setNickName(nickName);
            user.setSex(sex);
            user.setStatus("1");
            user.setLastRecordLoginErrTime(null);
            user.setLoginErrCount(new Integer("0"));
            baseUserDao.insert(user);
            return BaseResponseHelper.getSuccessResult();
        }
    }

    @Override
    public BaseResponse<LoginRestResponseBody> login(
            BaseAppRequest<LoginRestRequestBody> request) {
        String loginName = request.getBody().getLoginName();
        String loginPwd = request.getBody().getLoginpwd();
        String deviceId = request.getHead().getDeviceId();
        String uuid = UUID.getUUID();
        final User user = this.coreUserService.login(loginName, loginPwd,
                deviceId, uuid);
        BaseResponse<LoginRestResponseBody> response = new BaseResponse<LoginRestResponseBody>();
        BaseResponseHead responseHead = new BaseResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        LoginRestResponseBody responseBody = new LoginRestResponseBody();
        responseBody.setUuid(uuid);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        response.setBody(responseBody);
        return response;
    }

    @Override
    public BaseResponse<LoginRestResponseBody> loginv1_1(
            BaseAppRequest<LoginRestRequestBody> request) {
        String loginName = request.getBody().getLoginName();
        String loginPwd = request.getBody().getLoginpwd();
        String deviceId = request.getHead().getDeviceId();
        String uuid = UUID.getUUID();
        final User user = this.coreUserService.login(loginName, loginPwd,
                deviceId, uuid);

        BaseResponse<LoginRestResponseBody> response = new BaseResponse<LoginRestResponseBody>();
        BaseResponseHead responseHead = new BaseResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        LoginRestResponseBody responseBody = new LoginRestResponseBody();
        responseBody.setUuid(uuid);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        response.setBody(responseBody);
        return response;
    }

    @Override
    public BaseResponse<LoginRestResponseBody> autoLogin(
            BaseAppRequest<AutoLoginRestRequestBody> request) {
        String uuidOld = request.getBody().getUuidOld();
        String deviceId = request.getHead().getDeviceId();
        String uuidNew = UUID.getUUID();
        final User user = this.coreUserService.autoLogin(deviceId, uuidOld,
                uuidNew);

        BaseResponse<LoginRestResponseBody> response = new BaseResponse<LoginRestResponseBody>();
        BaseResponseHead responseHead = new BaseResponseHead();
        responseHead.setCode("0000");
        responseHead.setMsg("OK");
        response.setHead(responseHead);
        LoginRestResponseBody responseBody = new LoginRestResponseBody();
        responseBody.setUuid(uuidNew);
        responseBody.setNickName(user.getNickName());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileNo());
        response.setBody(responseBody);
        return response;
    }

    @Override
    public int updateById(User user) {
        return baseUserDao.updateByPK(user);

    }

    @Override
    public int insert(User user) {
        return baseUserDao.insert(user);
    }

    @Override
    public User selectById(Integer id) {
        return baseUserDao.selectByPK(id);
    }

    @Override
    public int deleteById(Integer id) {
        return baseUserDao.deleteByPK(id);
    }

    @Override
    public List<Map<String, Object>> queryForLimitList(RowBounds rowBounds) {
        List<User> list = baseUserDao.queryForLimitList(rowBounds);
        List<Map<String, Object>> restList = new ArrayList<Map<String, Object>>();
        Iterator<User> itera = list.iterator();
        while (itera.hasNext()) {
            User user = itera.next();
            Map<String, Object> restMap = new HashMap<String, Object>();
            restMap.put("loginPwd", user.getLoginPwd());
            restMap.put("nickName", user.getNickName());
            restMap.put("sex", user.getSex());
            restMap.put("mobileNo", user.getMobileNo());
            restMap.put("loginErrCount", user.getLoginErrCount());
            restMap.put("lastLoginTime", user.getLastLoginTime());
            restMap.put("createTime", user.getCreateTime());
            restMap.put("loginName", user.getLoginName());
            restMap.put("lastModifyTime", user.getLastModifyTime());
            restMap.put("id", user.getId());
            restMap.put("email", user.getEmail());
            restMap.put("lastRecordLoginErrTime",
                    user.getLastRecordLoginErrTime());
            restMap.put("status", user.getStatus());
            restList.add(restMap);
        }

        return restList;
    }
}
