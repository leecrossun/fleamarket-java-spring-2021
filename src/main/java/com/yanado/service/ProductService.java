package com.yanado.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanado.dao.ProductDAO;
import com.yanado.dto.Image;
import com.yanado.dto.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	public void createProduct(Product product, List<Image> image)
	{
		
		product.setRegDate(new Date());
		
		productDAO.createProduct(product, image);
	}
	
	public void updateProduct(Product product)
	{
		productDAO.updateProduct(product);
	}

}
