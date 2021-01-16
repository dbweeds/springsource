package com.company.poly;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		// 컨테이너 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");

		TV samsung = (TV) ctx.getBean("lg");
		samsung.turnOn();
		samsung.soundUp();
		samsung.soundDown();
		samsung.turnOff();

		TV tv = (TV) ctx.getBean("samsung");
		System.out.println(samsung == tv ? "같은 객체" : "다른객체");
	}

}
