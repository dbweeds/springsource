package com.company.member;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.MemberVO;
import com.company.service.MemberService;

public class MemberClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("member_config.xml");
		MemberService service = (MemberService) ctx.getBean("service");
		String userid = "dddd";
		String password = "4444";
		MemberVO login = new MemberVO();
		login.setUserid(userid);
		login.setPassword(password);

		System.out.println();
		System.out.println("인서트");
		System.out.println();
		MemberVO vo = new MemberVO(userid, password, "4", "남", "4444@naver.com");
		if (service.MemberInsert(vo)) {
			System.out.println("멤버 입력 완료");
			System.out.println(service.getMember(login));
		}
		System.out.println();
		System.out.println("업데이트");
		System.out.println();
		login.setPassword("ddddd");
		if (service.MemberUpdate(login)) {
			System.out.println("멤버 수정 완료");
			System.out.println(service.getMember(login));
		}
		System.out.println();
		System.out.println("딜리트");
		System.out.println();
		if (service.MemberDelete(login)) {
			System.out.println("맴버 삭제 완료");
		}

		System.out.println();
		System.out.println("리스트");
		System.out.println();
		List<MemberVO> list = service.getMemberList();
		for (MemberVO vo1 : list) {
			System.out.println(vo1);
		}
	}

}
