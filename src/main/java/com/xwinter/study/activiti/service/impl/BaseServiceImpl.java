package com.xwinter.study.activiti.service.impl;

import java.io.Serializable;
import java.util.Collection;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.entity.BaseEntity;
import com.xwinter.study.activiti.service.BaseService;

public class BaseServiceImpl<E extends BaseEntity, PK extends Serializable>
		implements BaseService<E, PK> {

	protected BaseDAO<E, PK> getDAO() {
		return null;
	}

	@Override
	public PK save(E entity) {
		return getDAO().save(entity);
	}

	@Override
	public void save(Collection<E> entities) {
		getDAO().save(entities);
	}

	@Override
	public E get(PK id) {
		return getDAO().get(id);
	}

}
