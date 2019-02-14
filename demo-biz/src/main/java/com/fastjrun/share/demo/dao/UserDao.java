/*
 * Copyright (C) 2018 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

import com.fastjrun.share.demo.entity.User;

public interface UserDao {

    @Insert("insert into t_user(`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,"
            + "`loginName`,`lastModifyTime`,`email`) values(#{loginPwd},#{nickName},#{sex},#{mobileNo},"
            + "#{loginErrCount},#{lastLoginTime},#{createTime},#{loginName},#{lastModifyTime},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertNoStatusAndLastRecordLoginErrTime(User user);

    @Update("update t_user set `loginPwd` = #{loginPwd},`nickName` = #{nickName},`sex` = #{sex},`mobileNo` = "
            + "#{mobileNo},`loginName` = #{loginName},`email` = #{email} where id = #{id} ")
    public int updateUserInfoById(User user);

}
