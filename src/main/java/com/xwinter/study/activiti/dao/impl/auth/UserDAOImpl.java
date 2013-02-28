package com.xwinter.study.activiti.dao.impl.auth;

import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.identity.UserDAO;
import com.xwinter.study.activiti.dao.impl.BaseDAOImpl;
import com.xwinter.study.activiti.entity.identity.User;

@Component
public class UserDAOImpl extends BaseDAOImpl<User, String> implements UserDAO {
	/**
	 * 根据用户名查询用户
	 */
	public User getByName(String username) {
		return (User) getSession().createQuery("from User where name=:name ")
				.setParameter("name", username).uniqueResult();
	}

	@Override
	public User getByCode(String code) {
		return (User) getSession().createQuery("from User where code=:code ")
				.setParameter("code", code).uniqueResult();
	}
}
