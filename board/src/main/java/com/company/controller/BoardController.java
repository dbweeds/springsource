package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.PageVO;
import com.company.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	BoardService service;

	@GetMapping("/register")
	public void registerGet() {
	}

	@PostMapping("/register")
	public String registerPost(BoardVO vo, RedirectAttributes rttr) {
		log.info("register post시작");
		service.regist(vo);
		log.info("msg" + vo.getBno());
		rttr.addFlashAttribute("result", vo.getBno());
		return "redirect:/board/list";
	}

	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		// 전체 목록 요청
		List<BoardVO> list = service.getList(cri);
		// 전체 게시물 수 요청
		int total = service.getTotalCnt(cri);
		model.addAttribute("list", list);
		model.addAttribute("pageVO", new PageVO(cri, total));

	}

	@GetMapping({ "/read", "/modify" })
	public void get(int bno, @ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("row", service.getRow(bno));
		log.info("criteria " + cri);
		// model.addAttribute("cri", cri);
	}

	@PostMapping("/remove")
	public String remove(int bno, Criteria cri, RedirectAttributes rttr) {
		service.remove(bno);
		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:list";
	}

	@PostMapping("/modify")
	public String modify(BoardVO vo, Criteria cri, RedirectAttributes rttr) {
		log.info(vo.toString());
		log.info("criteria - " + cri);
		service.modify(vo);
		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:list";

	}

}
