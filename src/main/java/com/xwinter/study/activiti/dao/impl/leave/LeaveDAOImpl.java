package com.xwinter.study.activiti.dao.impl.leave;

import org.springframework.stereotype.Component;

import com.xwinter.study.activiti.dao.impl.BaseDAOImpl;
import com.xwinter.study.activiti.dao.leave.LeaveDAO;
import com.xwinter.study.activiti.entity.Leave;

@Component
public class LeaveDAOImpl extends BaseDAOImpl<Leave, String> implements
		LeaveDAO {
}
