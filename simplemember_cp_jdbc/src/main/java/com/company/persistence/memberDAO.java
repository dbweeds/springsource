package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.MemberVO;

@Repository
public class memberDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(MemberVO vo) {
		String sql = "insert into member values(?,?,?,?,?)";
		return jdbcTemplate.update(sql, vo.getUserid(), vo.getPassword(), vo.getName(), vo.getGender(), vo.getEmail());
	}

	public int update(MemberVO vo) {
		String sql = "update member set password=? where userid=?";
		return jdbcTemplate.update(sql, vo.getPassword(), vo.getUserid());
	}

	public int delete(MemberVO vo) {
		String sql = "delete from member where userid=? and password=?";
		return jdbcTemplate.update(sql, vo.getUserid(), vo.getPassword());
	}

	public MemberVO getRow(MemberVO member) {
		String sql = "select * from member where userid=? and password=?";
		Object[] args = { member.getUserid(), member.getPassword() };
		return jdbcTemplate.queryForObject(sql, args, new MemberRowMapper());
	}

	public List<MemberVO> getList() {
		String sql = "select * from member";
		return jdbcTemplate.query(sql, new MemberRowMapper());
	}
}
