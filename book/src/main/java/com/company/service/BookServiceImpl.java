package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.domain.SearchVO;
import com.company.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookMapper mapper;

	@Override
	public boolean insert(BookVO vo) {
		return mapper.insert(vo) > 0 ? true : false;
	}

	@Override
	public boolean delete(int code) {
		return mapper.delete(code) > 0 ? true : false;
	}

	@Override
	public boolean update(BookVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public List<BookVO> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<BookVO> search(SearchVO vo) {
		return mapper.search(vo);
	}

}
