package com.yanado.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yanado.dao.UserDAO;
import com.yanado.dto.User;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
	private ProductDAO productDAO;

	@Autowired
	private UserDAO userDAO;

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
	public ModelAndView viewShoppingDetail(HttpServletRequest request, @RequestParam String shoppingId,
			Authentication authentication) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopping/shoppingDetail");
		Product shopping = productDAO.getProductByProductId(shoppingId);
		
		String s = StringEscapeUtils.unescapeHtml(shopping.getContent());
		shopping.setContent(s);
		
		mav.addObject("shopping", shopping);
		
		if (authentication != null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			User user = userDAO.getUserByUserName(userDetails.getUsername());

			String content = StringEscapeUtils.unescapeHtml(shopping.getContent());
			shopping.setContent(content);

			mav.addObject("isCertified", user.getCertified());
		}
		
		return mav;

	}

	@RequestMapping("/category")
	public ModelAndView viewShoppingByCategory(@RequestParam("category") String category) {
		List<Product> shopping = productDAO.getProductByCategory(category);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopping/shoppingList");
		mav.addObject("shoppingList", shopping);
		return mav;

	}
}
