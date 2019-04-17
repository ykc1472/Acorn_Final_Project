package com.dao;

import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.PagingQnABoardDTO;
import com.dto.QnABoardCommentDTO;
import com.dto.QnABoardDTO;

@Repository
public class QnABoardDAO {

	@Autowired
	SqlSessionTemplate template;

	public PagingQnABoardDTO selectAllQnABoard(PagingQnABoardDTO paging) {
		paging.setQnaboardlist(template.selectList("BoardMapper.selectAllQnABoard", null,
				new RowBounds(paging.getOffset(), paging.getLimit())));

		paging.setQnacommentlist(selectQnACommentList(paging));
		paging.setTotal(qnaCountAll());

		return paging;
	}

	public List<QnABoardCommentDTO> selectQnACommentList(PagingQnABoardDTO paging) {

		return template.selectList("BoardMapper.selectQnaCommentList", paging.getQnaboardlist());
	}

	public int qnaCountAll() {

		return template.selectOne("BoardMapper.QnACountAll");
	}

	public QnABoardDTO selectQnABoard(int qna_num, HashSet<Integer> set) {
		boolean flag = true;
		
		for(int num : set) {
			if(num == qna_num) {
				flag = false;
			}
		}
	
		if(flag) {
			template.update("BoardMapper.readCount", qna_num);
		}
		return template.selectOne("BoardMapper.selectQnABoard", qna_num);
	}

	public QnABoardCommentDTO selectQnACommentBoard(int qna_num, HashSet<Integer> set) {
		boolean flag = true;
		
		for(int num : set) {
			if(num == qna_num) {
				flag = false;
			}
		}
		
		if(flag) {
			template.update("BoardMapper.readCountComment", qna_num);
		}
			

		return template.selectOne("BoardMapper.selectQnACommentBoard", qna_num);
	}

	public int writeBoard(QnABoardDTO dto) {

		return template.insert("BoardMapper.writeBoard", dto);
	}
	public void deleteQnaBoard(QnABoardDTO dto) {
		template.update("BoardMapper.deleteQnaBoard", dto);
	}

	public String writeCommentBoard(QnABoardDTO dto) {
		template.insert("BoardMapper.writeCommentBoard", dto);
		
		return template.selectOne("BoardMapper.userEmail", dto);
	}

	public void updateCommentBoard(QnABoardDTO dto) {
		template.update("BoardMapper.updateCommentBoard", dto);
		
	}
	public void updateBoard(QnABoardDTO dto) {
		template.update("BoardMapper.updateBoard", dto);
		
	}

	public String passwordCheck(String userid) {
		return template.selectOne("BoardMapper.passwordCheck", userid);
	}

}
