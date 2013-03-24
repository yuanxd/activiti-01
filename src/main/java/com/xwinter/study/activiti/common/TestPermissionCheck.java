package com.xwinter.study.activiti.common;

import org.springframework.stereotype.Component;

import com.xwinter.study.access.AccessPermissionCheck;
import com.xwinter.study.access.Function;

@Component
public class TestPermissionCheck implements AccessPermissionCheck {

	@Override
	public boolean checkPermission(Function func, Object sessionKey) {
		System.err.println(func.toString());
		return false;
	}

}
