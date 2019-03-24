package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService service;
	
	@RequestMapping("/loginCheck/orderDone")
	public String orderDone(@RequestParam(value="amount", required=false) int[] amount, @RequestParam(value="fcode", required=false) String[] fcode, @RequestParam(value="foption", required=false) int[] foption, 
			@RequestParam(value="post", required=false) int post, @RequestParam(value="addr1", required=false) String address_f, @RequestParam(value="addr2", required=false) String address_l,
			@RequestParam(value="payment", required=false) int payment, @RequestParam(value="payMethod", required=false) int payMethod, HttpServletRequest request, HttpSession session) {
		
		String nextPage = null;
		String mesg = null;
		
		if(foption == null ||fcode == null || amount == null ){
			mesg = "잘못된 접근입니다.";
			nextPage = "main";
			request.setAttribute("mesg", mesg);
		} else {
			List<OrderDTO> orderList = new ArrayList<>();
			
			String userid = ((MemberDTO)session.getAttribute("loginInfo")).getUserid();
			for(int i = 0 ; i < fcode.length ; i++) {
				orderList.add(new OrderDTO(fcode[i], foption[i], userid, amount[i], post, address_f, address_l, payMethod, payment));
			}
			
			orderList = service.orderDone(orderList);
			request.setAttribute("orderUserInfo", service.orderUserInfo(userid));
			request.setAttribute("orderCount", orderList.size());
			request.setAttribute("orderList", orderList);
			nextPage = "orderDone";
		}
		return nextPage;
		
	}
	
	@RequestMapping("/loginCheck/orderConfirm")
	public String orderConfirm(@RequestParam(value="amount", required=false) int[] amount, @RequestParam(value="fcode", required=false) String[] fcode, @RequestParam(value="foption", required=false) int[] foption, HttpServletRequest request, HttpSession session) {
		String nextPage = null;
		String mesg = null;
		
		if(amount == null || fcode == null || foption == null) {
			mesg = "잘못된 접근입니다.";
			nextPage = "main.jsp";
			request.setAttribute("mesg", mesg);
		}
		
		String userid = ((MemberDTO)session.getAttribute("loginInfo")).getUserid();
		List<OrderDTO> orderList = new ArrayList<>();
		for(int i = 0 ; i < amount.length ; i ++) {
			System.out.println(fcode[i] +"//"+ foption[i]+"//"+ userid +"//"+ amount[i]);
			orderList.add(new OrderDTO(fcode[i], foption[i], userid, amount[i]));
		}
		orderList = service.orderConfirm(orderList);
		MemberDTO orderUserInfo = service.orderUserInfo(userid);

		request.setAttribute("orderUserInfo", orderUserInfo);
		request.setAttribute("orderList", orderList);
		nextPage = "orderConfirm";
	
		return nextPage;
	}
}
