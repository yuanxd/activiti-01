package com.xwinter.study.activiti.service.system;

import java.util.List;

import com.xwinter.study.activiti.entity.system.Permission;
import com.xwinter.study.activiti.service.BaseService;

public interface PermissionService extends BaseService<Permission, String> {
	public List<Permission> getByParent(String pid);
}
