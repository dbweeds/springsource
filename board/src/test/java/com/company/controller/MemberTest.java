package com.company.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class) // 테스트 글래스로서 스프링 안에서 실행
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
@Slf4j
public class MemberTest {
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private DataSource ds;

	@Test // 테스트 메소드(public, void, 파라미터는 없음)
	public void test() {
		log.info("테스트 호출");

		String sql = "insert into spring_member(userid,userpw,username) values(?,?,?) ";

		for (int i = 0; i < 100; i++) {
			try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {// 트라이안괄호에
																											// 넣으면 사용후
																											// 알아서 클로즈를
																											// 해줌

				pstmt.setString(2, encoder.encode("pw" + i));

				if (i < 80) {
					pstmt.setString(1, "user" + i);
					pstmt.setString(3, "일반회원" + i);
				} else if (i < 90) {
					pstmt.setString(1, "manager" + i);
					pstmt.setString(3, "운영자" + i);
				} else {
					pstmt.setString(1, "admin" + i);
					pstmt.setString(3, "관리자" + i);
				}
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
