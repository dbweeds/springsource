package com.company.service;

import com.company.domain.AuthVO;
import com.company.domain.LoginVO;
import com.company.domain.MemberVO;
import com.company.domain.updateVO;

public interface RegisterService {
	// 아이디중복
	public MemberVO selectById(String userid);

	// 회원가입
	public boolean member(MemberVO vo);

	// 로그인
	public AuthVO signin(LoginVO vo);

	public boolean update(updateVO vo);

	public boolean leaveMember(LoginVO vo);
}
