package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CartDAO;
import com.dto.OrderDTO;
@Service
public class CartService {
	
	@Autowired
	CartDAO dao;
	
	@Transactional
	public List<OrderDTO> addCart(List<OrderDTO> cartList) {
		int success = 0;
		List<OrderDTO> list = null;
		try {
			list = dao.selectAddInfoCart(cartList);
			
			if(list.size() == 0) {
				success = dao.cartAdd(cartList);
				if(cartList.size() != success) {
					throw new Exception("입력한 정보가 모두 Insert되지 못함");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	@Transactional
	public List<OrderDTO> selectCartList(String userid){

		return dao.selectCartList(userid);
	}
}
