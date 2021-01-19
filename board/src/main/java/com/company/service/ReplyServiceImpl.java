package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Criteria;
import com.company.domain.ReplyPageVO;
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
	public ReplyPageVO getList(Criteria cri, int bno) {

		return new ReplyPageVO(replyMapper.countBno(bno), replyMapper.list(cri, bno));
	}

	@Override
	public boolean update(ReplyVO vo) {
		return replyMapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean delete(int rno) {
		return replyMapper.delete(rno) > 0 ? true : false;
	}

}
