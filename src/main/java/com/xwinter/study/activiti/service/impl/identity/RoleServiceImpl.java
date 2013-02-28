package com.xwinter.study.activiti.service.impl.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.dao.identity.RoleDAO;
import com.xwinter.study.activiti.entity.identity.Role;
import com.xwinter.study.activiti.service.identity.RoleService;
import com.xwinter.study.activiti.service.impl.BaseServiceImpl;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements
		RoleService {
	@Autowired
	private RoleDAO userDao;

	@Override
	protected BaseDAO<Role, String> getDAO() {
		return userDao;
	}

	@Override
	public Role getByName(String name) {
		return userDao.getByName(name);
	}

}
