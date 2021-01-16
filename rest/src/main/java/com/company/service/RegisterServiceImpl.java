package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.AuthVO;
import com.company.domain.LoginVO;
import com.company.domain.MemberVO;
import com.company.domain.updateVO;
import com.company.mapper.RegisterMapper;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterMapper mapper;

	@Override
	public MemberVO selectById(String userid) {
		return mapper.selectById(userid);
	}

	@Override
	public boolean member(MemberVO vo) {
		return mapper.insert(vo) > 0 ? true : false;
	}

	@Override
	public AuthVO signin(LoginVO vo) {
		return mapper.signin(vo);
	}

	@Override
	public boolean update(updateVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean leaveMember(LoginVO vo) {
		return mapper.leaveMember(vo) > 0 ? true : false;
	}

}
