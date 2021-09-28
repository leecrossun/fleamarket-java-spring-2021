package com.yanado.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yanado.dao.BuyerDAO;
import com.yanado.dao.OrderDAO;
import com.yanado.dao.ProductDAO;
import com.yanado.dao.UserDAO;
import com.yanado.dto.Buyer;
import com.yanado.dto.Order;
import com.yanado.dto.Product;
import com.yanado.dto.User;
import com.yanado.dto.UserRole;

// 해당 회원 아이디로 정보 가져와서 마이페이지로 이동
@Controller
public class MypageController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private BuyerDAO buyerDAO;

	@RequestMapping("user/mypageMain")
	protected ModelAndView service(HttpServletRequest request) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		User dto = userDAO.getUserByUserId(userId);

		request.setAttribute("user", dto);

		System.out.println("user : " + dto.getCertified());


		mav.setViewName("user/mypageMain");
		mav.addObject("user", dto);
		return mav;

	}

	// 내 주문 리스트
	@RequestMapping("user/list/order")
	public ModelAndView viewOrderByUserId(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		List<Order> order = (List<Order>) orderDAO.getOrderByUserId(userId);

		mav.setViewName("order/myList");
		mav.addObject("orderList", order);

		return mav;

	}
	
	// 내 주문 리스트 - 결제 X
		@RequestMapping("user/list/notPay")
		public ModelAndView viewOrderByUserIdNPay(HttpServletRequest request) {
			ModelAndView mav = new ModelAndView();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userId = authentication.getName();

			List<Order> order = (List<Order>) orderDAO.getOrderByUserIdNPay(userId);

			mav.setViewName("order/myList");
			mav.addObject("type", "pay");
			mav.addObject("orderList", order);

			return mav;

		}

	// 내 주문 받은
	@RequestMapping("seller/list/order")
	public ModelAndView viewOrderBySupplierId(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		List<Order> order = (List<Order>) orderDAO.getOrderBySupplierId(userId);

		mav.setViewName("order/supplierList");
		mav.addObject("orderList", order);

		return mav;

	}
	
	// 내 주문 받은
		@RequestMapping("seller/list/notPay")
		public ModelAndView viewOrderBySupplierIdNPay(HttpServletRequest request) {
			ModelAndView mav = new ModelAndView();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userId = authentication.getName();

			List<Order> order = (List<Order>) orderDAO.getOrderBySupplierIdNPay(userId);

			mav.setViewName("order/supplierList");
			mav.addObject("orderList", order);
			mav.addObject("type", "pay");

			return mav;

		}

	@RequestMapping("order/view/detail")
	public ModelAndView viewDetailOrder(@RequestParam String orderId) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/result");
		Order order = orderDAO.getOrderByOrderId(orderId);
		mav.addObject("order", order);
		return mav;

	}

	@RequestMapping("order/updatePayment")
	public String updatePayment(@RequestParam String orderId, @RequestParam int payment) {
		System.out.println(payment);
		orderDAO.updatePaymentByOrderId(orderId, payment);
		
		System.out.println(payment);
		
		return "redirect:/seller/list/order";
	}
}