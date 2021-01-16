package com.company.board;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardClient {

	public static void main(String[] args) {

		log.trace("BoardClient 실행");

		ApplicationContext ctx = new ClassPathXmlApplicationContext("board_config2.xml");
		// 서비스 호출
		BoardService service = (BoardService) ctx.getBean("service");
		BoardVO vo = new BoardVO();
		vo.setTitle("스프링 컨테이너11");
		vo.setContent("스프링 주요 개념 - DI11");
		vo.setWriter("홍길동11");
		int result = service.insertBoard(vo);
		if (result > 0) {
			System.out.println("삽입 성공");
		}
		// 전체 리스트
		List<BoardVO> list = service.getList();
		for (BoardVO bvo : list) {
			System.out.println(bvo.toString());
		}

//		// 게시글 하나 가져오기
//		BoardVO row = service.getRow(8);
//		System.out.println("게시글하나 가져오기 : " + row.toString());
//		// 게시글 하나 삭제하기
//		if (service.deleteBoard(row) > 0) {
//			System.out.println("삭제성공");
//
//		} else {
//			System.out.println("삭제실패");
//		}
//		// 게시글 수정하기
//		BoardVO updateRow = new BoardVO();
//		updateRow.setBno(15);
//		updateRow.setTitle("스피링 수정수정");
//		updateRow.setContent("수프링부요 수정수정수정");
//		if (service.updateBoard(updateRow) > 0) {
//			System.out.println("수정성공");
//		}
	}

}
