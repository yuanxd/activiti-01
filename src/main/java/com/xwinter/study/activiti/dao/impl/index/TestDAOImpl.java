package com.xwinter.study.activiti.dao.impl.index;

import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.impl.BaseDAOImpl;
import com.xwinter.study.activiti.dao.index.TestDAO;
import com.xwinter.study.activiti.entity.Test;

@Component
public class TestDAOImpl extends BaseDAOImpl<Test, String> implements TestDAO {

}
