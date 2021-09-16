package com.yanado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	public void deleteBuyer(Cart cart) throws DataAccessException {
		em.remove(cart);
	}

	@Transactional
	public List<Cart> getProductByCartId(String userId) throws DataAccessException {
		List<Cart> result;
		TypedQuery<Cart> query;
		try {
			query = em.createNamedQuery("getCartByProductId", Cart.class);
			query.setParameter("id", userId);
			result = (List<Cart>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return result;
	}
}
