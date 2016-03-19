package com.demospring4.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseITest {
    protected final Log log = LogFactory.getLog(this.getClass());

    protected String process(String reqStr, String urlReq) {
        String line = "";
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL request = new URL(urlReq);
            HttpURLConnection connection = (HttpURLConnection) request.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            // connection.setRequestProperty("Charset", "UTF-8");
            connection.connect();
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(reqStr);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = in.readLine()) != null) {
                result += line;
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
