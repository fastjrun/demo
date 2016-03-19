
package com.fastjrun.demospring4.dao;

import java.util.List;
import com.fastjrun.demospring4.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;


/**
 * This is the dao to table 用户表.
 * 
 * @author fastjrun
 */
public interface BaseUserDao {


    @Insert("insert into t_user(loginPwd,nickName,sex,mobileNo,loginErrCount,lastLoginTime,createTime,loginName,lastModifyTime,email,lastRecordLoginErrTime,status) values(#{loginPwd},#{nickName},#{sex},#{mobileNo},#{loginErrCount},#{lastLoginTime},#{createTime},#{loginName},#{lastModifyTime},#{email},#{lastRecordLoginErrTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(User user);

    @Select("select loginPwd loginPwd,nickName nickName,sex sex,mobileNo mobileNo,loginErrCount loginErrCount,lastLoginTime lastLoginTime,createTime createTime,loginName loginName,lastModifyTime lastModifyTime,id id,email email,lastRecordLoginErrTime lastRecordLoginErrTime,status status from t_user where id = #{id} ")
    @Options(flushCache = true)
    public User selectById(int id);

    @Delete("delete from t_user where id = #{id} ")
    public int deleteById(int id);

    @Update("update t_user set loginPwd=#{loginPwd},nickName=#{nickName},sex=#{sex},mobileNo=#{mobileNo},loginErrCount=#{loginErrCount},lastLoginTime=#{lastLoginTime},createTime=#{createTime},loginName=#{loginName},lastModifyTime=#{lastModifyTime},email=#{email},lastRecordLoginErrTime=#{lastRecordLoginErrTime},status=#{status} where id = #{id} ")
    public int updateById(User user);

    @Select("select count(*) from t_user")
    @Options(flushCache = true)
    public int totalCount();

    @Select("select loginPwd loginPwd,nickName nickName,sex sex,mobileNo mobileNo,loginErrCount loginErrCount,lastLoginTime lastLoginTime,createTime createTime,loginName loginName,lastModifyTime lastModifyTime,id id,email email,lastRecordLoginErrTime lastRecordLoginErrTime,status status from t_user")
    @Options(flushCache = true)
    public List<User> queryForList();

    @Select("select loginPwd loginPwd,nickName nickName,sex sex,mobileNo mobileNo,loginErrCount loginErrCount,lastLoginTime lastLoginTime,createTime createTime,loginName loginName,lastModifyTime lastModifyTime,id id,email email,lastRecordLoginErrTime lastRecordLoginErrTime,status status from t_user")
    @Options(flushCache = true)
    public List<User> queryForLimitList(RowBounds rowBounds);

    @Select("select count(*) from t_user where 1 = 1 @{condition}")
    @Options(flushCache = true)
    public int totalCountCondition();

    @Select("select loginPwd loginPwd,nickName nickName,sex sex,mobileNo mobileNo,loginErrCount loginErrCount,lastLoginTime lastLoginTime,createTime createTime,loginName loginName,lastModifyTime lastModifyTime,id id,email email,lastRecordLoginErrTime lastRecordLoginErrTime,status status from t_user where 1 = 1 @{condition}")
    @Options(flushCache = true)
    public List<User> queryForListCondition();

    @Select("select loginPwd loginPwd,nickName nickName,sex sex,mobileNo mobileNo,loginErrCount loginErrCount,lastLoginTime lastLoginTime,createTime createTime,loginName loginName,lastModifyTime lastModifyTime,id id,email email,lastRecordLoginErrTime lastRecordLoginErrTime,status status from t_user where 1 = 1 @{condition}")
    @Options(flushCache = true)
    public List<User> queryForLimitListCondition(RowBounds rowBounds);

}
