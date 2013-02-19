package com.xwinter.study.activiti.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.dao.TestDAO;
import com.xwinter.study.activiti.entity.Test;
import com.xwinter.study.activiti.entity.User;
import com.xwinter.study.activiti.service.MainService;

@Component
public class MainServiceImpl extends WfBaseServiceImpl<Test, String> implements
		MainService {
	@Autowired
	private TestDAO testDao;

	@Override
	protected BaseDAO<Test, String> getDAO() {
		return testDao;
	}

	@Override
	public String getProcessDefinitionKey() {
		return "TestProcess";
	}

	@Override
	public void inCreate(Test entity, Map<String, Object> variables,
			User currentUser) {
		super.inCreate(entity, variables, currentUser);
		testDao.save(entity);
	}

	@Override
	public void inComplete(Test entity, Map<String, Object> variables,
			User currentUser) {
		super.inComplete(entity, variables, currentUser);
		testDao.save(entity);
	}

}
