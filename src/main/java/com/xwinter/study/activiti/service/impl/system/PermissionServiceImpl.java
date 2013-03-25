package com.xwinter.study.activiti.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.dao.system.PermissionDAO;
import com.xwinter.study.activiti.entity.system.Permission;
import com.xwinter.study.activiti.service.impl.BaseServiceImpl;
import com.xwinter.study.activiti.service.system.PermissionService;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, String>
		implements PermissionService {
	@Autowired
	private PermissionDAO permissionDAO;

	@Override
	public BaseDAO<Permission, String> getBaseDAO() {
		return permissionDAO;
	}

	@Override
	public List<Permission> getByParent(String pid) {
		return permissionDAO.getByParent(pid);
	}

}
