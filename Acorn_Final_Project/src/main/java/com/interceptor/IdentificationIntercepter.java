package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dto.MemberDTO;

public class IdentificationIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		System.out.println("Admin preHandle");
		
		if(session.getAttribute("loginInfo") == null) {
			response.sendRedirect("/Final_Project/loginForm");
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
			
			return false;
		}
		if()
		
		return super.preHandle(request, response, handler);
	}
}
