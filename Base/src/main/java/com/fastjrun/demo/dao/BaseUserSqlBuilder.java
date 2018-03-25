
package com.fastjrun.demo.dao;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import com.fastjrun.demo.bean.User;


/**
 * This is the sqlBuilder to table 用户表.
 * 
 * @author fastjrun
 */
public class BaseUserSqlBuilder {


    public String totalCountCondition(Map<java.lang.String, java.lang.String> parameter) {
        String condition = parameter.get("condition");
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from t_user where 1 = 1 ");
        sb.append(condition);
        return sb.toString();
    }

    public String queryWithCondition(Map<String, String> parameter) {
        String condition = parameter.get("condition");
        StringBuilder sb = new StringBuilder();
        sb.append("select `loginPwd` loginPwd,`nickName` nickName,`sex` sex,`mobileNo` mobileNo,`loginErrCount` loginErrCount,`lastLoginTime` lastLoginTime,`createTime` createTime,`loginName` loginName,`lastModifyTime` lastModifyTime,`id` id,`email` email,`lastRecordLoginErrTime` lastRecordLoginErrTime,`status` status from t_user where 1 = 1 ");
        sb.append(condition);
        return sb.toString();
    }

    public String insertAll(Map<String, List<User>> parameter) {
        List<User> Users = parameter.get("list");
        StringBuilder sb = new StringBuilder();
        MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].loginPwd},#'{'list[{0}].nickName},#'{'list[{0}].sex},#'{'list[{0}].mobileNo},#'{'list[{0}].loginErrCount},#'{'list[{0}].lastLoginTime},#'{'list[{0}].createTime},#'{'list[{0}].loginName},#'{'list[{0}].lastModifyTime},#'{'list[{0}].email},#'{'list[{0}].lastRecordLoginErrTime},#'{'list[{0}].status})");
        sb.append("INSERT INTO t_user(`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) VALUES");
        for (int i = 0; (i<Users.size()); i ++) {
            sb.append(messageFormat.format(new Object[] {i }));
            if (i<(Users.size()- 1)) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

}
