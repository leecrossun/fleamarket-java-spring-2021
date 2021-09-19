package com.yanado.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/home")
public class ViewIndexController {
	
	public String home(Model model) {
		
		
		return "index.html";
	}
}
