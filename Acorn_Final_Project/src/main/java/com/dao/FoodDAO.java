package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.FoodInfoDTO;
import com.dto.PagingFoodListDTO;
@Repository
public class FoodDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public PagingFoodListDTO foodList(PagingFoodListDTO paging){
		List<FoodInfoDTO> list = template.selectList("FoodMapper.FoodList", paging.getCategory(), new RowBounds(paging.getOffset(), paging.getLimit()));
		paging.setFoodlist(list);
		paging.setTotal(foodTotalCount(paging.getCategory()));
		
		return paging;
	}
	
	public int foodTotalCount(int category) {
		// 페이징 처리를 위한 검색 조건에 따른 전체 개수 검색
		return template.selectOne("FoodMapper.foodTotalCount", category);
	}
	
	public List<FoodInfoDTO> foodInfo(String fcode) {
		
		return template.selectList("FoodMapper.foodInfo", fcode);
		
	}
	
	public PagingFoodListDTO foodNewList(PagingFoodListDTO paging){
		List<FoodInfoDTO> list = template.selectList("FoodMapper.foodNewList", null, new RowBounds(paging.getOffset(), paging.getLimit()));
		paging.setFoodlist(list);
		paging.setTotal(foodnewTotal());
		
		return paging;
	}
	
	public int foodnewTotal() {
		return template.selectOne("FoodMapper.foodNewTotal");
	}
	
	public List<HashMap<String, String>> searchFoodList(String search){
		// 검색어를 받아서 해당하는 결과가 들어간 Title을 모두 리턴해준다.
		return template.selectList("FoodMapper.searchFoodList", search);
	}
	
	
	public PagingFoodListDTO searchingList(PagingFoodListDTO paging) {
		List<FoodInfoDTO> list = template.selectList("FoodMapper.searchingList", paging.getFtitle(), new RowBounds(paging.getOffset(), paging.getLimit()));
		paging.setFoodlist(list);
		paging.setTotal(searchingListTotal(paging.getFtitle()));
		
		return paging;
	}
	
	public int searchingListTotal(String ftitle) {
		return template.selectOne("FoodMapper.searchingListTotal", ftitle);
	}

	public int checkStock(String fcode, int foption) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("fcode", fcode);
		map.put("foption", foption);
		
		return template.selectOne("FoodMapper.checkStock", map);
	}
}
