package HortonExercises.Ch05.Measures;

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
public class CarpetsAndrasTest {
	
	public CarpetsAndrasTest() {
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
	 * Test of main method, of class CarpetsAndras.
Carpet 1: Size = 4m 0cm 0mm by 2m 9cm 0mm
          Weight per sq. m. = 1.25
          Area = 8.36 sq. m.
          Weight = 0t 10kg 450g
          Weight of 200 carpets = 2t 90kg 0g

Carpet 2: Size = 3m 57cm 0mm by 5m 0cm 0mm
          Weight per sq. m. = 1.05
          Area = 17.849999999999998 sq. m.
          Weight = 0t 18kg 743g
          Weight of 60 carpets = 1t 124kg 580g
	 */
	@Test
	public void testCalculateTotalWeight_Carpet1() {
		//given
		int inputSize[][]={{4,0,0},{2,9,0}};
		double weightPerSqm=1.25;
		int numCarpets=200;
		CarpetsAndras testCarpet1 = new CarpetsAndras();
		testCarpet1.carpetSize=new McmLengthAndras(inputSize[0][0], inputSize[0][1],inputSize[0][2] );
		testCarpet1.carpetWidth=new McmLengthAndras(inputSize[1][0], inputSize[1][1],inputSize[1][2] );
		testCarpet1.carpetWeightPerSQM=weightPerSqm;
		testCarpet1.numberOfCarpets=numCarpets;
		//when
		int expWeight[]={0,10,450};
		int exp60Weight[]={2,90,0};
		double expArea=8.36;
		//then
		testCarpet1.calculateArea();
		assertEquals(expArea, testCarpet1.carpetArea,0.0);
	}
	
}
