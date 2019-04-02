package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.FoodInfoDTO;
import com.dto.PagingFoodListDTO;
import com.service.ManagementService;

@Controller
public class ManagementController {
	
	@Autowired
	ManagementService service;
	
	@RequestMapping("/adminCheck/foodInfoManagement")
	public String foodManagementInfoList(@ModelAttribute("foodInfoList") PagingFoodListDTO paging, Model m) {
		paging = service.foodInfoList(paging);
		m.addAttribute("categoryList", service.selectCategory());
		return "foodManagement";
	}
	
	@RequestMapping("/adminCheck/updateFoodInfo")
	public @ResponseBody int updateFoodInfo(@ModelAttribute("aaa") FoodInfoDTO dto, Model m) {
		
		return service.updateFoodInfo(dto);
	}
}
