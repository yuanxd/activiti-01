package com.xwinter.study.activiti.web.form.workflow;

/**
 * 流程变量
 * 
 * @author 袁晓冬
 * 
 */
public class Variable {
	private String key;
	private String value;
	private String type;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
