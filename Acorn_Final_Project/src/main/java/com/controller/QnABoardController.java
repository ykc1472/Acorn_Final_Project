package com.controller;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.dto.PagingQnABoardDTO;
import com.dto.QnABoardCommentDTO;
import com.dto.QnABoardDTO;
import com.service.QnABoardService;

@Controller
public class QnABoardController {
	@Autowired
	QnABoardService service;

	@RequestMapping("/qnaBoardForm")
	public String qnaBoardForm(@RequestParam("pick") int pick, HttpServletRequest request, HttpSession session) {
		HashSet<Integer> set = (HashSet<Integer>)session.getAttribute("readBoard");
		if (set == null) {
			set = new HashSet<>();
		}
		request.setAttribute("QnABoard", service.seleclt(pick, set));
		set.add(pick);
		session.setAttribute("readBoard", set);
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
	public String qnaCommentBoardForm(@RequestParam("pick") int pick, HttpServletRequest request, HttpSession session) {
		
		HashSet<Integer> set = (HashSet<Integer>)session.getAttribute("readCommentBoard");
		if (set == null) {
			set = new HashSet<>();
		}
		request.setAttribute("QnABoard", service.selectQnACommentBoard(pick, set));
		
		set.add(pick);
		session.setAttribute("readCommentBoard", set);
		
		return "qna_CommentboardForm";
	}
	
	@RequestMapping("/admenCheck/writeCommentBoardForm")
	public String qnawriteCommentBoardForm(@RequestParam("pick") int pick, Model m, HttpSession session) {
	
		HashSet<Integer> set = (HashSet<Integer>)session.getAttribute("readBoard");
		if (set == null) {
			set = new HashSet<>();
		}
		QnABoardDTO dto = service.seleclt(pick, set);
		
		set.add(pick);
		session.setAttribute("readCommentBoard", set);
		
		dto.setContent(dto.getContent() + "\n ======================================================================================================== \n");
		
		m.addAttribute("boardInfo", dto);
		
		return "qna_writeCommentboardForm";
	}
	
	@RequestMapping("/admenCheck/writeBoard")
	public String qnawriteCommentBoard(@ModelAttribute QnABoardDTO dto, HttpSession session, Model model) {
		
		dto.setNickname(((MemberDTO)session.getAttribute("loginInfo")).getUsernickname());
		dto.setUserid(((MemberDTO)session.getAttribute("loginInfo")).getUserid());
		model.addAttribute("mailInfo", service.writeCommentBoard(dto));
		
		return "forward:../sendMail";
	}

	@RequestMapping("/adminCheck/deleteQnaBoard")
	public String deleteQnaBoard(@ModelAttribute QnABoardDTO dto) {
		service.deleteQnaBoard(dto);
		
		return "redirect:../qnaBoardListForm";
	}

	@RequestMapping("/adminCheck/updateQnaCommentBoardForm")
	public String updateQnaCommentBoard(@ModelAttribute QnABoardDTO dto, HttpSession session, Model model) {
		System.out.println(11111);
		HashSet<Integer> set = (HashSet<Integer>)session.getAttribute("readCommentBoard");
		if (set == null) {
			set = new HashSet<>();
		}
		QnABoardCommentDTO asd = service.selectQnACommentBoard(dto.getQna_num(), set);
		model.addAttribute("boardInfo", asd);
		
		set.add(dto.getQna_num());
		session.setAttribute("readCommentBoard", set);
		
		return "qna_updateCommentboardForm";
	}
	
	@RequestMapping("/loginCheck/updateBoardForm")
	public String updateQnaBoard(@ModelAttribute() QnABoardDTO dto, HttpSession session, HttpServletRequest request) {
		HashSet<Integer> set = (HashSet<Integer>)session.getAttribute("readBoard");
		if (set == null) {
			set = new HashSet<>();
		}
		
		request.setAttribute("QnABoard", service.seleclt(dto.getQna_num(), set));
		set.add(dto.getQna_num());
		session.setAttribute("readBoard", set);
		return "qna_updateboardForm";
	}
	
	@RequestMapping("/admenCheck/updateCommentBoard")
	public String updateQnaBoardForm(@ModelAttribute("QnABoard") QnABoardDTO dto) {
		service.updateCommentBoard(dto);
		
		return "redirect:../qnaBoardListForm";
	}
	
	@RequestMapping("/loginCheck/updateBoard")
	public String updateQnaCommentBoard(@ModelAttribute("QnABoard") QnABoardDTO dto) {
		System.out.println(dto);
		service.updateBoard(dto);
		
		return "redirect:../qnaBoardListForm";
	}

	@RequestMapping("/loginCheck/passwordCheck")
	public @ResponseBody boolean passwardCheck(@RequestParam("userpw") String userpw, HttpSession session) {
		
		return service.passwordCheck(((MemberDTO)session.getAttribute("loginInfo")).getUserid(), userpw);
	}
	@RequestMapping("/loginCheck/deleteBoard")
	public String deleteBoard(@ModelAttribute("QnABoard") QnABoardDTO dto) {
		service.deleteQnaBoard(dto);
		
		return "redirect:../qnaBoardListForm";
	}
	
}
