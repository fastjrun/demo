package com.fastjrun.demospring4.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fastjrun.demospring4.bean.User;

public interface UserDao {

    @Insert("insert into t_user(`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,`lastModifyTime`,`email`) values(#{loginPwd},#{nickName},#{sex},#{mobileNo},#{loginErrCount},#{lastLoginTime},#{createTime},#{loginName},#{lastModifyTime},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertNoStatusAndLastRecordLoginErrTime(User user);

    @Select("select count(`nickName`) from t_user where id = #{id} and sex=#{sex} ")
    @Options(flushCache = true)
    public int selectById(@Param("id") long id, @Param("sex") int sex);

}
