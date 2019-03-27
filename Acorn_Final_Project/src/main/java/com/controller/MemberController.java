package com.controller;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MailDTO;
import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;

	@RequestMapping("/userAdd")
	public String UserInsert(@RequestParam MemberDTO dto, @ModelAttribute("mesg") String mesg) {
		int success = service.userAdd(dto);

		if (success == 1) {
			mesg = "회원가입에 성공하셨습니다.";
		} else {
			mesg = "회원가입에 실패하셨습니다. 다시 시도해 주세요.";
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
				nextPage = "main";
			}
		}
		
		return nextPage;
	}
	
	@RequestMapping("loginCheck/Logout")
	public String logout(Model m, HttpSession session) {
		
		m.addAttribute("mesg", ((MemberDTO)session.getAttribute("loginInfo")).getusernickname() +"님 정상적으로 로그아웃 되었습니다.");
		session.removeAttribute("loginInfo");
		
		return "redirect:main";
	}
	
	//////////////////////////////////////////////////////////////
	
//	@RequestMapping("/login")
//	public String login(@RequestParam Map<String, String>map,
//			HttpSession session) {
//		
//		MemberDTO dto = service.login(map);
//		String nextPage=null;
//		if(dto!=null) {
//			session.setAttribute("login", dto);
//			nextPage = "redirect:/main";// 8090/shop/main
//		}else {
//			nextPage = "redirect:/loginUI"; //8090/shop/loginUI
//		}
//		return nextPage;
//	}
	
	@RequestMapping(value="MemberPasswdSearch", method=RequestMethod.GET)
	public String MemberPasswdSearch(@RequestParam("username") String username,
									@RequestParam("userid")String userid,
									@RequestParam("userEMail")String email,
									HttpServletRequest request,
									@ModelAttribute("mailInfo") MailDTO mailInfo, Model m) {
		
		username = username.trim();
		userid = userid.trim();
		email = email.trim();
		
		String mesg = "<h1>Dessert_Mini_Project</h1><h3>이메일 인증입니다</h3>";
		
		Random rnd = new Random();
		StringBuffer buf = new StringBuffer();
		
		for(int i=0;i<10;i++){
		    //rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴.
			//true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		        buf.append((rnd.nextInt(10)));
		    }
		}

		MemberDTO dto = new MemberDTO();
		dto.setUsername(username);
		dto.setUserid(userid);
		dto.setEmail(email);
		
		String userpw = service.passwdSearch(dto);
		String nextPage = null;
		if (userpw == null) {
			nextPage = "passwdSearch";
			request.setAttribute("mesg", "등록되지 않은 정보입니다.");
		} else {
			nextPage="forward:sendMail";
			HttpSession session = request.getSession();
			
			mesg += "<h3>비밀번호 찾기에 필요한 인증번호 :</h3><h2>" + buf + "</h2><h3>입니다.</h3>";
			mailInfo.setFormNickName("admin");
			mailInfo.setRandomMessage(buf.toString());
			mailInfo.setMesg(mesg);
			mailInfo.setMailtitle("Dessert_Mini_Project 본인인증 메일입니다.");
			mailInfo.setNextPage("passwdCheck");
			mailInfo.setUseremail(email);
			session.setAttribute("buf", buf.toString());
			session.setAttribute("userid", userid);
		}
		return nextPage;
	}
	
	
	@RequestMapping("/PasswdCheck")
	public String PasswdCheck(HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		String buf = (String)session.getAttribute("buf").toString();
		String userid = (String)session.getAttribute("userid").toString();
		String pwcheck = request.getParameter("pwcheck").trim();
		
		MemberDTO dto = new MemberDTO();
		dto.setUserid(userid);
		
		String userpw = service.passwdSearch2(dto);
		
		session.setAttribute("userpw", userpw);
		
		String nextPage = null;
		if (!(pwcheck.equals(buf))) {
			nextPage = "passwdCheck";
			request.setAttribute("mesg", "인증번호가 일치하지 않습니다. 다시 입력해주세요. ");
		} else {
			nextPage="passwdCheck2";
			session.removeAttribute("buf");
		}
		return nextPage;
	}
}
