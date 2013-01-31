package com.xwinter.study.activiti.service;

import java.io.Serializable;

import com.xwinter.study.activiti.entity.BaseEntity;

public interface BaseService<E extends BaseEntity, PK extends Serializable> {
	public PK save(E entity);

	public E get(PK id);
}
