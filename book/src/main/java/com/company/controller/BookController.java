package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookVO;
import com.company.domain.SearchVO;
import com.company.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {
	@Autowired
	BookService service;

	@PostMapping("/insert")
	public String insertPost(BookVO vo, RedirectAttributes rttr) {

		try {
			if (service.insert(vo)) {
				return "redirect:/select";
			} else {
				return "redirect:/";
			}

		} catch (Exception e) {
			rttr.addFlashAttribute("page", "insert");
			return "redirect:/";
		}
	}

	@GetMapping("/select")
	public String selectAll(Model model) {
		log.info("전체리스트 가져오기");

		List<BookVO> list = service.selectAll();
		model.addAttribute("list", list);
		return "book_selectAll";
	}

	@PostMapping("/delete")
	public String delete(int code, RedirectAttributes rttr) {

		try {
			if (service.delete(code)) {
				rttr.addFlashAttribute("msg", "삭제되었습니다.");
				return "redirect:/select";
			}
			return "redirect:/";
		} catch (Exception e) {
			rttr.addFlashAttribute("msg", "삭제에 실패하였습니다.");
			rttr.addFlashAttribute("page", "delete");
			return "redirect:/";
		}
	}

	@PostMapping("/modify")
	public String update(BookVO vo, RedirectAttributes rttr) {

		try {
			if (service.update(vo)) {
				rttr.addFlashAttribute("msg", "수정되었습니다.");
				return "redirect:/select";
			}
			return "redirect:/";
		} catch (Exception e) {
			rttr.addFlashAttribute("msg", "수정에 실패하였습니다.");
			rttr.addFlashAttribute("page", "modify");
			return "redirect:/";
		}
	}

	@PostMapping("/search")
	public String search(SearchVO vo, Model model, RedirectAttributes rttr) {
		List<BookVO> list = service.search(vo);
		if (!list.isEmpty()) {
			model.addAttribute("list", list);
			return "book_searchAll";
		} else {
			rttr.addFlashAttribute("page", "search");
			return "redirect:/";
		}
	}

	@GetMapping("/search")
	public String searchGet(RedirectAttributes rttr) {
		rttr.addFlashAttribute("page", "search");
		return "redirect:/";
	}
}
