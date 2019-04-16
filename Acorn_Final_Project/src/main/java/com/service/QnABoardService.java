package com.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.QnABoardDAO;
import com.dto.MailDTO;
import com.dto.PagingQnABoardDTO;
import com.dto.QnABoardCommentDTO;
import com.dto.QnABoardDTO;

@Service
public class QnABoardService {
	@Autowired
	QnABoardDAO dao;

	@Transactional
	public PagingQnABoardDTO selectAllQnABorder(PagingQnABoardDTO paging) {
		return dao.selectAllQnABoard(paging);
	}

	@Transactional
	public QnABoardDTO seleclt(int qna_num, HashSet<Integer> set) {
		return dao.selectQnABoard(qna_num, set);
	}

	@Transactional
	public int writeBoard(QnABoardDTO dto) {
		return dao.writeBoard(dto);
	}
	
	@Transactional
	public QnABoardCommentDTO selectQnACommentBoard(int qna_num, HashSet<Integer> set) {
		return dao.selectQnACommentBoard(qna_num, set);
	}
	@Transactional
	public void deleteQnaBoard(QnABoardDTO dto) {
		dao.deleteQnaBoard(dto);
	}

	@Transactional
	public void updateCommentBoard(QnABoardDTO dto) {
		dao.updateCommentBoard(dto);
	}
	
	@Transactional
	public void updateBoard(QnABoardDTO dto) {
		dao.updateBoard(dto);
	}

	@Transactional
	public boolean passwordCheck(String userid, String userpw) {
		return userpw.equals(dao.passwordCheck(userid));
	}
	
	@Transactional
	public MailDTO writeCommentBoard(QnABoardDTO dto) {
		
		MailDTO mail = new MailDTO();
		mail.setNextPage("redirect:../qnaBoardListForm");
		mail.setUseremail(dao.writeCommentBoard(dto));
		mail.setMailtitle(dto.getQna_title());
		mail.setMesg(dto.getContent());
		mail.setFormNickName(dto.getNickname());
		
		return mail;
	}
	
}
