package com.xwinter.study.activiti.entity.system;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.xwinter.study.activiti.entity.BaseEntity;

@Entity
@Table(name = "T_SYSTEM_PERMISSION")
public class Permission extends BaseEntity {
	/** uuid */
	private String id;
	/** 功能编码 */
	private String code;
	/** 功能名称 */
	private String name;
	/** 是否目录 */
	private boolean folder;
	/** 上级功能 */
	private Permission parent;
	/** 下级列表 */
	private Collection<Permission> children;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "parent", targetEntity = Permission.class)
	public Collection<Permission> getChildren() {
		return children;
	}

	public String getCode() {
		return code;
	}

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid2")
	@GeneratedValue(generator = "idGenerator")
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isFolder() {
		return folder;
	}

	public void setChildren(Collection<Permission> children) {
		this.children = children;
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

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne()
	@JoinColumn(name = "pid")
	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
		this.parent = parent;
	}
}
