package com.company.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component("log") // logadvice
@Aspect
public class LogAdvice {
//	@Before(value = "execution(* com.company.aop.Product.getInfo())")
//	public void beforeLog() {
//		System.out.println("[Before] 비지니스 로직 전 수행");
//	}

//	@After(value = "execution(* com.company.aop.Product.getInfo())")
//	public void afterLog() {
//		System.out.println("[afterLog] 비지니스 로직 후 수행 - 예외와 상관없이 호출");
//	}
//	@AfterThrowing(value = "execution(* com.company.aop.Product.getInfo())")
//	public void afterThrowLog() {
//		System.out.println("[afterThrowLog] 비지니스 로직 후 수행 - 예외 발생시 호출");
//	}
//	@AfterReturning(value = "execution(* com.company.aop.Product.getInfo())")
//	public void afterReturnLog() {
//		System.out.println("[afterReturnLog] 비지니스 로직 후 수행 - 정상 수행시 호출");
//	}
	@Around(value = "execution(* com.company.aop.Product.getInfo())")
	public void aroundLog(ProceedingJoinPoint pjp) {
		System.out.println("[Around] 비지니스 로직 전 수행");

		try {
			pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println("[Around] 비지니스 로직 후 수행");

	}
}
