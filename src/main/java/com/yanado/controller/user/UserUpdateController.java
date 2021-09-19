package com.yanado.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.yanado.dao.UserDAO;
import com.yanado.dto.User;

//회원 정보 업데이트
@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserUpdateController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDAO userDAO;

	@ModelAttribute("user")
	public User formBacking(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		User user = userDAO.getUserByUserId(userId);

		return user;
	}

	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	protected String service(HttpServletRequest request, @ModelAttribute("user") User user, SessionStatus status,
			BindingResult result) throws ServletException, IOException {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "user/mypageUpdate";
		}

		int res = userDAO.updateUser(user);

		System.out.println("userId : " + user.getUserId());

		if (res == 1) {
			status.setComplete();

			return "user/mypageMain";
		} else {
			return "user/mypageUpdate";
		}
	}

	@RequestMapping(value = "/updateInfo", method = RequestMethod.GET)
	public String form() {
		return "user/mypageUpdate";
	}
}