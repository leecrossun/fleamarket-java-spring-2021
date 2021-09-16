package com.yanado.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.yanado.dto.Cate;

@Service
public class CateDAO {
	@PersistenceContext
	public EntityManager em;

	public Cate getCategoryByCateId(String cateId) throws DataAccessException {
		Cate result;
		TypedQuery<Cate> query;
		try {
			query = em.createNamedQuery("getCategoryByCateId", Cate.class);
			query.setParameter("id", cateId);
			result = (Cate) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

		return result;
	}
	
	public Cate getCategoryBySupplierId(String supplierId) throws DataAccessException {
		Cate result;
		TypedQuery<Cate> query;
		try {
			query = em.createNamedQuery("getCategoryBySupplierId", Cate.class);
			query.setParameter("id", supplierId);
			result = (Cate) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

		return result;
	}
	
	public Cate getCategoryByCateName(String cateName) throws DataAccessException {
		Cate result;
		TypedQuery<Cate> query;
		try {
			query = em.createNamedQuery("getCategoryByCateName", Cate.class);
			query.setParameter("name", cateName);
			result = (Cate) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

		return result;
	}
}
