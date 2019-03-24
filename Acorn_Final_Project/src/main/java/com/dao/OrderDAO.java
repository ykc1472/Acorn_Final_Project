package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;
import com.dto.OrderDTO;
@Repository
public class OrderDAO {
	@Autowired
	SqlSessionTemplate template;
	
	
	public List<OrderDTO> orderConfirm(List<OrderDTO> orderList){
		
		for(int i = 0 ; i < orderList.size() ; i++) {
			OrderDTO dto = template.selectOne("OrderMapper.orderConfirm", orderList.get(i));
			
			orderList.get(i).setFcategoryname(dto.getCategoryname());
			orderList.get(i).setFcode(dto.getFcode());
			orderList.get(i).setFtitle(dto.getFtitle());
			orderList.get(i).setContent(dto.getContent());
			orderList.get(i).setFprice(dto.getFprice());
			orderList.get(i).setFmainimage(dto.getFmainimage());
			orderList.get(i).setOptionname(dto.getOptionname());
			orderList.get(i).setOptionprice(dto.getOptionprice());
			orderList.get(i).setStock(dto.getStock());
		}
		
		return orderList;
	}
	
	public MemberDTO orderUserInfo(String userid) {
		MemberDTO dto = template.selectOne("MemberMapper.orderUserInfo", userid);
		return dto;
	}
	
	public int orderDone(List<OrderDTO> orderList) {
		
		int success = 0;
		for(OrderDTO order : orderList) {
			success += template.insert("OrderMapper.orderAllDone", order);
		}
		
		return success;
	}
	
	public List<OrderDTO> selectOrderAllDone(List<OrderDTO> orderList){
		return template.selectList("OrderMapper.selectOrderAllDone", orderList);
	}
	
	public int updateOptionStock(List<OrderDTO> orderList){
		int success = 0;
		for(OrderDTO order : orderList) {
			success += template.update("OrderMapper.updateOptionStock", order);
		}
		return success;
	}
	
	
}
