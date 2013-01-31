package com.xwinter.study.activiti.service;

import java.util.List;

import com.xwinter.study.activiti.entity.Test;

public interface TestService extends BaseService<Test, String> {
	public String startWorkflow(Test test);
	public List<Test> getTodoList(String username);
}
