/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/2/21 12:10:21                           */
/*==============================================================*/


drop table if exists t_user;

drop table if exists t_user_login;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   int not null auto_increment comment '����',
   loginName            varchar(50) default NULL,
   loginPwd             char(32),
   mobileNo             varchar(20) comment '�ֻ���',
   nickName             varchar(20) comment '�ǳ�',
   sex                  smallint default 1 comment '1���У�2��Ů��3��δ֪',
   email                char(30) comment '�ʼ�',
   createTime           datetime,
   lastModifyTime       datetime,
   lastLoginTime        char(17),
   loginErrCount        smallint default 0,
   lastRecordLoginErrTime char(17),
   status               char(1) default '1' comment '1��������2������������3���˹�����',
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_login                                          */
/*==============================================================*/
create table t_user_login
(
   id                   int not null auto_increment comment '����',
   userId               int not null,
   deviceId             char(32),
   loginCredential      char(32),
   createTime           char(17),
   logOutTime           char(17) comment 'ƾ֤ʵ��ע��ʱ��',
   inValidateTime       char(17) comment '����ϵͳ���ã�ƾ֤Ӧ��ʧЧ��ʱ��',
   status               char(1) default '1' comment '1��������2����Ч',
   primary key (id)
);

