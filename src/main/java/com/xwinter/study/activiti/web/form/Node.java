package com.xwinter.study.activiti.web.form;

public class Node {
	private String id;
	private String name;

	private boolean folder = true;

	public boolean isFolder() {
		return folder;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	private String code;

	public String getCode() {
		return code;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
