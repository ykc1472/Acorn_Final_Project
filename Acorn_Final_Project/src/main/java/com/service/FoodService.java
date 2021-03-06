package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FoodDAO;
import com.dto.FoodInfoDTO;
import com.dto.PagingFoodListDTO;
@Service
public class FoodService {
	@Autowired
	FoodDAO dao;
	
	@Transactional
	public PagingFoodListDTO foodList(PagingFoodListDTO paging){
		// 제품 목록을 보여주기 위한 서비스
		// 해당하는 카테고리를 DAO로 보내줘서 제품의 정보를 가져온다.

		paging = dao.foodList(paging);
		
		return paging;
	}
	
	@Transactional
	public List<FoodInfoDTO> foodInfo(String fcode){
		// foodForm.jsp 요청시 한개의 제품정보를 가지고 돌아간다.
		// list를 사용하는 이유는 조인문에 의해서 옵션에 따라 결과가 1개 이상 나올 수 있기 때문이다.
		List<FoodInfoDTO> list = null;
		
		list = dao.foodInfo(fcode);
		
		return list;
	}
	
	@Transactional
	public PagingFoodListDTO foodNewList(PagingFoodListDTO paging){
		paging = dao.foodNewList(paging);
		return paging;
	}
	
	@Transactional
	public PagingFoodListDTO bestSeller(PagingFoodListDTO paging){
		return dao.bestSeller(paging);
	}
	
	@Transactional
	public List<HashMap<String, String>> searchFoodList(String search){
		List<HashMap<String, String>> list = null;
		list = dao.searchFoodList(search);
		
		return list;
	}
	
	@Transactional
	public PagingFoodListDTO searchingList(PagingFoodListDTO paging) {
		paging = dao.searchingList(paging);
		
		return paging;
		
	}
	
	@Transactional
	public int checkStock(String fcode, int foption) {
		int stock = 0;
		stock = dao.checkStock(fcode, foption);
		
		return stock;
	}
	
}
