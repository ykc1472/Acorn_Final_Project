package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OrderDAO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
@Service
public class OrderService {
	@Autowired
	OrderDAO dao;
	
	@Transactional
	public List<OrderDTO> orderConfirm(List<OrderDTO> orderlist){

		return dao.orderConfirm(orderlist);
	}
	
	@Transactional
	public MemberDTO orderUserInfo(String userid){
		
		return dao.orderUserInfo(userid);
	}
	@Transactional
	public List<OrderDTO> orderDone(List<OrderDTO> orderList) {
		int insert_success = 0;
		int update_success = 0;
		System.out.println(orderList);
		insert_success = dao.orderDone(orderList);
		System.out.println(2);
		update_success = dao.updateOptionStock(orderList);
		System.out.println(3);
		if(insert_success == orderList.size() && insert_success == update_success) {
			System.out.println(4);
			orderList = dao.selectOrderAllDone(orderList);
			System.out.println(5);
		}
		
		return orderList;
	}
}
