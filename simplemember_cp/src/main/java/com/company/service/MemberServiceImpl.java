package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.MemberVO;
import com.company.persistence.memberDAO;

@Service("service")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private memberDAO dao;

	@Override
	public boolean MemberInsert(MemberVO vo) {
		return dao.insert(vo) > 0 ? true : false;
	}

	@Override
	public boolean MemberUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean MemberDelete(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.delete(vo) > 0 ? true : false;
	}

	@Override
	public MemberVO getMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.getRow(vo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return dao.getList();
	}

}
