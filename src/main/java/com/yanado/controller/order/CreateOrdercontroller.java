package com.yanado.controller.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.controller.user.UserSessionUtils;
import com.yanado.dao.BuyerDAO;
import com.yanado.dao.OrderDAO;
import com.yanado.dao.ProductDAO;
import com.yanado.dao.UserDAO;
import com.yanado.dto.Buyer;
import com.yanado.dto.Item;
import com.yanado.dto.Order;
import com.yanado.dto.Product;
import com.yanado.dto.User;

@Controller
@SessionAttributes("order")
@RequestMapping("order/create")
public class CreateOrdercontroller {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private BuyerDAO buyerDAO;

	@ModelAttribute("order")
	public Order formBacking(HttpServletRequest request) {
		Order order = new Order();

		return order;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form(@Valid @ModelAttribute("order") Order order,
			@RequestParam(defaultValue = "1") int quantity, @RequestParam String productId,
			@RequestParam(defaultValue = "1") int type, BindingResult result, SessionStatus status,
			HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		Product product = productDAO.getProductByProductId(productId);

		User buyer = userDAO.getUserByUserId(userId);
		User seller = userDAO.getUserByUserId(product.getSupplierId());

		List<Item> items = new ArrayList<Item>();
		Item item = new Item(null, product, seller, product.getPrice() * quantity, quantity);
		items.add(item);

		// Total Price
		int total = 0;
		for (Item i : items) {
			total += i.getUnitcost();
		}

		total += product.getDelivery();

		order = new Order(null, seller, buyer, null, null, null, items, total, new Date(), 0, 0);

		mav.addObject("order", order);
		mav.setViewName("order/form");

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createOrder(@Valid @ModelAttribute("order") Order order, BindingResult result, SessionStatus status) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "shopping/form";
		}

		List<Item> items = order.getItem();
		for (Item i : items) {
			buyerDAO.createBuyer(new Buyer(i.getProduct(), i.getBuyer(), 0));
			productDAO.updateStockByProductId(i.getProduct().getProductId());

		}

		orderDAO.createOrder(order, order.getItem());
		System.out.println("createOrder Log");

		return "redirect:/order/view/result";
	}

}
