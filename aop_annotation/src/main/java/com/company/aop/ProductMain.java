package com.company.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductMain {

	public static void main(String[] args) throws Exception {
		log.info("productMain 실행");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop_config.xml");
		Product product = (Product) ctx.getBean("product");
		product.setCompany("lego");
		product.setName("lego 블럭");
		product.setPrice("250000");
		product.getInfo();
	}

}
