package com.xwinter.study.activiti.common;

import org.springframework.stereotype.Component;

import com.xwinter.study.access.IAccessPermissionCheck;
import com.xwinter.study.access.entity.FunctionEntity;

@Component
public class TestPermissionCheck implements IAccessPermissionCheck {
	public final static String PAGE_UNAUTHORISED = "/login";

	@Override
	public String checkPermission(FunctionEntity func, Object sessionKey) {
		System.err.println(func.toString());
		return PAGE_UNAUTHORISED;
	}

}
