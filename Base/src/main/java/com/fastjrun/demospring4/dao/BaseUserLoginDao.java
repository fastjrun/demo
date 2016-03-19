
package com.fastjrun.demospring4.dao;

import java.util.List;
import com.fastjrun.demospring4.bean.UserLogin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;


/**
 * This is the dao to table 用户登录凭证表.
 * 
 * @author fastjrun
 */
public interface BaseUserLoginDao {


    @Insert("insert into t_user_login(createTime,logOutTime,loginCredential,inValidateTime,userId,deviceId,status) values(#{createTime},#{logOutTime},#{loginCredential},#{inValidateTime},#{userId},#{deviceId},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(UserLogin userLogin);

    @Select("select createTime createTime,logOutTime logOutTime,loginCredential loginCredential,inValidateTime inValidateTime,id id,userId userId,deviceId deviceId,status status from t_user_login where id = #{id} ")
    @Options(flushCache = true)
    public UserLogin selectById(int id);

    @Delete("delete from t_user_login where id = #{id} ")
    public int deleteById(int id);

    @Update("update t_user_login set createTime=#{createTime},logOutTime=#{logOutTime},loginCredential=#{loginCredential},inValidateTime=#{inValidateTime},userId=#{userId},deviceId=#{deviceId},status=#{status} where id = #{id} ")
    public int updateById(UserLogin userLogin);

    @Select("select count(*) from t_user_login")
    @Options(flushCache = true)
    public int totalCount();

    @Select("select createTime createTime,logOutTime logOutTime,loginCredential loginCredential,inValidateTime inValidateTime,id id,userId userId,deviceId deviceId,status status from t_user_login")
    @Options(flushCache = true)
    public List<UserLogin> queryForList();

    @Select("select createTime createTime,logOutTime logOutTime,loginCredential loginCredential,inValidateTime inValidateTime,id id,userId userId,deviceId deviceId,status status from t_user_login")
    @Options(flushCache = true)
    public List<UserLogin> queryForLimitList(RowBounds rowBounds);

    @Select("select count(*) from t_user_login where 1 = 1 @{condition}")
    @Options(flushCache = true)
    public int totalCountCondition();

    @Select("select createTime createTime,logOutTime logOutTime,loginCredential loginCredential,inValidateTime inValidateTime,id id,userId userId,deviceId deviceId,status status from t_user_login where 1 = 1 @{condition}")
    @Options(flushCache = true)
    public List<UserLogin> queryForListCondition();

    @Select("select createTime createTime,logOutTime logOutTime,loginCredential loginCredential,inValidateTime inValidateTime,id id,userId userId,deviceId deviceId,status status from t_user_login where 1 = 1 @{condition}")
    @Options(flushCache = true)
    public List<UserLogin> queryForLimitListCondition(RowBounds rowBounds);

}
