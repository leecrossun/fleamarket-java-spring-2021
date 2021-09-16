package com.yanado.controller.product;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.dao.ProductDAO;
import com.yanado.dto.Product;
import com.yanado.service.ProductService;

@Controller
@SessionAttributes("shopping")
@RequestMapping("shopping/update")
public class UpdateProductController {

	@Autowired
	private ProductDAO shoppingDAO;

	@Autowired
	private ProductService service;

	@ModelAttribute("shopping")
	public Product formBacking(HttpServletRequest request) {
		// 상품 연결과정에서 문제있을 경우 수정
		Product product = new Product();
		return product;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form(@RequestParam String shoppingId) {
		System.out.println(shoppingId);
		ModelAndView mav = new ModelAndView();
		Product product = shoppingDAO.getProductByProductId(shoppingId);

		mav.setViewName("shopping/form");
		mav.addObject("shopping", product);
		mav.addObject("formtype", "update");

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String updateShopping(@Valid @ModelAttribute("shopping") Product shopping, BindingResult result,
			SessionStatus status) {

		if (result.hasErrors()) {
			return "shopping/form";
		}

		status.setComplete();
		service.updateProduct(shopping);
		
		return "redirect:/shopping/view/detail?shoppingId=" + shopping.getProductId();
	}

}
