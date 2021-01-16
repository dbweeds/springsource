package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.mapper.BookMapper;

@Service("service")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper mapper;

	@Override
	public boolean insertBook(BookVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo) > 0 ? true : false;
	}

	@Override
	public boolean updateBook(BookVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean deleteBook(int code) {
		// TODO Auto-generated method stub
		return mapper.delete(code) > 0 ? true : false;
	}

	@Override
	public BookVO getRow(int code) {
		// TODO Auto-generated method stub
		return mapper.select(code);
	}

	@Override
	public List<BookVO> getList() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

}
