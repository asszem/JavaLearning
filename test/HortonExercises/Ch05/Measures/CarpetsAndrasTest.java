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

	static CarpetsAndrasTest testCarpet1Parameters; //static so all test methods can use the same
	static CarpetsAndrasTest testCarpet1ExpectedResults;
	int[][] carpetSize;
	double weightPerSqm;
	int numCarpets;
	int expSingleCarpetWeight[];
	int expTotalWeight[];
	double expArea;
	static final int METER_INDEX=0;
	static final int CENTIMETER_INDEX=1;
	static final int MILLIMETER_INDEX=2;
	static final int TON_INDEX=0;
	static final int KG_INDEX=1;
	static final int GRAM_INDEX=2;
	static final int CARPET_HEIGHT_INDEX=0;
	static final int CARPET_WIDTH_INDEX=1;


	static CarpetsAndras testCarpet1; //static so all test methods can use the same

	public CarpetsAndrasTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		//Initializing object holding input parameters 
		testCarpet1Parameters = new CarpetsAndrasTest(); // to hold expected result parameters
		int[][] inputSizeValues = {{4, 0, 0}, {2, 9, 0}};
		testCarpet1Parameters.carpetSize = inputSizeValues;
		testCarpet1Parameters.weightPerSqm = 1.25;
		testCarpet1Parameters.numCarpets = 200;

		//Initializing object holding expected results
		testCarpet1ExpectedResults= new CarpetsAndrasTest();
		int[] expSingleWeightValues = {0, 10, 450};
		testCarpet1ExpectedResults.expSingleCarpetWeight = expSingleWeightValues;
		int[] expTotalWeightValues = {2, 90, 0};
		testCarpet1ExpectedResults.expTotalWeight = expTotalWeightValues;
		testCarpet1ExpectedResults.expArea = 8.36;

		//Initializing testCarpet1 object
		testCarpet1 = new CarpetsAndras(); //create new Carpet object
		testCarpet1.carpetHeight = new McmLengthAndras(testCarpet1Parameters.carpetSize[CARPET_HEIGHT_INDEX][METER_INDEX], testCarpet1Parameters.carpetSize[CARPET_HEIGHT_INDEX][CENTIMETER_INDEX], testCarpet1Parameters.carpetSize[CARPET_HEIGHT_INDEX][MILLIMETER_INDEX]);
		testCarpet1.carpetWidth = new McmLengthAndras(testCarpet1Parameters.carpetSize[CARPET_WIDTH_INDEX][METER_INDEX], testCarpet1Parameters.carpetSize[CARPET_WIDTH_INDEX][CENTIMETER_INDEX], testCarpet1Parameters.carpetSize[CARPET_WIDTH_INDEX][MILLIMETER_INDEX]);
		testCarpet1.carpetWeightPerSQM = testCarpet1Parameters.weightPerSqm;
		testCarpet1.numberOfCarpets = testCarpet1Parameters.numCarpets;
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
	 * Test of main method, of class CarpetsAndras. Carpet 1: Size = 4m 0cm 0mm by 2m 9cm 0mm Weight per sq. m. = 1.25 Area = 8.36 sq. m. Weight = 0t 10kg 450g Weight of 200
	 * carpets = 2t 90kg 0g
	 *
	 * Carpet 2: Size = 3m 57cm 0mm by 5m 0cm 0mm Weight per sq. m. = 1.05 Area = 17.849999999999998 sq. m. Weight = 0t 18kg 743g Weight of 60 carpets = 1t 124kg 580g
	 */
	@Test
	public void testCalculateArea_Carpet1() {
		//when
		//then
		testCarpet1.calculateArea();
		assertEquals(testCarpet1ExpectedResults.expArea, testCarpet1.carpetAreaInMeters, 0.0);
	}

	@Test
	public void testCalculateSingleCarpetWeight_Carpet1() {
		testCarpet1.calculateSingleCarpetWeight();
		int[] resultWeight = new int[3];
		if (testCarpet1.singleCarpetWeight==null){
			fail("The singleCarpetWeight array is not created");
		}
		resultWeight[0] = testCarpet1.singleCarpetWeight.getTons();
		resultWeight[1] = testCarpet1.singleCarpetWeight.getKilograms();
		resultWeight[2] = testCarpet1.singleCarpetWeight.getGrams();
		assertArrayEquals(testCarpet1ExpectedResults.expSingleCarpetWeight, resultWeight);
	}

}
