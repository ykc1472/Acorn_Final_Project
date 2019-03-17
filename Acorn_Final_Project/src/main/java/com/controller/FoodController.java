package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.PagingFoodListDTO;
import com.service.FoodService;

@Controller
public class FoodController {
	@Autowired
	FoodService service;
	
	@RequestMapping("/")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		
		return mav;
	}
	@RequestMapping("/FoodList")
	public ModelAndView foodList(@ModelAttribute("flist") PagingFoodListDTO paging) {
		ModelAndView mav = new ModelAndView();
		if(paging.getCategory() != 0) {
			paging = service.foodList(paging);
			mav.setViewName("foodListForm");
		} else{
			mav.addObject("mesg", "잘못된 접근입니다.");
			mav.setViewName("main");
		}
		
		return mav;
	}

}
