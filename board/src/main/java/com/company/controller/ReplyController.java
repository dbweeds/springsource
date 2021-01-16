package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;
import com.company.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/replies/*")
public class ReplyController {
	@Autowired
	private ReplyService service;

	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody ReplyVO reply) {
		log.info("댓글 삽입" + reply);
		return service.regist(reply) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/{rno}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyVO> select(@PathVariable("rno") int rno) {
		log.info("댓글 하나 가져오기" + rno);
		return new ResponseEntity<ReplyVO>(service.select(rno), HttpStatus.OK);
	}

	@GetMapping(value = "/pages/{bno}/{page}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") int bno, @PathVariable("page") int page) {
		log.info("전체 댓글 가져오기" + bno + page);

		Criteria cri = new Criteria(page, 10);
		return new ResponseEntity<List<ReplyVO>>(service.getList(cri, bno), HttpStatus.OK);
	}

	@PutMapping(value = "/{rno}")
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo) {
		log.info("댓글 수정" + vo);
		vo.setRno(rno);
		return service.update(vo) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
