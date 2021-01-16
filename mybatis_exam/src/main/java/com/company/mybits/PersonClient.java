package com.company.mybits;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.PersonVO;
import com.company.service.PersonService;

public class PersonClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		PersonService service = (PersonService) ctx.getBean("person");
		// service.insertPerson("kkang", "Mr.Kimi");
		// service.updatePerson("kkang", "미스터킴");
		// service.deltePerson("kkang");
		for (PersonVO vo : service.selectAll()) {
			System.out.println(vo);
		}
		System.out.println(service.getPerson("kang"));
	}

}
