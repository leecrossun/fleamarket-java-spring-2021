package com.yanado.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yanado.dao.UserDAO;
import com.yanado.dto.User;

@Controller
@WebServlet("/user/checkId")
public class UserIdCheckController {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping("/user/checkId")
	protected String service(HttpServletRequest request, HttpServletResponse response, RedirectAttributes red)
			throws ServletException, IOException {

		String id = request.getParameter("userId");
		System.out.println(id);
		User dto = userDAO.getUserByUserId(id);

		int exist = 1;

		if (dto == null) {
			exist = 0;
		}

		red.addAttribute("exist", exist);

		if (exist == 0) {
			red.addAttribute("userId", id);
		}

		return "redirect:/user/create";
	}
}
