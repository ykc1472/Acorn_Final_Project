package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OrderDAO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.dto.PagingOrderListDTO;

@Service
public class OrderService {
	@Autowired
	OrderDAO dao;

	@Transactional
	public List<OrderDTO> orderConfirm(List<OrderDTO> orderlist) {

		return dao.orderConfirm(orderlist);
	}

	@Transactional
	public MemberDTO orderUserInfo(String userid) {

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
		if (insert_success == orderList.size() && insert_success == update_success) {
			System.out.println(4);
			orderList = dao.selectOrderAllDone(orderList);
			System.out.println(5);
		}

		return orderList;
	}

	public PagingOrderListDTO orderListAll(MemberDTO memberDTO, PagingOrderListDTO pagingOrderList) {
		return dao.orderListAll(memberDTO, pagingOrderList);
	}

	public int selectState(int ordernum) {
		return dao.selectState(ordernum);
	}

	public int changeState(OrderDTO dto) {
		return dao.changeState(dto);
	}

	public String getCheckMemberId(int ordernum) {
		return dao.getCheckMemberId(ordernum);
	}

	public OrderDTO deliveryInfo(OrderDTO dto) {
		return dao.deliveryInfo(dto);
	}
	
	public String addressChange(OrderDTO dto) {
		String mesg = "업데이트에 실패했습니다.";
		int success = dao.addressChange(dto);
		
		if (success == 1 ) {
			mesg = "업데이트에 성공하였습니다.";
		}
		
		return mesg;
	}
}
