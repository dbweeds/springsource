package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller // @component @Service @repository
@RequestMapping("/sample/*") // http:localhost8080/sample
public class SampleController {

	@RequestMapping("/basic") // cmd.equals("list.do) == BookActionFactory.class
	public void basic() { // http:localhost8080/sample/basic
		log.info("basic....");/// sample/basic => view 리졸버
	}

	@RequestMapping("/test")
	public String test() { // http:localhost8080/sample/test
		log.info("test....");
		return "default";// default => view 리졸버
	}
}
