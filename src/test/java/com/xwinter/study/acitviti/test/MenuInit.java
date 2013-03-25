package com.xwinter.study.acitviti.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwinter.study.activiti.entity.system.Permission;
import com.xwinter.study.activiti.service.system.PermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:springtest/applicationContext*.xml" })
public class MenuInit {
	@Autowired
	private PermissionService permissionService;

	@Test
	public void init() {
		Permission p = new Permission();
		p.setName("测试");
		p.setCode("test");
		permissionService.save(p);
	}

	@Test
	public void testQuery() {
		List<Permission> permissionList = permissionService.getByParent("");
		System.err.println(permissionList);
	}
}
