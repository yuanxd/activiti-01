package com.xwinter.study.activiti.service.impl.identity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.dao.identity.UserDAO;
import com.xwinter.study.activiti.entity.identity.Department;
import com.xwinter.study.activiti.entity.identity.User;
import com.xwinter.study.activiti.service.identity.UserService;
import com.xwinter.study.activiti.service.impl.BaseServiceImpl;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements
		UserService {
	@Autowired
	private UserDAO userDao;

	@Override
	protected BaseDAO<User, String> getDAO() {
		return userDao;
	}

	@Override
	public User getByName(String username) {
		return userDao.getByName(username);
	}

	@Override
	public List<Department> queryDeptByUserName(String username) {
		return null;
	}

}
