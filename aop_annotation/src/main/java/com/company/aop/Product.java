package com.company.aop;

import org.springframework.stereotype.Component;

import lombok.Setter;

@Setter
@Component
public class Product {
	public String company;
	public String name;
	public String price;

	public void getInfo() {
		//
		System.out.println("회사명 : " + company);
		System.out.println("제품명 : " + name);
		System.out.println("가격 : " + price);
		// throw new Exception("강제 예외 발생");
	}
}
