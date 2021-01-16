package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.persistence.BookDAO;

@Service("service")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO dao;

	@Override
	public boolean insertBook(BookVO vo) {
		// TODO Auto-generated method stub
		return dao.insert(vo) > 0 ? true : false;
	}

	@Override
	public boolean updateBook(BookVO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean deleteBook(int code) {
		// TODO Auto-generated method stub
		return dao.delete(code) > 0 ? true : false;
	}

	@Override
	public BookVO getRow(int code) {
		// TODO Auto-generated method stub
		return dao.getRow(code);
	}

	@Override
	public List<BookVO> getList() {
		// TODO Auto-generated method stub
		return dao.getList();
	}

}
