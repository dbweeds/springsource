package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;

public interface BoardMapper {
	public int regist(BoardVO vo);

	public List<FileAttach> attachList(int bno);

	public int modify(BoardVO vo);

	public int remove(int bno);

	public List<BoardVO> getList(Criteria cri);

	public BoardVO getRow(int bno);

	public int totalCnt(Criteria cri);

	public int updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);

}
