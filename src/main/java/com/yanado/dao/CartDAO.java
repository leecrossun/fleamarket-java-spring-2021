package com.yanado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.yanado.dto.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanado.dto.Cart;

@Service
public class CartDAO {
	@PersistenceContext
	public EntityManager em;

	@Transactional
	public void createCart(Cart cart) throws DataAccessException {
		em.persist(cart);
	}

	@Transactional
	public void deleteCart(Cart cart) throws DataAccessException {
		em.remove(cart);
	}

	@Transactional
	public List<Cart> getCartListByUserId(String userId) throws DataAccessException {
		List<Cart> result;
		TypedQuery<Cart> query;
		try {
			query = em.createNamedQuery("getCartByUserId", Cart.class);
			query.setParameter("id", userId);
			result = query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
		return result;
	}

//	@Transactional
//	public List<User> getSupplierList(String userId) throws DataAccessException {
//		List<User> result;
//		TypedQuery<Cart> query;
//		try {
//			query = em.createNamedQuery("getSupplierList", User.class);
//			query.setParameter("id", userId);
//			result = query.getResultList();
//		} catch (NoResultException ex) {
//			return null;
//		}
//		return result;
//	}
}
