package com.xwinter.study.activiti.service.impl;

import java.io.Serializable;
import java.util.Collection;

import com.xwinter.study.activiti.dao.BaseDAO;
import com.xwinter.study.activiti.entity.BaseEntity;
import com.xwinter.study.activiti.service.BaseService;

public abstract class BaseServiceImpl<E extends BaseEntity, PK extends Serializable>
		implements BaseService<E, PK> {
	
	public abstract BaseDAO<E, PK> getBaseDAO();

	@Override
	public PK save(E entity) {
		return getBaseDAO().save(entity);
	}

	@Override
	public void save(Collection<E> entities) {
		getBaseDAO().save(entities);
	}

	@Override
	public E get(PK id) {
		return getBaseDAO().get(id);
	}
}
