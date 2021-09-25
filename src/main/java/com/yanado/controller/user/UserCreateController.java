package com.yanado.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yanado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.yanado.dao.UserDAO;
import com.yanado.dto.User;
import com.yanado.dto.UserRole;

//회원 추가 작업 - 파라미터 받아서 회원 리스트로 넘김
@Controller
@SessionAttributes("user")
public class UserCreateController {

	@ModelAttribute("user")
	public User formBacking() {
		User user = new User();
		return user;
	}

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	protected String service(HttpServletRequest request, SessionStatus status, @ModelAttribute("user") User user)
			throws ServletException, IOException {
		
		user.setAuth(UserRole.USER);

		userService.createUser(user);
		status.setComplete();
		return "redirect:/user/login";
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public String form() {
		return "user/signUp";
	}
}