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
import com.fastjrun.demo.dao.BaseUserDao;
import com.fastjrun.demo.dao.UserDao;
import com.fastjrun.demo.entity.User;
import com.fastjrun.demo.entity.UserExample;
import com.fastjrun.demo.entity.UserExample.Criteria;
import com.fastjrun.demo.mapper.UserLoginMapper;
import com.fastjrun.demo.mapper.UserMapper;
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
    private UserMapper userMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
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
        Short sex = Short.valueOf(request.getBody().getSex());
        String mobileNo = request.getBody().getMobileNo();
        String email = request.getBody().getEmail();
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(loginName);
        criteria.andMobilenoEqualTo(mobileNo);
        List<User> users = userMapper.selectByExample(example);
        if (!users.isEmpty()) {
            throw new ServiceException(USER_ALREADY_EXISTS,
                    serviceMessageSource.getMessage(USER_ALREADY_EXISTS, null,
                            null));
        } else {
            Timestamp curTimestamp = new Timestamp(System.currentTimeMillis());
            User user = new User();
            user.setCreatetime(curTimestamp);
            user.setEmail(email);
            user.setLastmodifytime(curTimestamp);
            user.setLoginname(loginName);
            user.setLoginpwd(loginPwd);
            user.setMobileno(mobileNo);
            user.setNickname(nickName);
            user.setSex(sex);
            user.setStatus("1");
            user.setLastrecordloginerrtime(null);
            user.setLoginerrcount(new Short("0"));
            userMapper.insert(user);
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
        responseBody.setNickName(user.getNickname());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileno());
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
        responseBody.setNickName(user.getNickname());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileno());
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
        responseBody.setNickName(user.getNickname());
        responseBody.setEmail(user.getEmail());
        responseBody.setSex(String.valueOf(user.getSex()));
        responseBody.setMobileNo(user.getMobileno());
        response.setBody(responseBody);
        return response;
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateByPrimaryKey(user);

    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map<String, Object>> queryForLimitList(RowBounds rowBounds) {
        UserExample example = new UserExample();
        List<User> list = userMapper.selectByExampleWithRowbounds(example,
                rowBounds);
        List<Map<String, Object>> restList = new ArrayList<Map<String, Object>>();
        Iterator<User> itera = list.iterator();
        while (itera.hasNext()) {
            User user = itera.next();
            Map<String, Object> restMap = new HashMap<String, Object>();
            restMap.put("loginPwd", user.getLoginpwd());
            restMap.put("nickName", user.getNickname());
            restMap.put("sex", user.getSex());
            restMap.put("mobileNo", user.getMobileno());
            restMap.put("loginErrCount", user.getLoginerrcount());
            restMap.put("lastLoginTime", user.getLastlogintime());
            restMap.put("createTime", user.getCreatetime());
            restMap.put("loginName", user.getLoginname());
            restMap.put("lastModifyTime", user.getLastmodifytime());
            restMap.put("id", user.getId());
            restMap.put("email", user.getEmail());
            restMap.put("lastRecordLoginErrTime",
                    user.getLastrecordloginerrtime());
            restMap.put("status", user.getStatus());
            restList.add(restMap);
        }

        return restList;
    }
}
