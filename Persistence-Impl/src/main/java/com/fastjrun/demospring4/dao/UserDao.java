package com.fastjrun.demospring4.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.fastjrun.demospring4.bean.User;

public interface UserDao {

    @Insert("insert into t_user(`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,`lastModifyTime`,`email`) values(#{loginPwd},#{nickName},#{sex},#{mobileNo},#{loginErrCount},#{lastLoginTime},#{createTime},#{loginName},#{lastModifyTime},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertNoStatusAndLastRecordLoginErrTime(User user);

    @Update("update t_user set `sex` = #{sex},`mobileNo` = #{mobileNo},`loginName` = #{loginName},`email` = #{email} where id = #{id} ")
    public int updateUserInfoById(@Param("loginName") String loginName, @Param("sex") String sex,
            @Param("mobileNo") String mobileNo, @Param("email") String email, @Param("id") Integer id);

}
