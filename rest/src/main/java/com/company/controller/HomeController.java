package com.company.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.domain.SampleVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@ResponseBody
	@GetMapping("/test")
	public SampleVO test() {
		SampleVO sample = new SampleVO();
		sample.setMno("12345");
		sample.setFirstName("hong");
		sample.setLastName("dong");
		return sample;
	}

	@GetMapping("/book")
	public void book() {
		logger.info("book.jsp요청");
	}

	@GetMapping("/insert")
	public String insertGet() {
		logger.info("book_insert 요청");
		return "book_insert";
	}

	@GetMapping("/step2")
	public void registerGet() {
		logger.info("regster 요청");
	}

	@GetMapping("/changePwd")
	public void changePwdGet() {
		logger.info("regster 요청");
	}

	@GetMapping("/leave")
	public void leaveGet() {
		logger.info("leave 요청");
	}

}
