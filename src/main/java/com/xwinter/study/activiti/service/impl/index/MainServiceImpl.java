package com.xwinter.study.activiti.service.impl.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.dao.index.TestDAO;
import com.xwinter.study.activiti.entity.Leave;
import com.xwinter.study.activiti.service.impl.BaseWorkflowServiceImpl;
import com.xwinter.study.activiti.service.index.MainService;

@Component
public class MainServiceImpl extends BaseWorkflowServiceImpl<Leave, String> implements
		MainService {
	@Autowired
	private TestDAO testDao;

	@Override
	protected BaseDAO<Leave, String> getDAO() {
		return testDao;
	}

	@Override
	public String getProcessDefinitionKey() {
		return "TestProcess";
	}
}
