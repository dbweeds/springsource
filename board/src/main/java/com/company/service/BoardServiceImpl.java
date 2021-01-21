package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.mapper.AttachMapper;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper mapper;
	@Autowired
	private AttachMapper attachMapper;

	@Override
	public boolean regist(BoardVO vo) {
		return mapper.regist(vo) > 0 ? true : false;
	}

	@Override
	public boolean remove(int bno) {
		return mapper.remove(bno) > 0 ? true : false;
	}

	@Override
	public boolean modify(BoardVO vo) {
		return mapper.modify(vo) > 0 ? true : false;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.getList(cri);
	}

	@Override
	public BoardVO getRow(int bno) {
		return mapper.getRow(bno);
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

}
