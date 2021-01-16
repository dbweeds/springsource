package com.company.mapper;

import java.util.List;

import com.company.domain.MemberVO;

public interface MemberMapper {
	public int insert(MemberVO vo);

	public int update(MemberVO vo);

	public int delete(MemberVO vo);

	public MemberVO select(MemberVO vo);

	public List<MemberVO> selectAll();
}
