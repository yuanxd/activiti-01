package com.xwinter.study.activiti.dao.system;

import java.util.List;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.entity.system.Permission;

/**
 * 权限操作DAO
 * 
 * @author 袁晓冬
 * 
 */
public interface PermissionDAO extends BaseDAO<Permission, String> {
	/**
	 * 根据父节点ID查询下级
	 * 
	 * @return
	 */
	public List<Permission> getByParent(String pid);
}
