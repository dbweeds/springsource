package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HandlerController {

	@GetMapping("/doA")
	public void doAGet() {
		log.info("doA요청");
	}

	@GetMapping("/doB")
	public void doB() throws Exception {
		log.info("doB요청");
		throw new Exception("강제발생");
	}
}
