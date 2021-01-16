package com.company.poly;

public class LgTV implements TV {

	private Speaker speaker;
	private int price;

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		this.price = price;
	}
//	public LgTV() {
//		speaker = new SonySpeaker();
//	}
//	public LgTV(Speaker speaker) {
//		this.speaker = speaker;
//	}

	@Override
	public void turnOn() {
		System.out.println("LGTV - 전원 On, 가격 : " + price);

	}

	@Override
	public void turnOff() {
		System.out.println("LGTV - 전원 Off");
	}

	@Override
	public void soundUp() {
		// System.out.println("LGTV - 볼륨 Up");
		speaker.volumeUp();
	}

	@Override
	public void soundDown() {
		// System.out.println("LGTV - 볼륨 Down");
		speaker.volumeDown();
	}
}
