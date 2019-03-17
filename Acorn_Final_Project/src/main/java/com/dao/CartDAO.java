package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.OrderDTO;
@Repository
public class CartDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public int cartAdd(List<OrderDTO> cartList){
		return template.insert("CartMapper.InsertAllcart", cartList);
	}
	
	public List<OrderDTO> selectAddInfoCart(List<OrderDTO> cartList) {
		return template.selectList("CartMapper.selectAddInfoCart", cartList);
	}
	
	public List<OrderDTO> selectCartList(String userid){
		System.out.println(userid);
		// 유저의 cart 정보를 보여주는 쿼리
		return template.selectList("CartMapper.selectCartList", userid);
	}

}
