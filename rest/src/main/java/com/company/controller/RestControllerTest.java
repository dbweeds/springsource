package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.SampleVO;

import lombok.extern.slf4j.Slf4j;

@RestController // 리턴하는 모든 값들은 실제 값이 됨(jsp 페이지 개념이 아님)
@Slf4j
public class RestControllerTest {

	@GetMapping(value = "/hello", produces = "text/plain;charset=utf-8")
	public String sayHallo() {
		log.info("hello요청");
		return "Hello World";
	}

	@GetMapping(value = "/sendlist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<SampleVO> sendList() {
		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			SampleVO sample = new SampleVO();
			sample.setMno("12345");
			sample.setFirstName("hong");
			sample.setLastName("dong");
			list.add(sample);
		}
		return list;
	}

	@GetMapping("/check")
	public ResponseEntity<SampleVO> check(double height, double weight) {
		SampleVO vo = new SampleVO();
		vo.setMno("12345");
		vo.setFirstName(height + "");
		vo.setLastName(weight + "");
		ResponseEntity<SampleVO> entity = null;
		if (height < 150) {
			entity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);

		} else {
			entity = ResponseEntity.status(HttpStatus.OK).body(vo);

		}
		return entity;
	}

	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid) {
		return new String[] { "category : " + cat, "productId : " + pid };
	}
}
