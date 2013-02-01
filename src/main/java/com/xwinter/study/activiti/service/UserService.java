package com.xwinter.study.activiti.service;

import java.util.List;

import com.xwinter.study.activiti.entity.Department;
import com.xwinter.study.activiti.entity.User;

/**
 * 用户服务
 * 
 * @author 袁晓冬
 * 
 */
public interface UserService extends BaseService<User, String> {
	/**
	 * 根据用户名称查询用户
	 * 
	 * @param username
	 *            用户名称
	 * @return
	 */
	public User getByName(String username);

	/**
	 * 根据用户名称查询用户组织
	 * 
	 * @param username
	 *            用户名称
	 * @return
	 */
	public List<Department> queryDeptByUserName(String username);
}
