package com.company.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
*
* @Scheduled => 스프링 프레임워크에 taskscheduler 인터페이스 구현되어 있음
*			 => 메소드는 void이고 , 파라메터를 갖지 않아야 함
*			 => 사용방식 -1. cron 표현식 사용하기(초 분 시 일 월 요일)(마지막에 연도는 비워두는경우가 많음)
*					   -2 fixedDelay : 이전에 실행된 task의 종료 시간으로 부터 정의된 시간만큼 경과후 실행(밀리세컨드)
*					   -3 fixedRate : 이전에 실행된 task의 시작 시간으로 부터 정의된 시간만큼 경과후 실행(밀리세컨드)
*
*
*/

@Component
public class TaskTest {
	@Scheduled(cron = "0 * * * * *")
	public void schedulerTest() {
		System.out.println("매 분 1 초마다 스케줄링 테스트..........");
	}

	@Scheduled(fixedDelay = 10000)
	public void schedulerTest2() {
		System.out.println("10초마다 스케줄링 테스트.........");
	}
}
