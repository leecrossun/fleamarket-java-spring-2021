package com.yanado.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.dao.ProductDAO;
import com.yanado.dto.Product;

@Controller
@RequestMapping("/product/view")
public class FindUserProductController {
	
	@Autowired
	ProductDAO productDAO;
	
	// 내 판매 리스트
	@RequestMapping("/my")
	public ModelAndView viewOrderByUserId(HttpServletRequest request){
		UserSessionUtils uSession = new UserSessionUtils();
		String userId = uSession.getLoginUserId(request.getSession());
		
		List<Product> product = productDAO.getProductBySupplierId(userId);
		ModelAndView mav = new ModelAndView();
		
		if (product.size() > 0) {
		mav.setViewName("product/myList");
		mav.addObject("shoppingList", product);
		return mav;
		}
		else {
			return null;
		}
		
	}

}
