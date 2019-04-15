package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.dto.PagingQnABoardDTO;
import com.dto.QnABoardDTO;
import com.service.QnABoardService;

@Controller
public class QnABoardController {
	@Autowired
	QnABoardService service;

	@RequestMapping("/qnaBoardForm")
	public String qnaBoardForm(@RequestParam("pick") int pick, HttpServletRequest request) {
		
		request.setAttribute("QnABoard", service.seleclt(pick));
		
		return "qna_boardForm";
	}
	
	@RequestMapping("/qnaBoardListForm")
	public String qnaBoardListForm(@ModelAttribute PagingQnABoardDTO paging, HttpServletRequest request) {
		
		request.setAttribute("paging", service.selectAllQnABorder(paging));

		return "qna_boardListForm";
	}

	@RequestMapping("/loginCheck/writeBoard")
	public String writeBoard(@ModelAttribute QnABoardDTO dto, HttpSession session) {
		dto.setNickname(((MemberDTO)session.getAttribute("loginInfo")).getUsernickname());
		dto.setUserid(((MemberDTO)session.getAttribute("loginInfo")).getUserid());	
		
		service.writeBoard(dto);
		return "redirect:../qnaBoardListForm";
	}
	
	@RequestMapping("/qnaCommentBoardForm")
	public String qnaCommentBoardForm(@RequestParam("pick") int pick, HttpServletRequest request) {
		
		request.setAttribute("QnABoard", service.selectQnACommentBoard(pick));
	
		return "qna_boardForm";
	}
	
}
