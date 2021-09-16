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

import com.yanado.dto.Buyer;
import com.yanado.dto.ComKey;

@Service
public class BuyerDAO {
	@PersistenceContext
	public EntityManager em;

	@Transactional
	public void createBuyer(Buyer buyer) throws DataAccessException {
		ComKey pk = new ComKey(buyer.getProductId(), buyer.getUserId());

		Buyer b = em.find(Buyer.class, pk);

		if (b == null)
			em.persist(buyer);
	}

	@Transactional
	public void deleteBuyer(Buyer buyer) throws DataAccessException {
		em.remove(buyer);
	}

	@Transactional
	public List<Buyer> getBuyerByProductId(String productId) throws DataAccessException {
		List<Buyer> result;
		TypedQuery<Buyer> query;
		try {
			query = em.createNamedQuery("getBuyerByProductId", Buyer.class);
			query.setParameter("id", productId);
			result = (List<Buyer>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return result;
	}

	@Transactional
	public List<Buyer> getProductByBuyerId(String userId) throws DataAccessException {
		List<Buyer> result;
		TypedQuery<Buyer> query;
		try {
			query = em.createNamedQuery("getBuyerByProductId", Buyer.class);
			query.setParameter("id", userId);
			result = (List<Buyer>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}

		return result;
	}

	@Transactional
	public void updatePayment(Buyer buyer) throws DataAccessException {

		Query query = em.createNamedQuery("updatePayment");

		query.setParameter("userId", buyer.getUser().getUserId())
				.setParameter("productId", buyer.getProduct().getProductId())
				.setParameter("payment", buyer.getPayment());

		query.executeUpdate();
	}

}
