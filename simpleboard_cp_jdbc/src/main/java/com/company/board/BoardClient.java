package com.company.board;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

public class BoardClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("board_config.xml");
		// 서비스 호출
		BoardService service = (BoardService) ctx.getBean("service");
		BoardVO vo = new BoardVO();
		vo.setTitle("스프링 컨테이너");
		vo.setContent("스프링 주요 개념 - DI");
		vo.setWriter("홍길동");
		int result = service.insertBoard(vo);
		if (result > 0) {
			System.out.println("삽입 성공");
		}
		// 전체 리스트
		List<BoardVO> list = service.getList();
		for (BoardVO bvo : list) {
			System.out.println(bvo.toString());
		}

		// 게시글 하나 가져오기
		BoardVO row = service.getRow(6);
		System.out.println("게시글하나 가져오기 : " + row.toString());
		// 게시글 하나 삭제하기
		if (service.deleteBoard(row) > 0) {
			System.out.println("삭제성공");

		} else {
			System.out.println("삭제실패");
		}
		// 게시글 수정하기
		BoardVO updateRow = new BoardVO();
		updateRow.setBno(7);
		updateRow.setTitle("스피링 수정수정");
		updateRow.setContent("수프링부요 수정수정수정");
		if (service.updateBoard(updateRow) > 0) {
			System.out.println("수정성공");
		}
	}

}
