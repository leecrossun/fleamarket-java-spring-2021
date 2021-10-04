package com.yanado.controller.product;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yanado.dao.CateDAO;
import com.yanado.dto.Cate;
import com.yanado.dto.Product;
import com.yanado.service.ProductService;

@Controller
@RequestMapping("shopping/create")
@SessionAttributes("shopping")
public class CreateProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private CateDAO cateDAO;

	@ModelAttribute("shopping")
	public Product formBacking(HttpServletRequest request) {

		Product product = new Product();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		Cate cate = cateDAO.getCategoryBySupplierId(userId);

		// String userId = "admin";
		product.setSupplierId(userId);
		product.setCate(cate);
		product.setImage(null);
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

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "redirect:create";
		}

		String s = StringEscapeUtils.escapeHtml(shopping.getContent());
		shopping.setContent(s);

		// 다중 이미지 처리

		//String basePath = "src/main/resources/static/thumbnail";
		String basePath = "/test/WEB-INF/classes/static/thumbnail";
		File folder = new File(basePath);

		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.

			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		
		System.out.println("folder : " + folder.getAbsolutePath());

		String filePath = folder.getAbsolutePath() + "\\" + file.getOriginalFilename();
		System.out.println(filePath);
		File dest = new File(filePath);
		try {
			file.transferTo(dest);
			System.out.println("파일저장");
		} catch (IllegalStateException | IOException e) {
			System.out.println(e.getMessage());
		}

		shopping.setImage("../../static/thumbnail/" + file.getOriginalFilename());

		service.createProduct(shopping);

		status.setComplete();

		red.addAttribute("shoppingId", shopping.getProductId());

		return "redirect:/shopping/view/detail";
	}

	@RequestMapping("/cancel")
	public String cancel(SessionStatus status, RedirectAttributes red) {

		status.setComplete();

		return "redirect:/";
	}

}
