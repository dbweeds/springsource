package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		log.info("index 요청");
		return "index";
	}

	@GetMapping("/except")
	public void exceptTest(int no) {
		log.info("예외 테스트");
	}
}
