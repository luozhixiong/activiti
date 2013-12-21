package com.goalclan.activiti.web.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goalclan.activiti.web.entity.Order;

@Service
public class OrderService {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void saveOrder() {
		Order order = new Order();
		order.setOrderNumber("sodxxxx001");
		entityManager.persist(order);
		entityManager.flush();
	}
}
