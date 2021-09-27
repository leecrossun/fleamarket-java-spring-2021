package com.yanado.controller.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.yanado.dao.*;
import com.yanado.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

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

	@Autowired
	private CartDAO cartDAO;

	@ModelAttribute("order")
	public Order formBacking(HttpServletRequest request) {
		Order order = new Order();

		return order;
	}

	@PostMapping(value = "/create")
	public ModelAndView orderForm(){
		ModelAndView mv = new ModelAndView();


		mv.setViewName("order/form");
		return mv;
	}

	@RequestMapping(value = "/all")
	public ModelAndView orderAll(@Valid @ModelAttribute("order") Order order, @RequestParam String cateName){
		ModelAndView mv = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		List<Cart> cartList = cartDAO.getCartListByUserId(userId);
		User buyer = userDAO.getUserByUserId(userId);
		User seller = userDAO.getUserByUserId(cateName);

		List<Item> items = new ArrayList<Item>();
		int total = 0;

		for(Cart c : cartList){
			if (c.getSupplierId().equals(cateName)){
				Product product = productDAO.getProductByProductId(c.getProduct().getProductId());
				int quantity = c.getQuantity();
				total += product.getDelivery();
				Item item = new Item(null, product, seller, product.getPrice() * quantity, quantity);
				items.add(item);
			}
		}

		for (Item i : items) {
			total += i.getUnitcost();
		}


		order = new Order(null, seller, buyer, null, null, null, items, total, new Date(), 0, 0);

		mv.addObject("order", order);
		mv.setViewName("order/form");

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form(@Valid @ModelAttribute("order") Order order,
			@RequestParam(defaultValue = "1") int quantity, @RequestParam String productId, BindingResult result, SessionStatus status,
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
		
		System.out.println(order.getOrderDate());

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
