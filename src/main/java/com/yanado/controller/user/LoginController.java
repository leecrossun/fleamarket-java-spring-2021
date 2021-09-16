package com.yanado.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yanado.dao.UserDAO;
import com.yanado.dto.User;

@Controller
public class LoginController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String service(HttpServletRequest request, RedirectAttributes red) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("userId");
		String pwd = request.getParameter("password");

		User dto = userDAO.getUserByUserId(id);

		if (dto == null || dto.getUserId().equals(id) == false || dto.getPassword().equals(pwd) == false) {
			red.addAttribute("type", "0");
			return "redirect:/user/login";
			
		} else {

			HttpSession session = request.getSession();
			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, id);
			
			int auth = dto.getAuth();
			if(auth == 0) {
				session.setAttribute(UserSessionUtils.AUTH, "auth");
			}
			
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String form() {
		return "user/loginPage";
	}
	

}