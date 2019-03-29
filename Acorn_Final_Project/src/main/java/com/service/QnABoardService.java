package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.QnABoardDAO;
import com.dto.PagingQnABoardDTO;
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
	public QnABoardDTO seleclt(int qna_num) {
		return dao.selectQnABoard(qna_num);
	}
}
