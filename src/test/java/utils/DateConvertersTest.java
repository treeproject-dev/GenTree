package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class DateConvertersTest {
	DateConverters dc = new DateConverters();

	@Test
	void testIsCorrectDate() {
		String wrongDate = "2019-AA-#";
		String okDate = "2020-05-01";
		boolean result = dc.isCorrectDate(wrongDate);
		assertEquals(false, result);
		result = dc.isCorrectDate(okDate);
		assertEquals(true, result);
		
	}

	@Test
	void testDateToString() {
		fail("Not yet implemented");
	}

	@Test
	void testStringToDate() {
		fail("Not yet implemented");
	}

}
