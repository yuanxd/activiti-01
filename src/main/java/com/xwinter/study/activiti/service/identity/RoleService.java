package com.xwinter.study.activiti.service.identity;

import com.xwinter.study.activiti.entity.identity.Role;
import com.xwinter.study.activiti.service.BaseService;

/**
 * 角色服务
 * 
 * @author 袁晓冬
 * 
 */
public interface RoleService extends BaseService<Role, String> {
	Role getByName(String name);
}
