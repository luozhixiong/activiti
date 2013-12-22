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
	public Order saveOrder(Order order) {
		entityManager.persist(order);
		return order;
	}
}
