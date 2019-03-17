//package com.service;
//
//import java.util.List;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.stereotype.Service;
//
//import com.dao.CartDAO;
//import com.dto.OrderDTO;
//@Service
//public class CartService {
//	public List<OrderDTO> addCart(List<OrderDTO> cartList) {
//		int success = 0;
//		CartDAO dao = new CartDAO();
//		List<OrderDTO> list = null;
//		
//		try {
//			list = dao.selectAddInfoCart(session, cartList);
//			
//			if(list.size() == 0) {
//				success = dao.cartAdd(session, cartList);
//				if(cartList.size() != success) {
//					throw new Exception("입력한 정보가 모두 Insert되지 못함");
//				}
//				session.commit();
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			session.rollback();
//		}
//		
//		return list;
//	}
//	
//	public List<OrderDTO> selectCartList(String userid){
//		List<OrderDTO> cartList = null;
//		CartDAO dao = new CartDAO();
//		try {
//			cartList = dao.selectCartList(, userid);
//		}
//		
//		return cartList;
//	}
//}
