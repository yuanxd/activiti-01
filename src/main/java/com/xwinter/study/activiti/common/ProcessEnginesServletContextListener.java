package com.xwinter.study.activiti.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.activiti.engine.ProcessEngines;

public class ProcessEnginesServletContextListener implements
		ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		ProcessEngines.init();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		ProcessEngines.destroy();
	}

}
