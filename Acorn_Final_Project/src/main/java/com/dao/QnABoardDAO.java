package com.dao;

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
		paging.setQnaboardlist(template.selectList("BoardMapper.selectAllQnABoard", null, new RowBounds(paging.getOffset(), paging.getLimit())));
		
		paging.setQnacommentlist(selectQnACommentList(paging));
		paging.setTotal(qnaCountAll());
		
		return paging;
	}
	public List<QnABoardCommentDTO> selectQnACommentList(PagingQnABoardDTO paging){
		
		return template.selectList("BoardMapper.selectQnaCommentList", paging.getQnaboardlist());
	}
	
	public int qnaCountAll() {
		
		return template.selectOne("BoardMapper.QnACountAll");
	}
	
	public QnABoardDTO selectQnABoard(int qna_num) {
		
		return template.selectOne("BoardMapper.selectQnABoard", qna_num);
	}
	
	
}
