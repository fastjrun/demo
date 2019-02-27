-- CoreUserServiceTest.testCheckLoign
insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(1,'00000000000000000000000000000001','fastjrun0001',
'1','12000000001',0,'20201209233400123','2020-12-09 23:34:00','fastjrun0001',null,'fastjrun0001@fastjrun.com',null,
1);
insert into t_user_login(`id`,`createTime`,`loginCredential`,`inValidateTime`,`userId`,`deviceId`,
`status`) values(1,'20201209233400010','00000000000000000000000000000001','20210209233400010',1,
'unitTest','1');

-- CoreUserServiceTest.testAutoLogin
insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(1001,'00000000000000000000000000001001',
'fastjrun1001',
'2','12000001001',0,'20201209233400123','2020-12-09 23:34:00','fastjrun1001',null,'fastjrun1001@fastjrun.com',null,
1);
insert into t_user_login(`id`,`createTime`,`loginCredential`,`inValidateTime`,`userId`,`deviceId`,
`status`) values(1001,'20201209233400010','00000000000000000000000000001001','20210209233400010',1001,'unitTest','1');
-- UserServiceTest.testAutoLogin
insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(1005,'00000000000000000000000000001005',
'fastjrun1005',
'2','12000001005',0,'20201209233400123','2020-12-09 23:34:00','fastjrun1005',null,'fastjrun1005@fastjrun.com',null,
1);
insert into t_user_login(`id`,`createTime`,`loginCredential`,`inValidateTime`,`userId`,`deviceId`,
`status`) values(1005,'20201209233400010','00000000000000000000000000001005','20210209233400010',1005,'unitTest','1');

-- InvalidLoginCredentialExecutorTest.testExecute
insert into t_user_login(`id`,`createTime`,`loginCredential`,`inValidateTime`,`userId`,`deviceId`,
`status`) values(2,'20161209233400010','00000000000000000000000000000002','20170209233400010',1,'unitTest','1');

insert into t_user_login(`id`,`createTime`,`loginCredential`,`inValidateTime`,`userId`,`deviceId`,
`status`) values(3,'20201209233400010','00000000000000000000000000000003','20170209233400010',1001,'unitTest','1');



-- CoreUserServiceTest.testLogin
insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(2001,'00000000000000000000000000002001',
'fastjrun2001',
'2','12000002001',0,'20201209233400123','2020-12-09 23:34:00','fastjrun2001',null,'fastjrun2001@fastjrun.com',null,
1);

insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(2002,'00000000000000000000000000002002',
'fastjrun2002',
'1','12000002002',5,'20201209233400123','2020-12-09 23:34:00','fastjrun2002',null,'fastjrun2002@fastjrun.com',null,
2);

insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(2003,'00000000000000000000000000002003',
'fastjrun2003',
'1','12000002003',5,'20201209233400123','2020-12-09 23:34:00','fastjrun2003',null,'fastjrun2002@fastjrun.com',null,
3);

insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(2004,'00000000000000000000000000002004',
'fastjrun2004',
'2','12000002004',4,'20201209233400123','2020-12-09 23:34:00','fastjrun2004',null,'fastjrun2004@fastjrun.com',null,
1);
-- UserServiceTest.testLogin
insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(2005,'00000000000000000000000000002005',
'fastjrun2005',
'2','12000002005',0,'20201209233400123','2020-12-09 23:34:00','fastjrun2005',null,'fastjrun2005@fastjrun.com',null,
1);

insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(2006,'00000000000000000000000000002006',
'fastjrun2006',
'1','12000002006',5,'20201209233400123','2020-12-09 23:34:00','fastjrun2006',null,'fastjrun2006@fastjrun.com',null,
2);

insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(2007,'00000000000000000000000000002007',
'fastjrun2007',
'1','12000002007',5,'20201209233400123','2020-12-09 23:34:00','fastjrun2007',null,'fastjrun2007@fastjrun.com',null,
3);

insert into t_user(`id`,`loginPwd`,`nickName`,`sex`,`mobileNo`,`loginErrCount`,`lastLoginTime`,`createTime`,`loginName`,
`lastModifyTime`,`email`,`lastRecordLoginErrTime`,`status`) values(2008,'00000000000000000000000000002008',
'fastjrun2008',
'2','12000002008',4,'20201209233400123','2020-12-09 23:34:00','fastjrun2008',null,'fastjrun2008@fastjrun.com',null,
1);
