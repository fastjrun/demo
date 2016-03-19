package com.fastjrun.demospring4.rest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.demospring4.test.BaseITest;
import com.demospring4.test.SystemConstants;
import com.fastjrun.demospring4.packet.req.Login10Request;
import com.fastjrun.helper.TimeHelper;

import net.sf.json.JSONObject;

public class Login10Test extends BaseITest {
    String txUrl = "/user/login/10/authtest/";

    @DataProvider(name = "success")
    public Object[][] success(Method m) {
        System.out.println(m.getName());
        Set<String> keys = SystemConstants.propParams.stringPropertyNames();
        List<String[]> parameters = new ArrayList<String[]>();
        for (String key : keys) {
            if (key.startsWith("Login10Test.success")) {
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
    @Parameters({ "loginName", "loginpwd" })
    public void success(String loginName, String loginPwd) {
        Login10Request request = new Login10Request();
        request.setLoginName(loginName);
        request.setLoginpwd(loginPwd);
        JSONObject reqObject = JSONObject.fromObject(request);
        String reqStr = reqObject.toString();
        log.debug(reqStr);
        String reqTime = TimeHelper.getCurrentTime(TimeHelper.DF17);
        String urlReq = SystemConstants.interfaceUrlPrefix + txUrl + reqTime;
        String response = this.process(reqStr, urlReq);
        log.debug(response);
    }
}
