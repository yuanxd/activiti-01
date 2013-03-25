package com.xwinter.study.activiti.dao.impl.system;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.impl.BaseDAOImpl;
import com.xwinter.study.activiti.dao.system.PermissionDAO;
import com.xwinter.study.activiti.entity.system.Permission;

@Component
public class PermissionDAOImpl extends BaseDAOImpl<Permission, String>
		implements PermissionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getByParent(String pid) {
		if (null == pid || pid.length() == 0) {

			return getSession().createQuery(
					"from Permission where pid is null ").list();
		} else {
			return getSession().createQuery("from Permission where pid=:pid ")
					.setParameter("pid", pid).list();
		}
	}
}
