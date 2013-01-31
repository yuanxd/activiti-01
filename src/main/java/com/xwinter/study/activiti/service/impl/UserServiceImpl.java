package com.xwinter.study.activiti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwinter.study.activiti.dao.UserDAO;
import com.xwinter.study.activiti.entity.User;
import com.xwinter.study.activiti.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;

	@Override
	public String save(User user) {
		return userDao.save(user);
	}

	@Override
	public User get(String id) {
		return userDao.get(id);
	}

}
