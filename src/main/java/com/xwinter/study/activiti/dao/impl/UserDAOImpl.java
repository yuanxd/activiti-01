package com.xwinter.study.activiti.dao.impl;

import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.UserDAO;
import com.xwinter.study.activiti.entity.User;
@Component
public class UserDAOImpl extends BaseDAOImpl<User, String> implements UserDAO {

}
