package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.BookVO;
import com.company.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookController {
	@Autowired
	private BookService service;

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BookVO>> list() {
		log.info("book list 요청");
		List<BookVO> list = service.getList();
		return new ResponseEntity<List<BookVO>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BookVO> get(@PathVariable("code") int code) {
		BookVO vo = service.getRow(code);
		return new ResponseEntity<BookVO>(vo, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{code}")
	public ResponseEntity<String> remove(@PathVariable("code") int code) {

		return service.deleteBook(code) ? new ResponseEntity<String>("succcess", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<String> update(@RequestBody BookVO vo) {
		log.info("book update 요청" + vo);
		return service.updateBook(vo) ? new ResponseEntity<String>("succcess", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/new")
	public ResponseEntity<String> insert(@RequestBody BookVO vo) {
		return service.insertBook(vo) ? new ResponseEntity<String>("succcess", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/insert")
	public ResponseEntity<String> insertPost(BookVO vo) {
		return service.insertBook(vo) ? new ResponseEntity<String>("succcess", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

}
