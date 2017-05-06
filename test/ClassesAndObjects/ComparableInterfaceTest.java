package ClassesAndObjects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ClassesAndObjects.ComparableInterface.ComparableGenericClass;
import org.junit.Ignore;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ComparableInterfaceTest {

	ComparableInterface testInstance = new ComparableInterface();
	ComparableGenericClass testGenericObjectThis;
	ComparableGenericClass testGenericObjectArgument;
	ComparableInterface.ComparableSpecificClass testSpecificObjectThis;
	ComparableInterface.ComparableSpecificClass testSpecificObjectArgument;

	public ComparableInterfaceTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}


	/*
	-1 	This is LESS than Argument
	0 	This EQUALS Argument
	1	This is GREATER than Argument	
	
	*/
	/**
	 * Test equality
	 */
	@Test
	public void testGenericObjectAsStringEquals() {
		testGenericObjectThis = testInstance.new ComparableGenericClass<String>("AAAA");
		testGenericObjectArgument = testInstance.new ComparableGenericClass<String>("AAAA");
		int actResult = testGenericObjectThis.compareTo(testGenericObjectArgument);
		assertEquals(0, actResult);
	}

	/**
	 * This test is ignored as there is no actual method implemented to compare inputs
	 * I can not determine if class type is String because of type erasure 
	 */
	@Ignore
	@Test
	public void testGenericObjectAsStringDifferent() {
		testGenericObjectThis = testInstance.new ComparableGenericClass<String>("AAAA");
		testGenericObjectArgument = testInstance.new ComparableGenericClass<String>("BBBB");
		int actResult = testGenericObjectThis.compareTo(testGenericObjectArgument);
		assertEquals(-1, actResult);
		int actResultInverese = testGenericObjectArgument.compareTo(testGenericObjectThis);
		assertEquals(1, actResultInverese);
	}

	/**
	 * Both specific object isManager field is true. CompareTo result expected to be 0
	 */
	@Test
	public void testSpecificObjectEquals() {
		testSpecificObjectThis =testInstance.new ComparableSpecificClass(true);
		testSpecificObjectArgument =testInstance.new ComparableSpecificClass(true);
		int actResult = testSpecificObjectThis.compareTo(testSpecificObjectArgument);
		assertEquals(0, actResult);
		int actResultInverese = testSpecificObjectArgument.compareTo(testSpecificObjectThis);
		assertEquals(0, actResultInverese);
	}

	/**
	 * This object isManager field is True
	 * Argument object isManager field is False
	 * this.compareTo(Argument) expected to be 1 (this is Manager, argument is not manager) 
	 * argument.compareTo(this) expected to be -1 (argument is not manager, this is manager)
	 */
	@Test
	public void testSpecificObjectDifferent() {
		testSpecificObjectThis =testInstance.new ComparableSpecificClass(true);
		testSpecificObjectArgument =testInstance.new ComparableSpecificClass(false);
		int actResult = testSpecificObjectThis.compareTo(testSpecificObjectArgument);
		assertEquals(1, actResult);
		int actResultInverese = testSpecificObjectArgument.compareTo(testSpecificObjectThis);
		assertEquals(-1, actResultInverese);
	}

}
