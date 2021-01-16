package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BoardVO;

//@Repository : 빈 생성(객체생성=new BoardDAO()) == @Component(기능은같으나 조금더 명확한 호칭을위헤 리파지토리로 씀)
@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(BoardVO vo) {
		System.out.println("====> spring jdbc insert");

		String sql = "insert into spring_board(bno,title,content,writer) values(seq_board.nextval,?,?,?)";
		int result = jdbcTemplate.update(sql, vo.getTitle(), vo.getContent(), vo.getWriter());
		return result;
	}

	public int update(BoardVO vo) {
		System.out.println("====> spring jdbc update");

		String sql = "update spring_board set title=?,content=?,updatedate=sysdate where bno=?";

		int result = jdbcTemplate.update(sql, vo.getTitle(), vo.getContent(), vo.getBno());
		return result;
	}

	public int delete(BoardVO vo) {
		System.out.println("====> spring jdbc delete");
		String sql = "delete from spring_board where bno=?";
		int result = jdbcTemplate.update(sql, vo.getBno());

		return result;
	}

	public BoardVO getRow(int bno) {
		System.out.println("====> spring jdbc getrow");
		String sql = "select * from spring_board where bno=?";

		Object[] args = { bno };

		BoardVO vo = jdbcTemplate.queryForObject(sql, args, new BoardRowMapper());

		return vo;
	}

	public List<BoardVO> getList() {
		System.out.println("====> spring jdbc getlist");
		String sql = "select * from spring_board";
		return jdbcTemplate.query(sql, new BoardRowMapper());
	}
}
