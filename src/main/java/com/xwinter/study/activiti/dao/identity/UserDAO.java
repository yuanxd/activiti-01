package com.xwinter.study.activiti.dao.identity;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.entity.identity.User;

/**
 */
public interface UserDAO extends BaseDAO<User, String> {
	public User getByName(String username);
	
	public User getByCode(String username);
}
