package com.xwinter.study.activiti.entity;

import java.util.Map;

import javax.persistence.Transient;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class BaseEntity implements WfEntity {
	// -- 临时属性 --//
	// 流程任务
	private Task task;
	private Map<String, Object> variables;

	// 运行中的流程实例
	private ProcessInstance processInstance;

	// 历史的流程实例
	private HistoricProcessInstance historicProcessInstance;

	// 流程定义
	private ProcessDefinition processDefinition;

	@Transient
	public HistoricProcessInstance getHistoricProcessInstance() {
		return historicProcessInstance;
	}

	@Transient
	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}

	@Transient
	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	@Transient
	public Task getTask() {
		return task;
	}

	@Transient
	public Map<String, Object> getVariables() {
		return variables;
	}
	@Transient
	public String getBusinessKey() {
		throw new RuntimeException("not supported!");
	}

	public void setHistoricProcessInstance(
			HistoricProcessInstance historicProcessInstance) {
		this.historicProcessInstance = historicProcessInstance;
	}

	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
}
