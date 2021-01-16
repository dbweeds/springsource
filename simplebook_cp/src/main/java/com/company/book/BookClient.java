package com.company.book;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookVO;
import com.company.service.BookService;

public class BookClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("book_config.xml");
		BookService service = (BookService) ctx.getBean("service");
		int code = 1010;
		// 도서정보 입력
		BookVO insertVO = new BookVO(code, "자바자바스크릅트", "정인용", 15000);
		if (service.insertBook(insertVO)) {
			System.out.println("도서 입력 성공");
			System.out.println(service.getRow(code));
		}

		BookVO updateVO = new BookVO();
		updateVO.setCode(code);
		updateVO.setPrice(52154);
		if (service.updateBook(updateVO)) {
			System.out.println("도서 수정 성공");
			System.out.println(service.getRow(code));
		}
		if (service.deleteBook(code)) {
			System.out.println("도서 삭제 성공");
		}
		// 전체 리스트 가져오기
		List<BookVO> list = service.getList();
		for (BookVO vo : list) {
			System.out.println(vo);
		}

	}

}
