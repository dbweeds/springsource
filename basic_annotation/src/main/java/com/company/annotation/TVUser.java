package com.company.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		// 컨테이너 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");

		TV samsung = (TV) ctx.getBean("tv");
		samsung.turnOn();
		samsung.soundUp();
		samsung.soundDown();
		samsung.turnOff();

	}

}
