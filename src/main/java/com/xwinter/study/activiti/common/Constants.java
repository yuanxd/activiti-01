package com.xwinter.study.activiti.common;

import java.util.regex.Pattern;

public abstract class Constants {
	public static final String SESSION_KEY = "USER_ID";
	/**
	 * 不判断Session的URI的正则表达式
	 */
	private static final String UNCHECKURI_REGEX = "(.*/login.*)|(.*/*.js)|(.*/*.css)|(.*/*.jpg)|(.*/*.gif)";

	public static final Pattern UNCHECKURI_PATTERN = Pattern
			.compile(UNCHECKURI_REGEX);
	/** 日期格式化 */
	public static final String FORMAT_DATE = "yyyy-MM-dd hh:mm:ss";
}
