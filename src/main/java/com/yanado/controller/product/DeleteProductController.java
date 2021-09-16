package com.yanado.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yanado.dao.ProductDAO;

@Controller
public class DeleteProductController {
	
	@Autowired	
	private ProductDAO productDAO;
	
	// 공동구매 파기하기
	@RequestMapping("shopping/delete")
	public String delete(@RequestParam String shoppingId) {
		productDAO.deleteProduct(shoppingId);
		return "redirect:/shopping/view/all";

	}

}
