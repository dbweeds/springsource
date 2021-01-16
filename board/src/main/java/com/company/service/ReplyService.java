package com.company.service;

import java.util.List;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;

public interface ReplyService {
	public boolean regist(ReplyVO reply);

	public ReplyVO select(int rno);

	public List<ReplyVO> getList(Criteria cri, int bno);

	public boolean update(ReplyVO vo);
}
