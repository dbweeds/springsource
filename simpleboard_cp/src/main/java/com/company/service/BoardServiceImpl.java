package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardVO;
import com.company.persistence.BoardDAO;

@Service("service") // 빈 생성 == @Component(기능은같으나 조금더 명확한 호칭을위헤 서비스로 씀)
public class BoardServiceImpl implements BoardService {

	@Autowired // 이미 생성된 빈을 주입
	private BoardDAO dao;

	@Override
	public int insertBoard(BoardVO vo) {

		return dao.insert(vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return dao.update(vo);
	}

	@Override
	public int deleteBoard(BoardVO vo) {
		return dao.delete(vo);
	}

	@Override
	public BoardVO getRow(int bno) {
		return dao.getRow(bno);
	}

	@Override
	public List<BoardVO> getList() {
		return dao.getList();
	}

}
