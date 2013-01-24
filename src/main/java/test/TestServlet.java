package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 6428943667955638630L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.err.println("doGet");
		WebApplicationContext wc = WebApplicationContextUtils
				.getWebApplicationContext(req.getSession().getServletContext());
		ProcessEngine processEngine = (ProcessEngine) wc
				.getBean("processEngine");

		RepositoryService repositoryService = (RepositoryService) wc
				.getBean("repositoryService");
		repositoryService.createDeployment()
				.addClasspathResource("VacationRequest.bpmn20.xml").deploy();

		// Starting a process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");

		RuntimeService runtimeService = (RuntimeService) wc
				.getBean("runtimeService");
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("vacationRequest", variables);

		// Verify that we started a new process instance
		System.err.println("Number of process instances: "
				+ runtimeService.createProcessInstanceQuery().count());

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.err.println("service");
		super.service(req, resp);
	}

}
