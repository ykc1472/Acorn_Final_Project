package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ManagementDAO;
import com.dto.FoodInfoDTO;
import com.dto.PagingFoodListDTO;
@Service
public class ManagementService {
	
	@Autowired
	ManagementDAO dao;
	
	@Transactional
	public PagingFoodListDTO foodInfoList(PagingFoodListDTO paging) {
		return paging = dao.foodInfoList(paging);
	}
	
	public List<HashMap<String, Object>> selectCategory() {
		return dao.selectCategory();
	}
	
	@Transactional
	public int updateFoodInfo(FoodInfoDTO dto) {
		int success = 0;
		try {
			success = dao.updateFoodInfo(dto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return success;
	}
}
