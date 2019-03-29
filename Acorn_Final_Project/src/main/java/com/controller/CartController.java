package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.CartService;

@Controller
public class CartController {
	@Autowired
	CartService service;
	
	@RequestMapping("/loginCheck/cartAdd")
	public String cartAdd(@RequestParam(value="amount", required=false) int[] amount, @RequestParam(value="fcode", required=false) String[] fcode, @RequestParam(value="foption", required=false) int[] foption,
			HttpServletRequest request, HttpSession session) {
		
		String nextPage = null;
		String mesg = null;
		if(amount == null || fcode == null || foption == null) {
			mesg = "잘못된 접근입니다.";
			nextPage = "main";
			request.setAttribute("mesg", mesg);
		}
		String userid = ((MemberDTO)session.getAttribute("loginInfo")).getUserid();
		List<OrderDTO> cartList = new ArrayList<>();
		for(int i = 0 ; i < amount.length ; i ++) {
			cartList.add(new OrderDTO(fcode[i], foption[i], userid, amount[i]));
		}

		List<OrderDTO> list = service.addCart(cartList);
		if(list.size() == 0) {
			request.setAttribute("success", cartList.size());
			nextPage = "cartAddForm";
		} else {
			nextPage = "redirect:" + request.getHeader("referer");
			System.out.println(nextPage);
			mesg = list.size()+"개의  등록된 같은 제품이 있습니다.";
			session.setAttribute("mesg", mesg);
		}
		return nextPage;
	}

	@RequestMapping("/loginCheck/cartList")
	public String cartList(HttpServletRequest request, HttpSession session) {
	String nextPage = null;
	
	String userid = ((MemberDTO)session.getAttribute("loginInfo")).getUserid();
	List<OrderDTO> cartList = service.selectCartList(userid);
	request.setAttribute("cartList", cartList);
	nextPage = "cartListForm";
	
	return nextPage;
	}

}
