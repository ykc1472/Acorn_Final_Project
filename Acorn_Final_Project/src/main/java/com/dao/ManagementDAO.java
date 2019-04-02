package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.FoodInfoDTO;
import com.dto.PagingFoodListDTO;

@Repository
public class ManagementDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public PagingFoodListDTO foodInfoList(PagingFoodListDTO paging) {
		paging.setLimit(10);
		paging.setFoodlist(template.selectList("AdminManagement.foodInfoList", null, new RowBounds(paging.getOffset(), paging.getLimit())));
		paging.setTotal(template.selectOne("AdminManagement.foodTotalCount"));
		return paging;
	}
	public List<HashMap<String, Object>> selectCategory() {
		return template.selectList("AdminManagement.categorySelect");
	}
	
	public int updateFoodInfo(FoodInfoDTO dto) throws Exception{
		int main = template.update("AdminManagement.updateFoodInfoMain", dto);
		int option = template.update("AdminManagement.updateFoodInfoOption", dto);
		if (main != option) {
			throw new Exception("모두 성공하지 못함");
		}
		return main;
		
	}
}
