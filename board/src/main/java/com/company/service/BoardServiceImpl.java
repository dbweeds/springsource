package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;
import com.company.mapper.AttachMapper;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper mapper;
	@Autowired
	private AttachMapper attachMapper;

	@Transactional
	@Override
	public boolean regist(BoardVO vo) {
		boolean result = mapper.regist(vo) > 0 ? true : false;
		addAttachMapper(vo);
		return result;
	}

	@Override
	public boolean remove(int bno) {
		attachMapper.remove(bno);
		return mapper.remove(bno) > 0 ? true : false;
	}

	@Override
	public boolean modify(BoardVO vo) {
		boolean result = mapper.modify(vo) > 0 ? true : false;
		attachMapper.remove(vo.getBno());
		addAttachMapper(vo);
		return result;
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

	@Override
	public List<FileAttach> getAttachList(int bno) {
		return mapper.attachList(bno);
	}

	private boolean addAttachMapper(BoardVO vo) {
		if (vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
			return true;
		}
		vo.getAttachList().forEach(attach -> {
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
		return true;
	}
}
