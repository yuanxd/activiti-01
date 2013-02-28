package com.xwinter.study.activiti.service.impl.leave;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.dao.leave.LeaveDAO;
import com.xwinter.study.activiti.entity.Leave;
import com.xwinter.study.activiti.service.impl.BaseWorkflowServiceImpl;
import com.xwinter.study.activiti.service.leave.LeaveService;

/**
 * 请假service
 * 
 * @author 袁晓冬
 * 
 */
@Service
public class LeaveServiceImpl extends BaseWorkflowServiceImpl<Leave, String>
		implements LeaveService {
	@Autowired
	private LeaveDAO leaveDAO;

	@Override
	public BaseDAO<Leave, String> getBaseDAO() {
		return leaveDAO;
	}

	@Override
	public String getProcessDefinitionKey() {
		return "leaveProcess";
	}

	@Override
	public ProcessInstance create(Leave entity, Map<String, Object> variables,
			String user) {
		// 保存请假记录
		save(entity);
		return super.create(entity, variables, user);
	}

}
