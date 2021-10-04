package com.yanado.controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yanado.dao.CateDAO;
import com.yanado.dto.Cate;

@Controller
public class ViewIndexController {
	
	@Autowired
	private CateDAO cateDAO;

	@RequestMapping(value={"/", "/home"})
	public String home(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		List<Cate> cateList = cateDAO.getCategory();
		session.setAttribute("cateList", cateList);

		return "index.html";
	}
}
