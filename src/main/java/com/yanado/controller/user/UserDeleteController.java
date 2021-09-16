package com.yanado.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yanado.dao.UserDAO;

//회원 삭제 작업
@Controller
@WebServlet("/user/delete")
public class UserDeleteController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping("/user/delete")
	protected String service(HttpServletRequest request, HttpSession session) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		UserSessionUtils uSession = new UserSessionUtils();
		String userId = uSession.getLoginUserId(request.getSession());
		int flag = userDAO.deleteUser(userId); // 1이면 성공, 0이면 실패

		if (flag == 1) {

			session.invalidate();
		}else {
			System.out.println("실패");
		}

		return "redirect:/";
	}
}
