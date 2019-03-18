package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;

	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String loginForm() {

		return "loginForm";
	}

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
}
