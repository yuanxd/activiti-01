package com.xwinter.study.activiti.dao.impl;

import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.RoleDAO;
import com.xwinter.study.activiti.entity.Role;

@Component
public class RoleDAOImpl extends BaseDAOImpl<Role, String> implements RoleDAO {

	@Override
	public Role getByName(String name) {
		return (Role) getSession().createQuery("from Role where name=:name ")
				.setParameter("name", name).uniqueResult();
	}
}
