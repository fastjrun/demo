
package com.fastjrun.demo.dao;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import com.fastjrun.demo.bean.UserLogin;


/**
 * This is the sqlBuilder to table 用户登录凭证表.
 * 
 * @author fastjrun
 */
public class BaseUserLoginSqlBuilder {


    public String totalCountCondition(Map<java.lang.String, java.lang.String> parameter) {
        String condition = parameter.get("condition");
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from t_user_login where 1 = 1 ");
        sb.append(condition);
        return sb.toString();
    }

    public String queryWithCondition(Map<String, String> parameter) {
        String condition = parameter.get("condition");
        StringBuilder sb = new StringBuilder();
        sb.append("select `createTime` createTime,`logOutTime` logOutTime,`loginCredential` loginCredential,`inValidateTime` inValidateTime,`id` id,`userId` userId,`deviceId` deviceId,`status` status from t_user_login where 1 = 1 ");
        sb.append(condition);
        return sb.toString();
    }

    public String insertAll(Map<String, List<UserLogin>> parameter) {
        List<UserLogin> UserLogins = parameter.get("list");
        StringBuilder sb = new StringBuilder();
        MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].createTime},#'{'list[{0}].logOutTime},#'{'list[{0}].loginCredential},#'{'list[{0}].inValidateTime},#'{'list[{0}].userId},#'{'list[{0}].deviceId},#'{'list[{0}].status})");
        sb.append("INSERT INTO t_user_login(`createTime`,`logOutTime`,`loginCredential`,`inValidateTime`,`userId`,`deviceId`,`status`) VALUES");
        for (int i = 0; (i<UserLogins.size()); i ++) {
            sb.append(messageFormat.format(new Object[] {i }));
            if (i<(UserLogins.size()- 1)) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

}
