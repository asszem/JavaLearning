package codingBat.array_3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class maxMirrorTest {

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
	public void testCompareMirror_invalidMirror() {
		int[] a={1,2,3,4};
		int[] b={5,6,7,8};
		assertFalse(maxMirror.compareMirror(a, b));
	}
	@Test
	public void testCompareMirror_validMirror() {
		int[] a={1,2,3,4};
		int[] b={4,3,2,1};
		assertTrue(maxMirror.compareMirror(a, b));
	}
	@Test
	public void testCompareMirror_validOddMirror() {
		int[] a={1,2,1};
		int[] b={1,2,1};
		assertTrue(maxMirror.compareMirror(a, b));
	}

}
