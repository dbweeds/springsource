package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardVO;
import com.company.mapper.BoardMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("service") // 빈 생성 == @Component(기능은같으나 조금더 명확한 호칭을위헤 서비스로 씀)
public class BoardServiceImpl implements BoardService {

	@Autowired // 이미 생성된 빈을 주입
	private BoardMapper mapper;

	@Override
	public int insertBoard(BoardVO vo) {
		log.info("service insert 실행");
		return mapper.insert(vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		log.info("service update 실행");
		return mapper.update(vo);
	}

	@Override
	public int deleteBoard(BoardVO vo) {
		log.info("service delete 실행");
		return mapper.delete(vo);
	}

	@Override
	public BoardVO getRow(int bno) {
		log.info("service select 실행");
		return mapper.select(bno);
	}

	@Override
	public List<BoardVO> getList() {
		log.info("service selectAll 실행");
		return mapper.selectAll();
	}

}
