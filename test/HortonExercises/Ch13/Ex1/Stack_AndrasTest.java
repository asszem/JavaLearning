package HortonExercises.Ch13.Ex1;

import HortonExercises.Ch13.Stack_Andras.Stack_Andras;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Stack_AndrasTest {
	
	public Stack_AndrasTest() {
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

	/**
	 * Test of pop method returns null when the stack is empty
	 */
	@Test
	public void testPopWhenStackIsEmpty() {
		Stack_Andras<Double> testInstance = new Stack_Andras<>();
		Double actResult=testInstance.pop();
		assertEquals(null, actResult);
	}

	/**
	 * Test of push method adds the correct value to the top of stack
	 */
	@Test
	public void testPush() {
		Stack_Andras<Double> testDoubleInstance=new Stack_Andras<>();
		Stack_Andras<String> testStringInstance=new Stack_Andras<>();
		testDoubleInstance.push(1.0);
		testDoubleInstance.push(2.0);
		testDoubleInstance.push(3.0);
		testStringInstance.push("First");
		testStringInstance.push("Second");
		testStringInstance.push("Third");
		double actTopStackDouble= testDoubleInstance.getStackItemObject(testDoubleInstance.topStackItem);
		String actTopStackString= testStringInstance.getStackItemObject(testStringInstance.topStackItem);
		double expTopStackDouble=3.0;
		String expTopStackString="Third";
		assertEquals(expTopStackDouble, actTopStackDouble, 0.0);
		assertEquals(expTopStackString, actTopStackString);
	}

	/**
	 * Test of pop method returns correct value from top of stack
	 */
	@Test
	public void testPopFromNonEmptyStack() {
		Stack_Andras<Double> testDoubleInstance=new Stack_Andras<>();
		Stack_Andras<String> testStringInstance=new Stack_Andras<>();
		testDoubleInstance.push(1.0);
		testDoubleInstance.push(2.0);
		testDoubleInstance.push(3.0); 					//Pop this
		testStringInstance.push("First");
		testStringInstance.push("Second");
		testStringInstance.push("Third");				//Pop this
		double actPopDouble=testDoubleInstance.pop();
		String actPopString=testStringInstance.pop();
		double expPopDouble=3.0;
		String expPopString="Third";
		assertEquals(expPopDouble, actPopDouble, 0.0);
		assertEquals(expPopString, actPopString);
	}

	/**
	 * Test of pop method removes the last item from stack
	 */
	@Test
	public void testPopRemovesLastStackItem() {
		Stack_Andras<Double> testDoubleInstance=new Stack_Andras<>();
		Stack_Andras<String> testStringInstance=new Stack_Andras<>();
		testDoubleInstance.push(1.0);
		testDoubleInstance.push(2.0);			//This should be top item after pop
		testDoubleInstance.push(3.0); 					
		testDoubleInstance.pop();
		testStringInstance.push("First");
		testStringInstance.push("Second");		//This should be top item after pop
		testStringInstance.push("Third");				
		testStringInstance.pop();
		double actTopStackDouble= testDoubleInstance.getStackItemObject(testDoubleInstance.topStackItem);
		String actTopStackString= testStringInstance.getStackItemObject(testStringInstance.topStackItem);
		double expTopStackDouble=2.0;
		String expTopStackString="Second";
		assertEquals(expTopStackDouble, actTopStackDouble, 0.0);
		assertEquals(expTopStackString, actTopStackString);
	}

	/**
	 * Test push after pop adds the new value to the top of the list
	 */
	@Test
	public void testPushAfterPop() {
		Stack_Andras<Double> testDoubleInstance=new Stack_Andras<>();
		Stack_Andras<String> testStringInstance=new Stack_Andras<>();
		testDoubleInstance.push(1.0);
		testDoubleInstance.push(2.0);			//This should be top item after pop
		testDoubleInstance.push(3.0); 					
		testDoubleInstance.pop();
		testDoubleInstance.push(3.0); 					
		testStringInstance.push("First");
		testStringInstance.push("Second");		//This should be top item after pop
		testStringInstance.push("Third");				
		testStringInstance.pop();
		testStringInstance.push("Third");				
		double actTopStackDouble= testDoubleInstance.getStackItemObject(testDoubleInstance.topStackItem);
		String actTopStackString= testStringInstance.getStackItemObject(testStringInstance.topStackItem);
		double expTopStackDouble=3.0;
		String expTopStackString="Third";
		assertEquals(expTopStackDouble, actTopStackDouble, 0.0);
		assertEquals(expTopStackString, actTopStackString);
	}

	/**
	 * Test pop of last item resets the root and top stack item to null
	 */
	@Test
	public void testPopLastItem() {
		Stack_Andras<Double> testDoubleInstance=new Stack_Andras<>();
		Stack_Andras<String> testStringInstance=new Stack_Andras<>();
		testDoubleInstance.push(1.0);
		testDoubleInstance.pop();
		testStringInstance.push("Third");				
		testStringInstance.pop();
		assertEquals(null, testDoubleInstance.getStackItemObject(testDoubleInstance.rootStackItem));
		assertEquals(null, testDoubleInstance.getStackItemObject(testDoubleInstance.topStackItem));
		assertEquals(null, testStringInstance.getStackItemObject(testStringInstance.rootStackItem));
		assertEquals(null, testStringInstance.getStackItemObject(testStringInstance.topStackItem));
	}


	
}
