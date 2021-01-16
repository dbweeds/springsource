package com.company.factorial;

import org.springframework.stereotype.Component;

@Component("rec")
public class RecCalc implements Calculator {

	@Override
	public long factorial(long num) {

		// 재귀호풀 팩토리얼
		if (num == 0) {
			long end = System.nanoTime();
			return 1;
		} else {
			return num * factorial(num - 1);
		}
		// 종료시간
	}

}
