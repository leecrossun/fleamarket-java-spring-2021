package com.yanado.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanado.dto.Image;
import com.yanado.dto.Product;

@Service
public class ProductDAO {

	// JPA
	@PersistenceContext
	public EntityManager em;

	@Transactional
	public void createProduct(Product product, List<Image> images) throws DataAccessException {

		if (images != null) {
			for (int i = 0; i < images.size(); i++) {
				Image image = images.get(i);
				image.setProductId(product.getProductId());
				em.persist(image);
			}
		}
		
		System.out.println(product.getProductName());

		em.persist(product);
	}

	@Transactional
	public void updateProduct(Product product) throws DataAccessException {
		List<Image> images = product.getImageList();

		for (int i = 0; i < images.size(); i++) {
			em.merge(images.get(i));
		}

		em.merge(product);
	}

	@Transactional
	public void deleteProduct(String productId) throws DataAccessException {
		Product product = em.find(Product.class, productId);
		List<Image> images = product.getImageList();
		for (int i = 0; i < images.size(); i++) {
			em.remove(images.get(i));
		}

		em.remove(product);
	}

	// 모든 product 가져오기
	@Transactional
	public List<Product> getProductList() throws DataAccessException {
		List<Product> result;
		TypedQuery<Product> query;
		try {
			query = em.createNamedQuery("getProductList", Product.class);
			result = (List<Product>) query.getResultList();
		} catch (NoResultException ex) {
			System.out.println("fail getproductList");
			return null;
		}
		System.out.println("success getproductList");
		return result;
	}

	// product detail 가져오기
	public Product getProductByProductId(String productId) throws DataAccessException {
		Product result;
		TypedQuery<Product> query;
		try {
			query = em.createNamedQuery("getProductByProductId", Product.class);
			query.setParameter("id", productId);
			result = (Product) query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("fail getproduct");
			return null;
		}
		System.out.println("success getproduct");
		return result;
	}

	// 카데고리 별로 product 가져오기
	@Transactional
	public List<Product> getProductByCategory(String category) throws DataAccessException {
		List<Product> result;
		TypedQuery<Product> query = em.createNamedQuery("getProductByCategory", Product.class);
		query.setParameter("id", category);
		try {
			result = (List<Product>) query.getResultList();
		} catch (NoResultException ex) {
			System.out.println("fail" + category);
			return null;
		}
		System.out.println("success" + category);
		return result;
	}

	// 내가 올린 product 가져오기
	@Transactional
	public List<Product> getProductBySupplierId(String supplierId) throws DataAccessException {
		ArrayList<Product> result;
		TypedQuery<Product> query = em.createNamedQuery("getProductBySupplierId", Product.class);
		query.setParameter("id", supplierId);
		try {
			result = (ArrayList<Product>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
		System.out.println("success " + result.get(0).getProductName());
		return result;
	}

	public List<Product> getProductByProductName(String productName) throws DataAccessException {

		ArrayList<Product> result;
		TypedQuery<Product> query = em.createNamedQuery("getProductByProductName", Product.class);
		query.setParameter("productName", productName);
		try {
			result = (ArrayList<Product>) query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
		return result;
	}

	// Stock 수 줄이기
	@Transactional
	public void updateStockByProductId(String productId) throws DataAccessException {
		Query query = em.createNamedQuery("updateStockByProductId");
		query.setParameter("id", productId);
		query.executeUpdate();
		
	}

	// 이미지 불러오기
	public Image getImageByImageId(String imageId) throws DataAccessException {
		Image result;
		TypedQuery<Image> query;
		try {
			query = em.createNamedQuery("getImageByImageId", Image.class);
			query.setParameter("id", imageId);
			result = (Image) query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("fail getproduct");
			return null;
		}
		System.out.println("success getproduct");
		return result;
	}

	public List<Image> getImageByProductId(String productId) throws DataAccessException {
		List<Image> result;
		TypedQuery<Image> query;
		try {
			query = em.createNamedQuery("getImageByProductId", Image.class);
			query.setParameter("id", productId);
			result = (List<Image>) query.getResultList();
		} catch (NoResultException ex) {
			System.out.println("fail getproduct");
			return null;
		}
		System.out.println("success getproduct");
		return result;
	}

}
