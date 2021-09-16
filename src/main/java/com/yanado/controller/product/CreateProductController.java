package com.yanado.controller.product;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yanado.controller.user.UserSessionUtils;
import com.yanado.dao.CateDAO;
import com.yanado.dao.ProductDAO;
import com.yanado.dto.Cate;
import com.yanado.dto.Product;
import com.yanado.service.ProductService;
@Controller
@SessionAttributes("shopping")
@RequestMapping("shopping/create")
public class CreateProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private CateDAO cateDAO;

	@ModelAttribute("shopping")
	public Product formBacking(HttpServletRequest request) {
		Product product = new Product();
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
		Cate cate = cateDAO.getCategoryBySupplierId(userId);

		// String userId = "admin";
		product.setSupplierId(userId);
		product.setCate(cate);
		return product;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopping/form");
		mav.addObject("formtype", "create");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createShopping(@Valid @ModelAttribute("shopping") Product shopping, BindingResult result,
			MultipartFile file, HttpServletRequest request, SessionStatus status, RedirectAttributes red) {

		// red.addAttribute("type", 1);
		
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "redirect:create";
		}

		// 다중 이미지 처리
		/*
		 * String basePath = "src/main/resources/static/productImage"; File folder = new
		 * File(basePath);
		 * 
		 * if (!folder.exists()) { try { folder.mkdir(); // 폴더 생성합니다.
		 * 
		 * } catch (Exception e) { e.getStackTrace(); } }
		 * 
		 * String filePath = folder.getAbsolutePath() + "\\" +
		 * file.getOriginalFilename(); System.out.println(filePath); File dest = new
		 * File(filePath); try { file.transferTo(dest); System.out.println("파일저장"); }
		 * catch (IllegalStateException | IOException e) {
		 * System.out.println(e.getMessage()); }
		 */
		
		
		System.out.println(shopping.getCate().getCateId() + "     " + shopping.getCate().getCateName());
		
		service.createProduct(shopping, null);

		/*
		 * Product product = shopping.getProduct();
		 * product.setImage("../../static/productImage/" + file.getOriginalFilename());
		 * 
		 * shopping.setProduct(product);
		 * 
		 * service.createProduct(shopping);
		 */
		status.setComplete();
		
		red.addAttribute("shoppingId", shopping.getProductId());

		return "redirect:/shopping/view/detail";
	}

}
