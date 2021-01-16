package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public boolean regist(ReplyVO reply) {
		return replyMapper.insert(reply) > 0 ? true : false;
	}

	@Override
	public ReplyVO select(int rno) {
		return replyMapper.select(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, int bno) {

		return replyMapper.list(cri, bno);
	}

	@Override
	public boolean update(ReplyVO vo) {
		return replyMapper.update(vo) > 0 ? true : false;
	}

}
