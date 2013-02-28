package com.xwinter.study.acitviti.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwinter.study.activiti.entity.identity.Role;
import com.xwinter.study.activiti.entity.identity.User;
import com.xwinter.study.activiti.service.identity.RoleService;
import com.xwinter.study.activiti.service.identity.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:springtest/applicationContext*.xml" })
public class IdentityInit {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Test
	public void init() {
		Role pgRole = new Role();
		pgRole.setCode("pg");
		pgRole.setName("程序员");
		roleService.save(pgRole);

		Role leaderRole = new Role();
		leaderRole.setCode("leader");
		leaderRole.setName("领导");
		roleService.save(leaderRole);

		User yxd = new User();
		yxd.setCode("yxd");
		yxd.setName("袁晓冬");
		List<Role> yxdRoleList = new ArrayList<Role>();
		yxdRoleList.add(pgRole);
		yxd.setRoles(yxdRoleList);
		userService.save(yxd);

		User leader = new User();
		leader.setCode("leader");
		leader.setName("领导A");
		List<Role> leaderRoleList = new ArrayList<Role>();
		leaderRoleList.add(leaderRole);
		leader.setRoles(leaderRoleList);
		userService.save(leader);

	}
}
