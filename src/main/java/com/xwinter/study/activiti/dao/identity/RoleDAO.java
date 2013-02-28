package com.xwinter.study.activiti.dao.identity;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.entity.identity.Role;

/**
 */
public interface RoleDAO extends BaseDAO<Role, String> {
	Role getByName(String name);
}
