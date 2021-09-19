package com.yanado.controller.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yanado.dao.CartDAO;
import com.yanado.dto.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yanado.controller.user.UserSessionUtils;
import com.yanado.dao.ProductDAO;
import com.yanado.dao.UserDAO;
import com.yanado.dto.Product;

@Controller
@RequestMapping("/cart")
public class CartController {


	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CartDAO cartDAO;

	@RequestMapping("/add")
	protected String add(@ModelAttribute("shopping") Product shopping, HttpServletRequest request, RedirectAttributes red) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 여기 추가
		Cart cart = new Cart();
		return "redirect:/";
	}
	
	@RequestMapping("/view")
	protected ModelAndView view(HttpServletRequest request,RedirectAttributes red) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		
		List<String> cart = (List<String>)session.getAttribute("cart");
		
		if (session.getAttribute("cart") != null) {
			List<Product> shoppingList = new ArrayList<Product>();
			for(String s: cart) {
				shoppingList.add(productDAO.getProductByProductId(s));
			}
			
			mav.setViewName("shopping/cart");
			mav.addObject("shoppingList", shoppingList);
			return mav;
		}
		else {
			return null;
		}
		
	}

	@RequestMapping("/delete")
	protected String delete(@RequestParam String shoppingId, HttpServletRequest request, RedirectAttributes red) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 여기 추가
	
		return "redirect:/";
	}
	
	@RequestMapping("/update")
	protected String update(@RequestParam String shoppingId, HttpServletRequest request, RedirectAttributes red) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 여기 추가
	
		return "redirect:/";
	}

}