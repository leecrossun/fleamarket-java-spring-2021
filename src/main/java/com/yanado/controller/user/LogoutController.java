package com.yanado.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController{
	
	private static final long serialVersionUID = 1L;

	@RequestMapping("/user/logout")
	protected String service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.getSession().invalidate();
		
		return "redirect:/";
	}
}