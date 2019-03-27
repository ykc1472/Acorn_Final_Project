package com.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MemberDAO;
import com.dto.MemberDTO;
@Service
public class MemberService {
	@Autowired
	MemberDAO dao;
	
	@Transactional
	public int phoneNumberCheck(String phoneNumber) {
		int success = 0;
		success = dao.phoneNumberCheck(phoneNumber);
	
		return success;
	}
	@Transactional
	public int nickNameCheck (String nickName) {
		int success = 0;
		success = dao.nickNameCheck(nickName);
		
		return success;
	}
	@Transactional
	public int userIDCheck (String userid) {
		int success = 0;
		
		success = dao.userIDCheck(userid);
	
		return success;
	}
	@Transactional
	public int userAdd (MemberDTO dto) {
		int success = 0;
		success = dao.userAdd(dto);
	
		return success;
	}
	@Transactional
	public MemberDTO loginAction (MemberDTO dto) {
		MemberDTO loginInfo = null;
		loginInfo = dao.loginAction(dto);
		System.out.println(loginInfo);
		return loginInfo;
	}
	
	@Transactional
	public HashMap<String, String> idSearch(MemberDTO dto) {
		HashMap<String, String> map = null;
		map = dao.idSearch(dto);
		
		return map;
	}
	
	public String passwdSearch2(MemberDTO dto) {
	
		String userpw = null;
	
		userpw = dao.passwdSearch2(dto);
	
		return userpw;
	}
	
	@Transactional
	public String passwdSearch(MemberDTO dto) {
	
		String userpw = null;
	
		userpw = dao.passwdSearch(dto);
		
		return userpw;
	}
	
}
