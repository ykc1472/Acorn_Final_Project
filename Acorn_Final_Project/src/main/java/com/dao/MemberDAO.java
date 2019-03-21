package com.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;
@Repository
public class MemberDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public int phoneNumberCheck(String phoneNumber) {
		
		return template.selectOne("MemberMapper.phoneNumberCheck", phoneNumber);
	}
	
	public int nickNameCheck(String nickName) {
		
		return template.selectOne("MemberMapper.nickNameCheck", nickName);
	}
	
	public int userIDCheck(String userid) {
		
		return template.selectOne("MemberMapper.userIDCheck", userid);
	}
	
	public int userAdd(MemberDTO dto) {
		System.out.println(dto);
		return template.insert("MemberMapper.userAdd");
	}
	
	public MemberDTO loginAction(MemberDTO dto) {
	
		return template.selectOne("MemberMapper.loginAction", dto);
	}

	public HashMap<String, String> idSearch(MemberDTO dto) {
		return template.selectOne("MemberMapper.idSearch",dto);
	}
	
	public String passwdSearch2(MemberDTO dto) {
		return template.selectOne("MemberMapper.passwdSearch2",dto);
	}
	
	public String passwdSearch(MemberDTO dto) {
		return template.selectOne("MemberMapper.passwdSearch",dto);
	}
}
