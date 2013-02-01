package com.xwinter.study.activiti.dao.impl;

import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.UserDAO;
import com.xwinter.study.activiti.entity.User;

@Component
public class UserDAOImpl extends BaseDAOImpl<User, String> implements UserDAO {
	/**
	 * 根据用户名查询用户
	 */
	public User getByName(String username) {
		return (User) getSession().createQuery("from User where name=:name ")
				.setParameter("name", username).uniqueResult();
	}
}
