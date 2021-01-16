package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.BoardVO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
public class BoardController {

	@GetMapping("/insert")
	public void insert() {
		log.info("insert 요청.....");
	}

	@PostMapping("/insert")
	public String insertForm(@ModelAttribute("vo") BoardVO vo) {
		log.info(vo);
		return "/board/view";
	}

	@PostMapping("/view")
	public void view(@ModelAttribute("vo") BoardVO vo) {
		log.info(vo);
	}

	@GetMapping("/select")
	public void select() {
		log.info("select 요청.....");
	}

}
