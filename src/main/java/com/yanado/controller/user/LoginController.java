package com.yanado.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yanado.dao.CateDAO;

@Controller
public class LoginController {

	@Autowired
	private CateDAO cateDAO;
	
	// 로그인
	@RequestMapping("/user/login")
	public String login(Model model, HttpServletRequest req){
		model.addAttribute("message",req.getServletContext());
		
		return "user/loginPage";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String form() {
		return "user/loginPage";
	}
	

}