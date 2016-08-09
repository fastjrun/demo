package com.fastjrun.demospring4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.fastjrun.demospring4.bean.User;

public interface UserDao {

    @Insert("insert into t_user(`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,`lastModifyTime`,`email`) values(#{loginPwd},#{nickName},#{sex},#{mobileNo},#{loginErrCount},#{lastLoginTime},#{createTime},#{loginName},#{lastModifyTime},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertNoStatusAndLastRecordLoginErrTime(User user);

    @Select("select * from t_user where id = #{id} and sex=#{sex} ")
    @Options(flushCache = true)
    public int selectByIdAndSex(@Param("id") long id, @Param("sex") int sex);

    @Select("select `id` id,`createTime` createTime,`sex` sex,`mobileNo` mobileNo,`loginName` loginName,`id` id,`email` email from t_user")
    @Options(flushCache = true)
    public List<User> queryForLimitList(RowBounds rowBounds);

}
