package com.fastjrun.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemConstants {

    public static String interfaceUrlPrefix = "http://localhost:8080/demo-Rest";
    public static String TESTDATA_PATH = "src/main/testdata/local.properties";

    public static Properties propParams = new Properties();
    static {
        Properties prop = new Properties();
        try {
            prop.load(SystemConstants.class.getResourceAsStream("/service.properties"));
            SystemConstants.interfaceUrlPrefix = prop.getProperty("system.interfaceUrlPrefix");
            SystemConstants.TESTDATA_PATH = prop.getProperty("testdata.filePath");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            InputStream inParam = SystemConstants.class.getResourceAsStream(SystemConstants.TESTDATA_PATH);
            propParams.load(inParam);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    };

}
