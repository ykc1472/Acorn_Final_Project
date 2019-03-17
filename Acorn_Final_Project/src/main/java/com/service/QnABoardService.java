//package com.service;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.config.MySqlSessionFactory;
//import com.dao.QnABoardDAO;
//import com.dto.PagingQnABoardDTO;
//import com.dto.QnABoardDTO;
//@Service
//public class QnABoardService {
//	@Transactional
//	public PagingQnABoardDTO selectAllQnABorder(PagingQnABoardDTO paging) {
//		SqlSession session = MySqlSessionFactory.getSession();
//		try {
//			QnABoardDAO dao = new QnABoardDAO();
//			paging = dao.selectAllQnABoard(session, paging);
//			
//		} finally {
//			if(session != null)
//			session.close();
//		}
//	
//		return paging;
//	}
//	@Transactional
//	public QnABoardDTO seleclt (int qna_num) {
//		SqlSession session = MySqlSessionFactory.getSession();
//		QnABoardDTO dto = null;
//		try {
//			QnABoardDAO dao = new QnABoardDAO();
//			dto = dao.selectQnABoard(session, qna_num);
//			
//		} finally {
//			if(session != null)
//			session.close();
//		}
//	
//		return dto;
//	}
//}
