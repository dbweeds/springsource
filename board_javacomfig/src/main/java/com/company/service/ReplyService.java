package com.company.service;

import com.company.domain.Criteria;
import com.company.domain.ReplyPageVO;
import com.company.domain.ReplyVO;

public interface ReplyService {
	public boolean regist(ReplyVO reply);

	public ReplyVO select(int rno);

	public ReplyPageVO getList(Criteria cri, int bno);

	public boolean update(ReplyVO vo);

	public boolean delete(int rno);
}
