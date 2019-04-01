package com.dao;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.PagingFoodListDTO;

@Repository
public class ManagementDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public PagingFoodListDTO foodInfoList(PagingFoodListDTO paging) {
		paging.setLimit(10);
		paging.setFoodlist(template.selectList("AdminManagement.foodInfoList", null, new RowBounds(paging.getOffset(), paging.getLimit())));
		return paging;
	}

}
