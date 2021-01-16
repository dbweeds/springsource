package com.company.service;

import java.util.List;

import com.company.domain.BookVO;
import com.company.domain.SearchVO;

public interface BookService {
	public boolean insert(BookVO vo);

	public boolean delete(int code);

	public boolean update(BookVO vo);

	public List<BookVO> selectAll();

	public List<BookVO> search(SearchVO vo);
}
