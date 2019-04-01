package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.PagingFoodListDTO;
import com.service.ManagementService;

@Controller
public class ManagementController {
	
	@Autowired
	ManagementService service;
	
	@RequestMapping("/adminCheck/foodInfoManagement")
	public String foodManagementInfoList(@ModelAttribute("foodInfoList") PagingFoodListDTO paging) {
		paging = service.foodInfoList(paging);
		return "foodManagement";
	}
}
