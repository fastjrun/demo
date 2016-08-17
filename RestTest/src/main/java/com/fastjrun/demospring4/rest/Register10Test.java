package com.fastjrun.demospring4.rest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fastjrun.demospring4.packet.user.RegistserRestRequestBody;
import com.fastjrun.helper.TimeHelper;
import com.fastjrun.test.BaseITest;
import com.fastjrun.test.SystemConstants;

public class Register10Test extends BaseITest {
    String txUrl = "/api/user/register/autotest/";

    @DataProvider(name = "success")
    public Object[][] success(Method m) {
        System.out.println(m.getName());
        Set<String> keys = SystemConstants.propParams.stringPropertyNames();
        List<String[]> parameters = new ArrayList<String[]>();
        for (String key : keys) {
            if (key.startsWith("Register10Test.success")) {
                String value = SystemConstants.propParams.getProperty(key);
                parameters.add(value.split(","));
            }
        }
        Object[][] object = new Object[parameters.size()][];
        for (int i = 0; i < object.length; i++) {
            String[] str = parameters.get(i);
            object[i] = new Object[str.length];
            for (int j = 0; j < str.length; j++) {
                object[i][j] = str[j];
            }
        }
        return object;

    }

    @Test(groups = { "local", "env-test", "env-testing", "env-prod" }, dataProvider = "success", threadPoolSize = 4)
    @Parameters({ "email", "loginName", "loginpwd", "mobileNo", "nickName",
            "sex" })
    public void success(String email, String loginName, String loginpwd,
            String mobileNo, String nickName, String sex) {
        RegistserRestRequestBody requestBody = new RegistserRestRequestBody();
        requestBody.setEmail(email);
        requestBody.setLoginpwd(loginpwd);
        requestBody.setMobileNo(mobileNo);
        requestBody.setNickName(nickName);
        requestBody.setSex(sex);
        JSONObject reqObject = JSONObject.fromObject(requestBody);
        String reqStr = reqObject.toString();
        log.debug(reqStr);
        String reqTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
        String urlReq = SystemConstants.interfaceUrlPrefix + txUrl + reqTime;
        String response = this.process(reqStr, urlReq);
        log.debug(response);
    }
}
