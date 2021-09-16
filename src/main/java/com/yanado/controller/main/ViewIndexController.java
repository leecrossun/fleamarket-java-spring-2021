package com.yanado.controller.main;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
public class ViewIndexController {
	
	public String home(Model model) {
		
		
		return "index.html";
	}
}
