package com.company.service;

import java.util.List;

import com.company.domain.MemberVO;

public interface MemberService {
	public boolean MemberInsert(MemberVO vo);

	public boolean MemberUpdate(MemberVO vo);

	public boolean MemberDelete(MemberVO vo);

	public MemberVO getMember(MemberVO vo);

	public List<MemberVO> getMemberList();
}
