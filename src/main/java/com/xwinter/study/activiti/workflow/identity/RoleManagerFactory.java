package com.xwinter.study.activiti.workflow.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleManagerFactory implements SessionFactory {
	@Autowired
	private RoleManager roleManager;

	@Override
	public Class<?> getSessionType() {
		return GroupManager.class;
	}

	@Override
	public Session openSession() {
		return roleManager;
	}

}
