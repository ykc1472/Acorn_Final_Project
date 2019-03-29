package com.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.PagingQnABoardDTO;
import com.dto.QnABoardDTO;
import com.service.QnABoardService;

@Controller
public class QnABoardController {
	@Autowired
	QnABoardService service;

	@RequestMapping("/qnaBoardForm")
	public String qnaBoardForm(@RequestParam("pick") int pick, HttpServletRequest request) {
		
		QnABoardDTO dto = service.seleclt(pick);
		request.setAttribute("QnABoard", dto);
		
		return "qna_boardForm";
	}
	
	@RequestMapping("/qnaBoardListForm")
	public String qnaBoardListForm(@ModelAttribute PagingQnABoardDTO paging, HttpServletRequest request) {
		
		request.setAttribute("paging", service.selectAllQnABorder(paging));

		return "qna_boardListForm";
	}
	
//	@RequestMapping("/asdsa")
//	public String qnaBoardComment() {
//		
//		return "";
//	}

}
