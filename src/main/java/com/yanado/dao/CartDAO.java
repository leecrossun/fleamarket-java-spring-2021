package com.yanado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanado.dto.Cart;
import com.yanado.dto.Cate;

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
	public Cart getCartByCartId (String cartId) throws DataAccessException {
		Cart result;
		TypedQuery<Cart> query;
		try {
			query = em.createNamedQuery("getCartByCartId", Cart.class);
			query.setParameter("id", cartId);
			result = query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
		return result;
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

	@Transactional
	public List<Cate> getSupplierList(String userId) throws DataAccessException {
		List<Cate> result;
		TypedQuery<Cate> query;
		try {
			query = em.createNamedQuery("getSupplierList", Cate.class);
			query.setParameter("id", userId);
			result = query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
		return result;
	}
	
	@Transactional
	public void updateQuantityByCartId(String cartId, int quantity) {
		Query query = em.createNamedQuery("updateQuantityByCartId");
		query.setParameter("id", cartId);
		query.setParameter("quantity", quantity);
		query.executeUpdate();

	}
}
