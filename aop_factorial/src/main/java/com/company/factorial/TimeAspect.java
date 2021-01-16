package com.company.factorial;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAspect {
	@Around(value = "execution(* com.company.factorial..*(..))")
	public Object measure(ProceedingJoinPoint pjp) throws Throwable {
		// 시작시간
		long start = System.nanoTime();

		try {
			// factorial 결과를 리턴하기 위해 필요함
			Object obj = pjp.proceed();
			return obj;
		} finally {
			// 종료시간
			long end = System.nanoTime();
			// 걸린시간 출력하기
			System.out.println("걸린시간 : " + (end - start));
		}

	}
}
