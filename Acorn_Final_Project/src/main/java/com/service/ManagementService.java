package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ManagementDAO;
import com.dto.PagingFoodListDTO;
@Service
public class ManagementService {
	
	@Autowired
	ManagementDAO dao;
	
	@Transactional
	public PagingFoodListDTO foodInfoList(PagingFoodListDTO paging) {
		return paging = dao.foodInfoList(paging);
	}
}
