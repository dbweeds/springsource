package com.company.poly;

public class TVUser {

	public static void main(String[] args) {
		// 다형성

		LgTV tv = new LgTV();
		tv.setSpeaker(new AppleSpeaker());
		tv.turnOn();
		tv.soundUp();
		tv.soundDown();
		tv.turnOff();

	}

}
