package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.domain.LoginVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class LoginController {

	// get => login.jsp, post=>사용자가 입력한 데이터를 가져와서 db작업
	// @RequestMapping(value = "/login", method = RequestMethod.GET) //
	// http://localhost:8080/login
	@GetMapping("/login")
	public void loginGet() {
		log.info("login get..");
	}

	// @RequestMapping(value = "/login", method = RequestMethod.POST) //
	// http://localhost:8080/login
	// 기존방법대로 사용자 입력값 받아오기
	// @PostMapping("/login")
	// public void loginPost(HttpServletRequest requset) {
	// log.info("login post..");
	// log.info("userid : " + requset.getParameter("userid"));
	// log.info("password : " + requset.getParameter("password"));
	// }
	// 두번째 방법 - 파라메터 처리(단, 이름 맞추기)
	@PostMapping("/login")
	public String loginPost(String userid, String password, Model model) {

		log.info("login post..");
		log.info("userid : " + userid);
		log.info("password : " + password);
		model.addAttribute("login", new LoginVO(userid, password));
		return "logout";
	}

//	@PostMapping("/login")
//	public String loginPost(LoginVO vo) {
//		log.info("login post..");
//		log.info("userid : " + vo.getUserid());
//		log.info("password : " + vo.getPassword());
//		return "logout";// logout=>view 리졸버
//	}

	// 네번째 방법 - 파라메터 처리(@RequestParam)
//	@PostMapping("/login")
//	public void loginPost(@RequestParam("userid")String id, String password) {
//
//		log.info("login post..");
//		log.info("userid : " + id);
//		log.info("password : " + password);
//	}
}
