CREATE TABLE T_ACTIVITI_ROLE 
(
	ID VARCHAR(40) NOT NULL, 
    CODE VARCHAR(40), 
    NAME VARCHAR(40), 
	TYPE VARCHAR(10), 
	constraint PK_T_ACTIVITI_ROLE primary key (ID)
);

comment on table T_ACTIVITI_ROLE is
'ҵ���ɫ';
comment on column T_ACTIVITI_ROLE.ID is
'UUID';
comment on column T_ACTIVITI_ROLE.CODE is
'��ɫ����';
comment on column T_ACTIVITI_ROLE.NAME is
'��ɫ����';
comment on column T_ACTIVITI_ROLE.TYPE is
'����';

CREATE TABLE T_ACTIVITI_USER 
(
	ID VARCHAR(40) NOT NULL, 
	EMAIL VARCHAR(255), 
    CODE VARCHAR(40), 
    NAME VARCHAR(40), 
	PASSWORD VARCHAR(40), 
	constraint PK_T_ACTIVITI_USER primary key (ID)
);

comment on table T_ACTIVITI_USER is
'ҵ���û�';
comment on column T_ACTIVITI_USER.EMAIL is
'����';
comment on column T_ACTIVITI_USER.CODE is
'�û�����';
comment on column T_ACTIVITI_USER.NAME is
'�û�����';
comment on column T_ACTIVITI_USER.PASSWORD is
'�û�����';

CREATE TABLE T_ACTIVITI_USER_ROLE 
(
	USER_ID VARCHAR(40) NOT NULL, 
	ROLE_ID VARCHAR(40) NOT NULL
);
comment on table T_ACTIVITI_USER_ROLE is
'�û���ɫ��ϵ��';
alter table T_ACTIVITI_USER_ROLE add constraint FK_T_ACTIVITI_USER_ROLE_USER_ID foreign key (ROLE_ID) references T_ACTIVITI_ROLE (ID);
alter table T_ACTIVITI_USER_ROLE add constraint FK_T_ACTIVITI_USER_ROLE_ROLE_ID foreign key (USER_ID) references T_ACTIVITI_USER (ID);

CREATE TABLE T_ACTIVITI_DEPARTMENT 
(
	ID VARCHAR(255) NOT NULL, 
	NAME VARCHAR(255), 
	constraint PK_T_ACTIVITI_DEPARTMENT primary key (ID)
);
comment on table T_ACTIVITI_DEPARTMENT is
'��֯��';
comment on column T_ACTIVITI_DEPARTMENT.ID is
'UUID';
comment on column T_ACTIVITI_DEPARTMENT.NAME is
'��֯����';
CREATE TABLE T_ACTIVITI_LEAVE
(
	ID VARCHAR(255) NOT NULL, 
	NAME VARCHAR(255), 
	constraint PK_T_ACTIVITI_LEAVE primary key (ID)
);
comment on table T_ACTIVITI_LEAVE is
'��ٱ�';
comment on column T_ACTIVITI_LEAVE.ID is
'UUID';
comment on column T_ACTIVITI_LEAVE.NAME is
'�������';