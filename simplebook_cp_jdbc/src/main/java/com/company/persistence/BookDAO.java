package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BookVO;

@Repository
public class BookDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(BookVO vo) {

		String sql = "insert into BookTBL values(?,?,?,?)";

		int result = jdbcTemplate.update(sql, vo.getCode(), vo.getTitle(), vo.getWriter(), vo.getPrice());

		return result;
	}

	public int update(BookVO vo) {

		String sql = "update bookTBL set price=? where code=?";
		int result = jdbcTemplate.update(sql, vo.getPrice(), vo.getCode());

		return result;
	}

	public int delete(int code) {

		String sql = "delete from bookTBL where code=?";

		int result = jdbcTemplate.update(sql, code);

		return result;
	}

	public BookVO getRow(int code) {

		String sql = "select * from bookTBL where code=?";

		Object[] args = { code };

		return jdbcTemplate.queryForObject(sql, args, new BookRowMapper());
	}

	public List<BookVO> getList() {

		String sql = "select * from bookTBL";

		return jdbcTemplate.query(sql, new BookRowMapper());

	}
}
