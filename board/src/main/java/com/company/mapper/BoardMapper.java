package com.company.mapper;

import java.util.List;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;

public interface BoardMapper {
	public int regist(BoardVO vo);

	public int modify(BoardVO vo);

	public int remove(int bno);

	public List<BoardVO> getList(Criteria cri);

	public BoardVO getRow(int bno);

	public int totalCnt(Criteria cri);

}
