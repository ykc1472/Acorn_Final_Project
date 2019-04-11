package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dto.MemberDTO;
import com.service.OrderService;

public class IdentificationIntercepter extends HandlerInterceptorAdapter {

	@Autowired
	OrderService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		System.out.println("Identification preHandle");
		
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
			response.sendRedirect("/Final_Project/loginForm");
			
			return false;
			
		}else if(request.getParameter("ordernum") != null) {
			int ordernum = Integer.parseInt(request.getParameter("ordernum"));
			String userid = service.getCheckMemberId(ordernum);

			if(!userid.equals(((MemberDTO)session.getAttribute("loginInfo")).getUserid())) {
				
				if(((MemberDTO)session.getAttribute("loginInfo")).getGrade() != 99) {
					session.setAttribute("mesg", "본인만 확인이 가능합니다.");
					response.sendRedirect("/Final_Project/loginForm");
					
					return false;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
