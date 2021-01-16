package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AuthVO;
import com.company.domain.LoginVO;
import com.company.domain.MemberVO;
import com.company.domain.updateVO;
import com.company.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // log4j2 랑 동일
@RequestMapping("/member/*")
public class RegisterController {
	@Autowired
	RegisterService service;
	@Autowired
	HttpSession session;

	@GetMapping("/step1")
	public void step1Get() {
		log.info("step1 시작....");
	}

	@PostMapping("/step2")
	public String step2Post(boolean agree, RedirectAttributes rttr) {
		log.info("step2 시작...." + agree);

		if (agree) {
			return "/member/step2";
		} else {
			rttr.addFlashAttribute("check", "false");
			return "redirect:/member/step1";
		}
	}

	@PostMapping("/step3")
	public String step3Post(@ModelAttribute("vo") MemberVO vo) {
		log.info(vo.toString());
		if (vo.isPasswordEqualTOConfirmPassword()) {
			service.member(vo);
			return "/member/step3";
		} else {
			return "/member/step2";
		}
	}

	// get방식으로 요청하는 핸들러
	@GetMapping(value = { "/step2", "/step3" })
	public String handleStep2_3() {
		log.info("/step2,/step3 직접요청");
		return "redirect:step1";
	}

	// 중복아이디
	@ResponseBody // 보내는 리턴값은 실제의 값임
	@PostMapping("/checkId")
	public String checkId(String userid) {
		log.info("중복아이디검사요청" + userid);
		MemberVO dupId = service.selectById(userid);
		if (dupId != null) {
			return "false";
		}
		return "true";
	}

	@GetMapping("/signin")
	public void logininGet() {
		log.info("login 시작....");
	}

	@PostMapping("/signin")
	public String logininPost(LoginVO vo, RedirectAttributes rttr) {
		log.info("login 시작....");
		AuthVO authVO = service.signin(vo);
		if (authVO != null) {
			session.setAttribute("auth", authVO);
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("check", "false");
			return "redirect:/member/signin";
		}
	}

	@GetMapping("/logout")
	public String logoutGet() {
		log.info("logout 시작....");
		session.removeAttribute("login");
		return "redirect:/";
	}

	@GetMapping("/changePwd")
	public void changePwdGet() {
		log.info("modifyGet시작....");
	}

	@PostMapping("/changePwd")
	public String changePwdPost(updateVO vo, @SessionAttribute("auth") AuthVO authVO, RedirectAttributes rttr) {
		log.info("modifyPost 시작...." + authVO.getUserid());
		// vo.setUserid(((AuthVO) session.getAttribute("login")).getUserid());
		vo.setUserid(authVO.getUserid());
		if (!vo.newPasswordEqualsConfirmPassword() || !service.update(vo)) {
			rttr.addFlashAttribute("check", "false");
			return "redirect:/member/changePwd";
		} else {
			session.removeAttribute("login");
			return "redirect:/member/signin";
		}

	}

	@GetMapping("/leave")
	public void leaveGet() {
	}

	@PostMapping("/leave")
	public String leavePost(LoginVO vo, RedirectAttributes rttr) {
		if (!service.leaveMember(vo)) {
			rttr.addFlashAttribute("check", "false");
			return "redirect:/member/leave";
		} else {
			session.removeAttribute("login");
			return "redirect:/";
		}
	}
}
