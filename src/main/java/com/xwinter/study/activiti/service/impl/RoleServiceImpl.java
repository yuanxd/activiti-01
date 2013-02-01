package com.xwinter.study.activiti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.dao.RoleDAO;
import com.xwinter.study.activiti.entity.Role;
import com.xwinter.study.activiti.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements
		RoleService {
	@Autowired
	private RoleDAO userDao;

	@Override
	protected BaseDAO<Role, String> getDAO() {
		return userDao;
	}

}
