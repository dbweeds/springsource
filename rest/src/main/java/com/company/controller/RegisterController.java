package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.LoginVO;
import com.company.domain.MemberVO;
import com.company.domain.updateVO;
import com.company.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RegisterController {
	@Autowired
	private RegisterService service;

	@PostMapping("/regist")
	public ResponseEntity<String> regist(@RequestBody MemberVO vo) {
		log.info("회원가입요청");
		return service.member(vo) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/changePwd")
	public ResponseEntity<String> changePwd(@RequestBody updateVO vo) {
		log.info("회원수정요청");
		return service.update(vo) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/leave")
	public ResponseEntity<String> delete(@RequestBody LoginVO vo) {
		log.info("회원수정요청");
		return service.leaveMember(vo) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
