package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.Criteria;
import com.company.domain.ReplyPageVO;
import com.company.domain.ReplyVO;
import com.company.mapper.BoardMapper;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private BoardMapper boardMapper;

	@Transactional
	@Override
	public boolean regist(ReplyVO reply) {
		// 게시글의 댓글 수 수정
		boardMapper.updateReplyCnt(reply.getBno(), 1);

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

	@Transactional
	@Override
	public boolean delete(int rno) {
		ReplyVO reply = replyMapper.select(rno);
		boardMapper.updateReplyCnt(reply.getBno(), -1);
		return replyMapper.delete(rno) > 0 ? true : false;
	}

}
