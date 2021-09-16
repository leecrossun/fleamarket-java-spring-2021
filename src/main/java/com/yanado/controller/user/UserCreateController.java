package com.yanado.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

//회원 추가 작업 - 파라미터 받아서 회원 리스트로 넘김
@Controller
@SessionAttributes("user")
public class UserCreateController {

	@Autowired
	private UserDAO userDAO;

	// 공동구매 생성 폼으로 가기
	@ModelAttribute("user")
	public User formBacking() {
		User user = new User();
		return user;
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	protected String service(HttpServletRequest request, HttpServletResponse response, SessionStatus status,
			@ModelAttribute("user") User user, BindingResult result) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "user/signUp";
		}

		String id = request.getParameter("userId");
		String pwd = request.getParameter("password");
		String name = request.getParameter("userName");
		String address = request.getParameter("address");
		String phone = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String account = request.getParameter("account");
		String bank = request.getParameter("bank");
		String accName = request.getParameter("accName");

		User dto = new User(id, pwd, name, address, phone, email, 1, account, bank, accName, null, null);

		userDAO.createUser(dto);

		status.setComplete();

		return "redirect:/user/login";
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public String form() {
		return "user/signUp";
	}
}