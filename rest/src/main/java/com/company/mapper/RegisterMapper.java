package com.company.mapper;

import com.company.domain.AuthVO;
import com.company.domain.LoginVO;
import com.company.domain.MemberVO;
import com.company.domain.updateVO;

public interface RegisterMapper {

	public MemberVO selectById(String userid);

	public int insert(MemberVO vo);

	public AuthVO signin(LoginVO vo);

	public int update(updateVO vo);

	public int leaveMember(LoginVO vo);
}
