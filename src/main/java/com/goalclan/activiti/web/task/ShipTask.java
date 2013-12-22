package com.goalclan.activiti.web.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShipTask implements JavaDelegate{
	
	private static final Logger logger = LogManager.getLogger(ShipTask.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("发货");
	}

}
