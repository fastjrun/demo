/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.utils;

import org.testng.annotations.Test;

import com.fastjrun.demo.share.utils.MD5Utils;

public class MD5UtilsTest {

    @Test
    public void testMd5Encode(){
        String passwd = "111111";
        System.out.println(MD5Utils.md5Encode(passwd,"UTF-8"));
    }

}
