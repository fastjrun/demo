
package com.fastjrun.demo.dao;

import java.util.List;
import com.fastjrun.demo.bean.UserLogin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;


/**
 * This is the dao to table 用户登录凭证表.
 * 
 * @author fastjrun
 */
public interface BaseUserLoginDao {


    @Insert("insert into t_user_login(`createTime`,`logOutTime`,`loginCredential`,`inValidateTime`,`userId`,`deviceId`,`status`) values(#{createTime},#{logOutTime},#{loginCredential},#{inValidateTime},#{userId},#{deviceId},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(UserLogin userLogin);

    @Select("select `createTime` createTime,`logOutTime` logOutTime,`loginCredential` loginCredential,`inValidateTime` inValidateTime,`id` id,`userId` userId,`deviceId` deviceId,`status` status from t_user_login where `id` = #{id}")
    @Options(flushCache = true)
    public UserLogin selectByPK(
        @Param("id")
        Integer id);

    @Delete("delete from t_user_login where `id` = #{id}")
    public int deleteByPK(
        @Param("id")
        Integer id);

    @Update("update t_user_login set `createTime` = #{createTime},`logOutTime` = #{logOutTime},`loginCredential` = #{loginCredential},`inValidateTime` = #{inValidateTime},`userId` = #{userId},`deviceId` = #{deviceId},`status` = #{status} where `id` = #{id}")
    public int updateByPK(UserLogin userLogin);

    @Select("select count(*) from t_user_login")
    @Options(flushCache = true)
    public int totalCount();

    @Select("select `createTime` createTime,`logOutTime` logOutTime,`loginCredential` loginCredential,`inValidateTime` inValidateTime,`id` id,`userId` userId,`deviceId` deviceId,`status` status from t_user_login")
    @Options(flushCache = true)
    public List<UserLogin> queryForList();

    @Select("select `createTime` createTime,`logOutTime` logOutTime,`loginCredential` loginCredential,`inValidateTime` inValidateTime,`id` id,`userId` userId,`deviceId` deviceId,`status` status from t_user_login")
    @Options(flushCache = true)
    public List<UserLogin> queryForLimitList(RowBounds rowBounds);

    @SelectProvider(type = BaseUserLoginSqlBuilder.class, method = "totalCountCondition")
    public int totalCountCondition(
        @Param("condition")
        java.lang.String condition);

    @SelectProvider(type = BaseUserLoginSqlBuilder.class, method = "queryWithCondition")
    public UserLogin selectOneCondition(
        @Param("condition")
        String condition);

    @SelectProvider(type = BaseUserLoginSqlBuilder.class, method = "queryWithCondition")
    public List<UserLogin> queryForListCondition(
        @Param("condition")
        String condition);

    @SelectProvider(type = BaseUserLoginSqlBuilder.class, method = "queryWithCondition")
    public List<UserLogin> queryForLimitListCondition(
        @Param("condition")
        String condition, RowBounds rowBounds);

    @InsertProvider(type = BaseUserLoginSqlBuilder.class, method = "insertAll")
    public int insertAll(List<UserLogin> userLogins);

}
