package com.company.service;

import java.util.List;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;

public interface BoardService {
	public boolean regist(BoardVO vo);

	public boolean remove(int bno);

	public boolean modify(BoardVO vo);

	public List<BoardVO> getList(Criteria cri);

	public BoardVO getRow(int bno);

	public int getTotalCnt(Criteria cri);
}
