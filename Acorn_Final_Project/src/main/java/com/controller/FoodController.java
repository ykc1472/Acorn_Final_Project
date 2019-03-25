package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.FoodInfoDTO;
import com.dto.PagingFoodListDTO;
import com.service.FoodService;

@Controller
public class FoodController {
	@Autowired
	FoodService service;
	

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
	
	@RequestMapping("/foodForm")
	public ModelAndView foodForm(@RequestParam("fcode") String fcode) {
		ModelAndView mav = new ModelAndView();
		if(fcode != null) {
			List<FoodInfoDTO> foodinfoList = service.foodInfo(fcode);
			if (foodinfoList != null) {
				mav.addObject("foodinfoList", foodinfoList);
				mav.setViewName("foodForm");
			} else {
				mav.addObject("mesg", fcode + "에 해당하는 상품정보가 없습니다.");
				mav.setViewName("main");
			}
		} else {
			mav.addObject("mesg", "잘못된 접근입니다.");
			mav.setViewName("main");
		}
		return mav;
	}
	
	@RequestMapping(value="/checkStock", method=RequestMethod.POST)
	public ModelAndView checkStock(@RequestParam("fcode") String fcode, @RequestParam("foption") int foption) {
		ModelAndView mav = new ModelAndView();
		boolean flag = true;
		String mesg = null;
		if(foption == 0) {
			flag = false;
			mesg = "잘못된 접근입니다.";
		}
		if(fcode == null) {
			flag = false;
			mesg = "잘못된 접근입니다.";
		}
		if(flag) {
			mav.addObject("mesg", service.checkStock(fcode, foption));
			
		}else {
			mav.addObject("mesg", mesg);
		}
		mav.setViewName("mesg/message");
		return mav;
		
	}
	@RequestMapping(value="/searchingFood", method=RequestMethod.POST)
	public @ResponseBody List<HashMap<String, String>> searchingFood(@RequestParam("search") String search) {
		System.out.println(service.searchFoodList(search));
		return service.searchFoodList(search);
	}

	@RequestMapping(value="/searchingFoodList", method=RequestMethod.GET)
	public String searchingFoodList(@RequestParam(value="search", required=false) String ftitle, @ModelAttribute("flist") PagingFoodListDTO paging, HttpServletRequest request) {
		System.out.println(ftitle);
		System.out.println(paging);
		String nextPage = null;
		if(ftitle != null) {
			paging.setFtitle(ftitle);
			paging = service.searchingList(paging);
			nextPage = "foodListForm";
			request.setAttribute("flist", paging);
		}else {
			request.setAttribute("mesg", "잘못된 접근입니다.");
			nextPage = "forward:main";
		}
		return nextPage;
	}
	
	@RequestMapping("newFoodList")
	public String newFoodList(@RequestParam PagingFoodListDTO paging, HttpServletRequest request) {
		
		String nextPage = null;
		
		paging = service.foodList(paging);
		request.setAttribute("flist", paging);
		nextPage = "foodListForm";
		
		return nextPage;
	}
}
