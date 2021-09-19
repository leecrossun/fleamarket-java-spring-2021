package com.yanado.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.controller.user.UserSessionUtils;
import com.yanado.dao.ProductDAO;
import com.yanado.dto.Product;


import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shopping/view")
public class ViewProductController {

	@Autowired
	public ProductDAO productDAO;

	// 모든 쇼핑 리스트
	@RequestMapping("/all")
	public ModelAndView viewShoppingList() {

		List<Product> shopping = productDAO.getProductList();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopping/shoppingList");
		mav.addObject("shoppingList", shopping);
		
		return mav;

	}

	// 쇼핑 상품 상세보기
	@RequestMapping("/detail")
	public ModelAndView viewShoppingDetail(HttpServletRequest request, @RequestParam String shoppingId) {
		ModelAndView mav = new ModelAndView();
		Product shopping = productDAO.getProductByProductId(shoppingId);

		UserSessionUtils uSession = new UserSessionUtils();
		String userId = uSession.getLoginUserId(request.getSession());
		
		String content = StringEscapeUtils.unescapeHtml(shopping.getContent());
		System.out.println(content);
		shopping.setContent(content);
		System.out.println(shopping.getContent());

		mav.setViewName("shopping/shoppingDetail");
		mav.addObject("shopping", shopping);
		return mav;

	}
	/*
	 * // 내가 올린 쇼핑 리스트
	 * 
	 * @RequestMapping("/search") public ModelAndView
	 * searchShoppingByProductName(@RequestParam String p){ List<Shopping> shopping
	 * = shoppingDAO.getShoppingByProductName("Test"); ModelAndView mav = new
	 * ModelAndView("shoppingList"); mav.setViewName("shopping/myList");
	 * mav.addObject("shoppingList", shopping); return mav; }
	 */

	
	@RequestMapping("/category")
	public ModelAndView viewShoppingByCategory(@RequestParam("category") String category) {
		List<Product> shopping = productDAO.getProductByCategory(category);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopping/shoppingList");
		mav.addObject("shoppingList", shopping);
		return mav;

	}
}
