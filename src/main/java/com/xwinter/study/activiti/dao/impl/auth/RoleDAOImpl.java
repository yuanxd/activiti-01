package com.xwinter.study.activiti.dao.impl.auth;

import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.identity.RoleDAO;
import com.xwinter.study.activiti.dao.impl.BaseDAOImpl;
import com.xwinter.study.activiti.entity.identity.Role;

@Component
public class RoleDAOImpl extends BaseDAOImpl<Role, String> implements RoleDAO {

	@Override
	public Role getByName(String name) {
		return (Role) getSession().createQuery("from Role where name=:name ")
				.setParameter("name", name).uniqueResult();
	}
}
