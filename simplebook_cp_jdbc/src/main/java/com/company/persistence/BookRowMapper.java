package com.company.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.domain.BookVO;

public class BookRowMapper implements RowMapper<BookVO> {

	@Override
	public BookVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BookVO vo = new BookVO();
		vo.setCode(rs.getInt("code"));
		vo.setPrice(rs.getInt("price"));
		vo.setTitle(rs.getString("title"));
		vo.setWriter(rs.getString("writer"));
		return vo;
	}

}
