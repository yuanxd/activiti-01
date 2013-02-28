package com.xwinter.study.activiti.workflow.identity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.entity.identity.Role;
import com.xwinter.study.activiti.entity.identity.User;
import com.xwinter.study.activiti.service.identity.UserService;

@Component
public class RoleManager extends GroupManager {

	@Autowired
	private UserService userService;

	public Group createNewGroup(String groupId) {
		throw new ActivitiException("unsupported method");
	}

	public void insertGroup(Group group) {
		throw new ActivitiException("unsupported method");
	}

	public void updateGroup(GroupEntity updatedGroup) {
		throw new ActivitiException("unsupported method");
	}

	public void deleteGroup(String groupId) {
		throw new ActivitiException("unsupported method");
	}

	public GroupQuery createNewGroupQuery() {
		throw new ActivitiException("unsupported method");
	}

	public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
		throw new ActivitiException("unsupported method");
	}

	public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
		throw new ActivitiException("unsupported method");
	}

	public GroupEntity findGroupById(String groupId) {
		throw new ActivitiException("unsupported method");
	}

	public List<Group> findGroupsByUser(String userId) {
		User user = userService.get(userId);
		if (null == user) {
			return null;
		}
		Collection<Role> roles = user.getRoles();
		List<Group> groupList = new ArrayList<Group>();
		groupList.addAll(roles);
		return groupList;
	}

	public List<Group> findPotentialStarterUsers(String proceDefId) {
		throw new ActivitiException("unsupported method");
	}

}
