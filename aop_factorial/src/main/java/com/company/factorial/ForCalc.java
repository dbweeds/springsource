package com.company.factorial;

import org.springframework.stereotype.Component;

@Component("fact")
public class ForCalc implements Calculator {

	@Override
	public long factorial(long num) {

		// for 문으로 팩토리얼 구하기
		long result = 1;
		for (int i = 1; i <= num; i++) {
			result *= i;
		}

		return result;
	}

}
