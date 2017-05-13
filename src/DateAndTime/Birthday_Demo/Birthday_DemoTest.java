package DateAndTime.Birthday_Demo;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Birthday_DemoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void isYearValidTest() {
		// Assertion
		assertFalse(Birthday_Demo.isYearValid(3000));
		assertTrue(Birthday_Demo.isYearValid(2000));
	}

	@Test
	public void isMonthValidTest() {
		// Assertion
		assertFalse(Birthday_Demo.isMonthValid(3000));
		assertFalse(Birthday_Demo.isMonthValid(0));
		assertTrue(Birthday_Demo.isMonthValid(12));
	}

	@Test
	public void isDayValidTest() {
		// Assertion
		assertFalse(Birthday_Demo.isDayValid(3000, 1, 2015)); // non leap-year
		assertFalse(Birthday_Demo.isDayValid(0, 1, 2015));
		assertFalse(Birthday_Demo.isDayValid(29, 2, 2015)); // non-leap-year
		assertTrue(Birthday_Demo.isDayValid(29, 2, 2016)); // 2016 is a leapyear
		assertFalse(Birthday_Demo.isDayValid(31, 4, 2015));
		assertTrue(Birthday_Demo.isDayValid(31, 3, 2015));
	}

}
