package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.MemberVO;
import com.company.mapper.MemberMapper;

@Service("service")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper mapper;

	@Override
	public boolean MemberInsert(MemberVO vo) {
		return mapper.insert(vo) > 0 ? true : false;
	}

	@Override
	public boolean MemberUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean MemberDelete(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.delete(vo) > 0 ? true : false;
	}

	@Override
	public MemberVO getMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.select(vo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return mapper.selectAll();
	}

}
