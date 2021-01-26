package com.company.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;
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
		log.info("register post시작" + vo);
		// 파일첨부 확인
//		if (vo.getAttachList() != null) {
//			vo.getAttachList().forEach(attach -> log.info("" + attach));
//		}

		if (service.regist(vo)) {
			rttr.addFlashAttribute("result", vo.getBno());
			return "redirect:/board/list";
		}
		return "redirect:register";
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
		// 게시물 번호에 해당ㅇ하는 첨부 파일 삭제(서버,데이터베이스도 삭제)

		// 서버 폴더 안 파일 삭제하기
		// 1.bno해당하는 첨부물 목록 알아내기
		List<FileAttach> attachList = service.getAttachList(bno);
		// 성공하면 리스트 보여주기
		if (service.remove(bno)) {// 2.데이터베이스 삭제(게시물,첨부물)
			// 3. 파일삭제
			deleteFiles(attachList);

			rttr.addFlashAttribute("result", "success");
		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:list";
	}

	private void deleteFiles(List<FileAttach> attachList) {
		log.info("첨부물삭제" + attachList);

		if (attachList == null || attachList.size() <= 0) {
			return;
		}
		for (FileAttach atta : attachList) {
			Path path = Paths.get("F:\\upload\\",
					atta.getUploadPath() + "\\" + atta.getUuid() + "_" + atta.getFileName());

			// 일반파이르 이미지 원본 파일 삭제
			try {
				Files.deleteIfExists(path);

				if (Files.probeContentType(path).startsWith("image")) {
					Path thumb = Paths.get("F:\\upload\\",
							atta.getUploadPath() + "\\s_" + atta.getUuid() + "_" + atta.getFileName());
					Files.delete(thumb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@PostMapping("/modify")
	public String modify(BoardVO vo, Criteria cri, RedirectAttributes rttr) {
		log.info(vo.toString());
		log.info("criteria - " + cri);
		// 파일첨부 확인
		if (vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> log.info("" + attach));
		}
		service.modify(vo);
		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:list";

	}

	// 첨부물 가져오기
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileAttach>> getAttachList(int bno) {
		log.info("첨부물 가져오기 : " + bno);
		List<FileAttach> list = service.getAttachList(bno);
		log.info("첨부물" + list);
		return new ResponseEntity<List<FileAttach>>(list, HttpStatus.OK);
	}

}
