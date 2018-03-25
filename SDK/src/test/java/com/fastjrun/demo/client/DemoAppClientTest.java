
package com.fastjrun.demo.client;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import com.fastjrun.demo.client.DemoAppClient;
import com.fastjrun.demo.packet.app.AutoLoginRestRequestBody;
import com.fastjrun.demo.packet.app.RegistserRestRequestBody;
import com.fastjrun.test.BaseTest;
import net.sf.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoAppClientTest
    extends BaseTest
{

    DemoAppClient demoAppClient = new DemoAppClient();
    Properties propParams = new Properties();

    @BeforeTest
    @Parameters({
        "envName"
    })
    public void init(String envName) {
        demoAppClient.initSDKConfig("demo");
        try {
            InputStream inParam = DemoAppClient.class.getResourceAsStream((("/testdata/"+ envName)+".properties"));
            propParams.load(inParam);
        } catch (IOException _x) {
            _x.printStackTrace();
        }
    }

    @DataProvider(name = "loadParam")
    public Object[][] loadParam(Method method) {
        Set<String> keys = propParams.stringPropertyNames();
        List<String[]> parameters = new ArrayList<String[]>();
        for (String key: keys) {
            if (key.startsWith((("DemoAppClient"+".")+(method.getName()+".")))) {
                String value = propParams.getProperty(key);
                parameters.add(new String[] {value });
            }
        }
        Object[][] object = new Object[parameters.size()][] ;
        for (int i = 0; (i<object.length); i ++) {
            String[] str = parameters.get(i);
            object[i] = new String[str.length] ;
            for (int j = 0; (j<str.length); j ++) {
                object[i][j] = str[j];
            }
        }
        return object;
    }

    @Test(dataProvider = "loadParam")
    @Parameters({
        "reqParamsJsonStr"
    })
    public void register(String reqParamsJsonStr) {
        JSONObject reqParamsJson = JSONObject.fromObject(reqParamsJsonStr);
        JSONObject reqJsonRequest = reqParamsJson.getJSONObject("request");
        RegistserRestRequestBody registserRestRequestBody = new RegistserRestRequestBody();
        String registserRestRequestBodyloginId = reqJsonRequest.getString("loginId");
        if ((!(registserRestRequestBodyloginId == null))&&(!registserRestRequestBodyloginId.equals(""))) {
            registserRestRequestBody.setLoginId(registserRestRequestBodyloginId);
        }
        String registserRestRequestBodyloginpwd = reqJsonRequest.getString("loginpwd");
        if ((!(registserRestRequestBodyloginpwd == null))&&(!registserRestRequestBodyloginpwd.equals(""))) {
            registserRestRequestBody.setLoginpwd(registserRestRequestBodyloginpwd);
        }
        String registserRestRequestBodynickName = reqJsonRequest.getString("nickName");
        if ((!(registserRestRequestBodynickName == null))&&(!registserRestRequestBodynickName.equals(""))) {
            registserRestRequestBody.setNickName(registserRestRequestBodynickName);
        }
        String registserRestRequestBodysex = reqJsonRequest.getString("sex");
        if ((!(registserRestRequestBodysex == null))&&(!registserRestRequestBodysex.equals(""))) {
            registserRestRequestBody.setSex(registserRestRequestBodysex);
        }
        String registserRestRequestBodymobileNo = reqJsonRequest.getString("mobileNo");
        if ((!(registserRestRequestBodymobileNo == null))&&(!registserRestRequestBodymobileNo.equals(""))) {
            registserRestRequestBody.setMobileNo(registserRestRequestBodymobileNo);
        }
        String registserRestRequestBodyemail = reqJsonRequest.getString("email");
        if ((!(registserRestRequestBodyemail == null))&&(!registserRestRequestBodyemail.equals(""))) {
            registserRestRequestBody.setEmail(registserRestRequestBodyemail);
        }
        String appKey = reqParamsJson.optString("appKey");
        String appVersion = reqParamsJson.optString("appVersion");
        String appSource = reqParamsJson.optString("appSource");
        String deviceId = reqParamsJson.optString("deviceId");
        try {
            demoAppClient.register(registserRestRequestBody, appKey, appVersion, appSource, deviceId);
        } catch (Exception _x) {
            _x.printStackTrace();
        }
    }

    @Test(dataProvider = "loadParam")
    @Parameters({
        "reqParamsJsonStr"
    })
    public void login(String reqParamsJsonStr) {
        JSONObject reqParamsJson = JSONObject.fromObject(reqParamsJsonStr);
        JSONObject reqJsonRequest = reqParamsJson.getJSONObject("request");
        com.fastjrun.demo.packet.app.LoginRestRequestBody loginRestRequestBody = new com.fastjrun.demo.packet.app.LoginRestRequestBody();
        String loginRestRequestBodyloginpwd = reqJsonRequest.getString("loginpwd");
        if ((!(loginRestRequestBodyloginpwd == null))&&(!loginRestRequestBodyloginpwd.equals(""))) {
            loginRestRequestBody.setLoginpwd(loginRestRequestBodyloginpwd);
        }
        String loginRestRequestBodyloginName = reqJsonRequest.getString("loginName");
        if ((!(loginRestRequestBodyloginName == null))&&(!loginRestRequestBodyloginName.equals(""))) {
            loginRestRequestBody.setLoginName(loginRestRequestBodyloginName);
        }
        String appKey = reqParamsJson.optString("appKey");
        String appVersion = reqParamsJson.optString("appVersion");
        String appSource = reqParamsJson.optString("appSource");
        String deviceId = reqParamsJson.optString("deviceId");
        try {
            com.fastjrun.demo.packet.app.LoginRestResponseBody responseBody = demoAppClient.login(loginRestRequestBody, appKey, appVersion, appSource, deviceId);
            log.info(responseBody);
        } catch (Exception _x) {
            _x.printStackTrace();
        }
    }

    @Test(dataProvider = "loadParam")
    @Parameters({
        "reqParamsJsonStr"
    })
    public void loginv1_1(String reqParamsJsonStr) {
        JSONObject reqParamsJson = JSONObject.fromObject(reqParamsJsonStr);
        JSONObject reqJsonRequest = reqParamsJson.getJSONObject("request");
        com.fastjrun.demo.packet.app.LoginRestRequestBody loginRestRequestBody = new com.fastjrun.demo.packet.app.LoginRestRequestBody();
        String loginRestRequestBodyloginpwd = reqJsonRequest.getString("loginpwd");
        if ((!(loginRestRequestBodyloginpwd == null))&&(!loginRestRequestBodyloginpwd.equals(""))) {
            loginRestRequestBody.setLoginpwd(loginRestRequestBodyloginpwd);
        }
        String loginRestRequestBodyloginName = reqJsonRequest.getString("loginName");
        if ((!(loginRestRequestBodyloginName == null))&&(!loginRestRequestBodyloginName.equals(""))) {
            loginRestRequestBody.setLoginName(loginRestRequestBodyloginName);
        }
        String appKey = reqParamsJson.optString("appKey");
        String appVersion = reqParamsJson.optString("appVersion");
        String appSource = reqParamsJson.optString("appSource");
        String deviceId = reqParamsJson.optString("deviceId");
        try {
            com.fastjrun.demo.packet.app.LoginRestResponseBody responseBody = demoAppClient.loginv1_1(loginRestRequestBody, appKey, appVersion, appSource, deviceId);
            log.info(responseBody);
        } catch (Exception _x) {
            _x.printStackTrace();
        }
    }

    @Test(dataProvider = "loadParam")
    @Parameters({
        "reqParamsJsonStr"
    })
    public void autoLogin(String reqParamsJsonStr) {
        JSONObject reqParamsJson = JSONObject.fromObject(reqParamsJsonStr);
        JSONObject reqJsonRequest = reqParamsJson.getJSONObject("request");
        AutoLoginRestRequestBody autoLoginRestRequestBody = new AutoLoginRestRequestBody();
        String autoLoginRestRequestBodyuuidOld = reqJsonRequest.getString("uuidOld");
        if ((!(autoLoginRestRequestBodyuuidOld == null))&&(!autoLoginRestRequestBodyuuidOld.equals(""))) {
            autoLoginRestRequestBody.setUuidOld(autoLoginRestRequestBodyuuidOld);
        }
        String appKey = reqParamsJson.optString("appKey");
        String appVersion = reqParamsJson.optString("appVersion");
        String appSource = reqParamsJson.optString("appSource");
        String deviceId = reqParamsJson.optString("deviceId");
        try {
            com.fastjrun.demo.packet.app.LoginRestResponseBody responseBody = demoAppClient.autoLogin(autoLoginRestRequestBody, appKey, appVersion, appSource, deviceId);
            log.info(responseBody);
        } catch (Exception _x) {
            _x.printStackTrace();
        }
    }

}
