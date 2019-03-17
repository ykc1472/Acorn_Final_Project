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
		int success = template.selectOne("MemberMapper.phoneNumberCheck", phoneNumber);
		
		return success;
	}
	
	public int nickNameCheck(String nickName) {
		int success = template.selectOne("MemberMapper.nickNameCheck", nickName);
		
		return success;
	}
	
	public int userIDCheck(String userid) {
		int success = template.selectOne("MemberMapper.userIDCheck", userid);
		
		return success;
	}
	
	public int userAdd(MemberDTO dto) {
		int success = template.insert("MemberMapper.userAdd", dto);
		
		return success;
	}
	
	public MemberDTO loginAction(MemberDTO dto) {
		MemberDTO loginInfo = template.selectOne("MemberMapper.loginAction", dto);
	
		return loginInfo;
	}

	public HashMap<String, String> idSearch(MemberDTO dto) {
		HashMap map = template.selectOne("MemberMapper.idSearch",dto);
		return map;
	}
	
	public String passwdSearch2(MemberDTO dto) {
		String success = template.selectOne("MemberMapper.passwdSearch2",dto);
		return success;
	}
	
	public String passwdSearch(MemberDTO dto) {
		String success = template.selectOne("MemberMapper.passwdSearch",dto);
		return success;
	}
}
