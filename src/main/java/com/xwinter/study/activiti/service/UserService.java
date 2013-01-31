package com.xwinter.study.activiti.service;

import com.xwinter.study.activiti.entity.User;

public interface UserService extends BaseService {
	public String save(User user);

	public User get(String id);
}
