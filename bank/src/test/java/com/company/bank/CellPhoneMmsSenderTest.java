package com.company.bank;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;

public class CellPhoneMmsSenderTest {
	final static String MESSAGE = "테스트 문자 메세지";

	@Test
	public void testSend() {
		CellPhoneService mock = Mockito.mock(CellPhoneService.class);
		CellPhoneMmsSender sender = new CellPhoneMmsSender(mock);

		sender.send(MESSAGE);
		verify(mock).sendMMS(MESSAGE);

	}
}
