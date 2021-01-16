package com.company.mapper;

import java.util.List;

import com.company.domain.BookVO;
import com.company.domain.SearchVO;

public interface BookMapper {
	public int insert(BookVO vo);

	public int delete(int code);

	public int update(BookVO vo);

	public List<BookVO> selectAll();

	public List<BookVO> search(SearchVO vo);

}
