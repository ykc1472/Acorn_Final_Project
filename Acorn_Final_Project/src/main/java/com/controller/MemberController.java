package com.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MailDTO;
import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;

	@RequestMapping("/userAdd")
	public String UserInsert(MemberDTO dto, Model m) {
		System.out.println(dto);
		
		int success = service.userAdd(dto);

		if (success == 1) {
			m.addAttribute("mesg", "회원가입에 성공하셨습니다.");
		} else {
			m.addAttribute("mesg", "회원가입에 실패하셨습니다. 다시 시도해 주세요.");
		}
		
		return "loginForm";
	}

	@RequestMapping("/idCheck")
	public String idCheck(@RequestParam("userid") String userid, Model m) {

		int success = service.userIDCheck(userid);
		if (success == 0) {
			m.addAttribute("mesg", "사용 가능한 ID 입니다.");
		} else {
			m.addAttribute("mesg", "이미 사용중인 ID 입니다.");
		}
		return "mesg/message";
	}
	
	
//	@RequestMapping("/emailCheck")
//	public String emailCheck(@RequestParam("useremail") String useremail, Model m) {
//		MailDTO mailInfo = new MailDTO();
//		
//		Random rnd = new Random();
//		StringBuffer buf = new StringBuffer();
//		String mesg = "<h1>Dessert_Mini_Project</h1><h3>이메일 인증입니다</h3>";
//
//		for (int i = 0; i < 10; i++) {
//			// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한
//			// 숫자를 StringBuffer 에 append 한다.
//			if (rnd.nextBoolean()) {
//				buf.append((char) ((int) (rnd.nextInt(26)) + 97));
//			} else {
//				buf.append((rnd.nextInt(10)));
//			}
//		}
//		mesg += "<h3>회원가입에 필요한 인증번호 :</h3><h2>" + buf + "</h2><h3>입니다.</h3>";
//		mailInfo.setUseremail(useremail);
//		mailInfo.setFormNickName("admin");
//		mailInfo.setRandomMessage(buf.toString());
//		mailInfo.setMesg(mesg);
//		mailInfo.setMailtitle("Dessert_Mini_Project 회원가입 인증메일 입니다.");
//		mailInfo.setNextPage("mesg/message");
//		m.addAttribute("mailInfo", mailInfo);
//		System.out.println(mailInfo);
//		
//		return "forward:sendMail";
//	}

	@RequestMapping("/emailCheck")
	public String emailCheck(@ModelAttribute("mailInfo") MailDTO mailInfo, Model m) {
		
		Random rnd = new Random();
		StringBuffer buf = new StringBuffer();
		String mesg = "<h1>Dessert_Mini_Project</h1><h3>이메일 인증입니다</h3>";

		for (int i = 0; i < 10; i++) {
			// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한
			// 숫자를 StringBuffer 에 append 한다.
			if (rnd.nextBoolean()) {
				buf.append((char) ((int) (rnd.nextInt(26)) + 97));
			} else {
				buf.append((rnd.nextInt(10)));
			}
		}
		mesg += "<h3>회원가입에 필요한 인증번호 :</h3><h2>" + buf + "</h2><h3>입니다.</h3>";
		mailInfo.setFormNickName("admin");
		mailInfo.setRandomMessage(buf.toString());
		mailInfo.setMesg(mesg);
		mailInfo.setMailtitle("Dessert_Mini_Project 회원가입 인증메일 입니다.");
		mailInfo.setNextPage("mesg/message");

		return "forward:sendMail";
	}
	
	@RequestMapping(value="/loginAction", method=RequestMethod.POST)
	public String loginAction(@ModelAttribute MemberDTO dto, HttpSession session) {
		dto = service.loginAction(dto);
		
		String nextPage = "loginForm";
		if (dto != null && dto.getGrade() < 10) {
			session.setAttribute("mesg", dto.getUserid()+"는 접근이 제한된 계정입니다.");
			
		} else if (dto == null) {
			session.setAttribute("mesg", "아이디와 비밀번호를 확인해 주세요.");
			
		} else {
			session.setAttribute("loginInfo", dto);
			
			if(session.getAttribute("backPage") != null) {
				nextPage = (String)session.getAttribute("backPage");
				session.removeAttribute("backPage");
			}
			else {
				nextPage = "redirect:main";
			}
		}
		
		return nextPage;
	}
	
	@RequestMapping("/loginCheck/logout")
	public String logout(HttpSession session) {
		
		session.setAttribute("mesg", ((MemberDTO)session.getAttribute("loginInfo")).getUsernickname() +"님 정상적으로 로그아웃 되었습니다.");
		session.removeAttribute("loginInfo");
		
		return "redirect:../main";
	}
	
	@RequestMapping("/nickCheck")
	public @ResponseBody int nickCheck(@RequestParam("nickName") String nickName) {
		
		return service.nickNameCheck(nickName);
	}
	
	@RequestMapping("phoneCheck")
	public @ResponseBody int phoneCheck(@RequestParam("phoneNumber") String phoneNumber) {
	
		return service.phoneNumberCheck(phoneNumber);
	}
}
