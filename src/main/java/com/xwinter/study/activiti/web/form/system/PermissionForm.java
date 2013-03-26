package com.xwinter.study.activiti.web.form.system;

/**
 * 功能对象表单
 * 
 * @author 袁晓冬
 * 
 */
public class PermissionForm {
	/** 功能ID */
	private String id;
	/** 父功能ID */
	private String pid;
	/** 功能名称 */
	private String name;
	/** 代码 */
	private String code;
	/** URL属性冲突 */
	private String link;
	/** 菜单状态 */
	private int status;
	/** 是否目录 */
	private boolean folder = true;
	public String getCode() {
		return code;
	}
	public String getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

	public String getPid() {
		return pid;
	}

	public int getStatus() {
		return status;
	}

	public boolean isFolder() {
		return folder;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
