package com.goalclan.activiti.web.wf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goalclan.activiti.web.entity.Order;
import com.goalclan.activiti.web.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class OrderProcessTest {

	@Inject
	private OrderService orderService;

	@Inject
	private RuntimeService runtimeService;
	@Inject
	private TaskService taskService;

	@Test
	public void testStartOrderProcess() {
		String user = "messi";
		Order order = new Order();
		order.setOrderNumber(user + "-" + new Random().nextInt());
		order.setUser(user);
		Map<String, Object> variables = new HashMap<>();
		variables.put("user", user);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("sod.order", variables);
		order.setProcessInstanceId(processInstance.getProcessInstanceId());
		orderService.saveOrder(order);
	}
	
	@Test
	public void testPay() {
		String user = "messi";
		List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("sod.order").taskAssignee(user).taskDefinitionKey("task_pay").list();
		for (Task task : tasks) {
			Map<String, Object> variables = task.getProcessVariables();
			variables.put("money", 100);
			taskService.complete(task.getId(), variables);
		}
	}
	
	@Test
	public void testShip() {
		Execution execution = runtimeService.createExecutionQuery().processInstanceId("301").activityId("task_ship").singleResult();
		runtimeService.signalEventReceived("start", execution.getId());
	}

}
