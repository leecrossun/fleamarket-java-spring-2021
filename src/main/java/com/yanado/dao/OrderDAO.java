package com.yanado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanado.dto.Item;
import com.yanado.dto.Order;

@Service
public class OrderDAO {

	@PersistenceContext
	public EntityManager em;

	@Transactional
	public void createOrder(Order order, List<Item> items) throws DataAccessException {
		em.persist(order);

		System.out.println(order.getOrderId());

		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			item.setOrder(order);
			em.persist(item);
		}
	}

	@Transactional
	public void deleteOrder(Order order, List<Item> items) throws DataAccessException {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			em.remove(item);
		}
		em.remove(order);
		System.out.println("delete order");
	}

	@Transactional
	public Order getOrderByOrderId(String orderId) throws DataAccessException {
		Order result;
		TypedQuery<Order> query;
		try {
			query = em.createNamedQuery("getOrderByOrderId", Order.class);
			query.setParameter("id", orderId);
			result = (Order) query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("fail getOrder");
			return null;
		}
		System.out.println("success getOrder");
		return result;
	}

	@Transactional
	public List<Order> getOrderByUserId(String userId) throws DataAccessException {
		List<Order> result;
		TypedQuery<Order> query;
		try {
			query = em.createNamedQuery("getOrderByUserId", Order.class);
			query.setParameter("id", userId);
			result = (List<Order>) query.getResultList();
		} catch (NoResultException ex) {
			System.out.println("fail getShoppingList");
			return null;
		}
		System.out.println("success getShoppingList");
		return result;
	}
	
	@Transactional
	public List<Order> getOrderBySupplierId(String userId) throws DataAccessException {
		List<Order> result;
		TypedQuery<Order> query;
		try {
			query = em.createNamedQuery("getOrderBySupplierId", Order.class);
			query.setParameter("id", userId);
			result = (List<Order>) query.getResultList();
		} catch (NoResultException ex) {
			System.out.println("fail getShoppingList");
			return null;
		}
		System.out.println("success getShoppingList");
		return result;
	}

	// item
	@Transactional
	public Item getItemByItemId(String itemId) throws DataAccessException {
		Item result;
		TypedQuery<Item> query;
		try {
			query = em.createNamedQuery("getItemByItemId", Item.class);
			query.setParameter("id", itemId);
			result = (Item) query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("fail getOrder");
			return null;
		}
		System.out.println("success getOrder");
		return result;
	}

}
