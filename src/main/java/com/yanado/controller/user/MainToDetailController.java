package com.yanado.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.dao.UserDAO;

import com.yanado.dto.User;

// 해당 회원 아이디로 정보 가져와서 마이페이지로 이동
// 마이페이지 메인에서 상세정보 페이지로 이동
@Controller
public class MainToDetailController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping("user/update")
	protected ModelAndView service(HttpServletRequest request) throws ServletException, IOException {

		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		User dto = userDAO.getUserByUserId(userId);

		request.setAttribute("dto", dto);

		mav.setViewName("user/update");
		mav.addObject("user", dto);
		return mav;

	}
}