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

	//Create static objects to hold test parameters and expected results
	static CarpetsAndrasTest testCarpet1Parameters; 
	static CarpetsAndrasTest testCarpet1ExpectedResults;
	static CarpetsAndrasTest testCarpet2Parameters; 
	static CarpetsAndrasTest testCarpet2ExpectedResults;
	int[][] carpetSize;
	double weightPerSqm;
	int numCarpets;
	int expSingleCarpetWeight[];
	int expTotalWeight[];
	double expArea;
	static final int METER_INDEX = 0;
	static final int CENTIMETER_INDEX = 1;
	static final int MILLIMETER_INDEX = 2;
	static final int TON_INDEX = 0;
	static final int KG_INDEX = 1;
	static final int GRAM_INDEX = 2;
	static final int CARPET_HEIGHT_INDEX = 0;
	static final int CARPET_WIDTH_INDEX = 1;
	
//Create test carpets that will have the actual results
	static CarpetsAndras testCarpet1; 
	static CarpetsAndras testCarpet2; 

	public CarpetsAndrasTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		//<editor-fold desc="Expected results">
		/*
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
		//</editor-fold>
		//Initializing object holding input parameters 
		//<editor-fold> desc="Initializing test carpet 1">
		testCarpet1Parameters = new CarpetsAndrasTest(); // to hold expected result parameters
		int[][] inputSizeValues = {{4, 0, 0}, {2, 9, 0}};
		testCarpet1Parameters.carpetSize = inputSizeValues;
		testCarpet1Parameters.weightPerSqm = 1.25;
		testCarpet1Parameters.numCarpets = 200;

		//Initializing object holding expected results
		testCarpet1ExpectedResults = new CarpetsAndrasTest();
		int[] expSingleWeightValues = {0, 10, 450};
		testCarpet1ExpectedResults.expSingleCarpetWeight = expSingleWeightValues;
		int[] expTotalWeightValues = {2, 90, 0};
		testCarpet1ExpectedResults.expTotalWeight = expTotalWeightValues;
		testCarpet1ExpectedResults.expArea = 8.36;

		//Initializing testCarpet1 object
		testCarpet1 = new CarpetsAndras(); //create new Carpet object
		testCarpet1.carpetHeight = new McmLengthAndras(testCarpet1Parameters.carpetSize[CARPET_HEIGHT_INDEX][METER_INDEX], testCarpet1Parameters.carpetSize[CARPET_HEIGHT_INDEX][CENTIMETER_INDEX], testCarpet1Parameters.carpetSize[CARPET_HEIGHT_INDEX][MILLIMETER_INDEX]);
		testCarpet1.carpetWidth = new McmLengthAndras(testCarpet1Parameters.carpetSize[CARPET_WIDTH_INDEX][METER_INDEX], testCarpet1Parameters.carpetSize[CARPET_WIDTH_INDEX][CENTIMETER_INDEX], testCarpet1Parameters.carpetSize[CARPET_WIDTH_INDEX][MILLIMETER_INDEX]);
		testCarpet1.carpetWeightKgPerSQM = testCarpet1Parameters.weightPerSqm;
		testCarpet1.numberOfCarpets = testCarpet1Parameters.numCarpets;
		//</editor-fold>

		//<editor-fold> desc="Initializing test carpet 2">
		testCarpet2Parameters = new CarpetsAndrasTest(); // to hold expected result parameters
		int[][] inputSizeValues2 = {{3, 57, 0}, {5, 0, 0}};
		testCarpet2Parameters.carpetSize = inputSizeValues2;
		testCarpet2Parameters.weightPerSqm = 1.05;
		testCarpet2Parameters.numCarpets = 60;

		//Initializing object holding expected results
		testCarpet2ExpectedResults = new CarpetsAndrasTest();
		int[] expSingleWeightValues2 = {0, 18, 743};
		testCarpet2ExpectedResults.expSingleCarpetWeight = expSingleWeightValues2;
		int[] expTotalWeightValues2 = {1, 124, 580};
		testCarpet2ExpectedResults.expTotalWeight = expTotalWeightValues2;
		testCarpet2ExpectedResults.expArea = 17.849999999999998;

		//Initializing testCarpet2 object
		testCarpet2 = new CarpetsAndras(); //create new Carpet object
		testCarpet2.carpetHeight = new McmLengthAndras(testCarpet2Parameters.carpetSize[CARPET_HEIGHT_INDEX][METER_INDEX], testCarpet2Parameters.carpetSize[CARPET_HEIGHT_INDEX][CENTIMETER_INDEX], testCarpet2Parameters.carpetSize[CARPET_HEIGHT_INDEX][MILLIMETER_INDEX]);
		testCarpet2.carpetWidth = new McmLengthAndras(testCarpet2Parameters.carpetSize[CARPET_WIDTH_INDEX][METER_INDEX], testCarpet2Parameters.carpetSize[CARPET_WIDTH_INDEX][CENTIMETER_INDEX], testCarpet2Parameters.carpetSize[CARPET_WIDTH_INDEX][MILLIMETER_INDEX]);
		testCarpet2.carpetWeightKgPerSQM = testCarpet2Parameters.weightPerSqm;
		testCarpet2.numberOfCarpets = testCarpet2Parameters.numCarpets;
		//</editor-fold>
	}

	@AfterClass
	public static void tearDownClass() {
		//<editor-fold desc="Expected results">
		/*
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
		//</editor-fold>
		System.out.println("Test Carpet 1:");
		System.out.println(testCarpet1.totalWeight);
		System.out.println("Test Carpet 2:");
		System.out.println(testCarpet2.totalWeight);
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCalculateArea_Carpet1() {
		testCarpet1.calculateArea();
		assertEquals(testCarpet1ExpectedResults.expArea, testCarpet1.carpetAreaInMeters, 0.0);
	}
	@Test
	public void testCalculateArea_Carpet2() {
		testCarpet2.calculateArea();
		assertEquals(testCarpet2ExpectedResults.expArea, testCarpet2.carpetAreaInMeters, 0.0);
	}

	@Test
	public void testCalculateSingleCarpetWeight_Carpet1() {
		testCarpet1.calculateSingleCarpetWeight();
		int[] resultWeight = new int[3];
		if (testCarpet1.singleCarpetWeight == null) {
			fail("The singleCarpetWeight object is not created");
		}
		resultWeight[TON_INDEX] = testCarpet1.singleCarpetWeight.getTons();
		resultWeight[KG_INDEX] = testCarpet1.singleCarpetWeight.getKilograms();
		resultWeight[GRAM_INDEX] = testCarpet1.singleCarpetWeight.getGrams();
		assertArrayEquals(testCarpet1ExpectedResults.expSingleCarpetWeight, resultWeight);
	}
	@Test
	public void testCalculateSingleCarpetWeight_Carpet2() {
		testCarpet2.calculateSingleCarpetWeight();
		int[] resultWeight = new int[3];
		if (testCarpet2.singleCarpetWeight == null) {
			fail("The singleCarpetWeight object is not created");
		}
		resultWeight[TON_INDEX] = testCarpet2.singleCarpetWeight.getTons();
		resultWeight[KG_INDEX] = testCarpet2.singleCarpetWeight.getKilograms();
		resultWeight[GRAM_INDEX] = testCarpet2.singleCarpetWeight.getGrams();
		assertArrayEquals(testCarpet2ExpectedResults.expSingleCarpetWeight, resultWeight);
	}

	@Test
	public void testCalculateTotalWeight_Carpet1() {
		testCarpet1.calculateTotalWeight();
		int[] resultTotalWeight = new int[3];
		if (testCarpet1.totalWeight == null) {
			fail("The totalWeight object has not yet been created");
		}
		resultTotalWeight[TON_INDEX] = testCarpet1.totalWeight.getTons();
		resultTotalWeight[KG_INDEX] = testCarpet1.totalWeight.getKilograms();
		resultTotalWeight[GRAM_INDEX] = testCarpet1.totalWeight.getGrams();
		assertArrayEquals(testCarpet1ExpectedResults.expTotalWeight, resultTotalWeight);
	}
	@Test
	public void testCalculateTotalWeight_Carpet2() {
		testCarpet2.calculateTotalWeight();
		int[] resultTotalWeight = new int[3];
		if (testCarpet2.totalWeight == null) {
			fail("The totalWeight object has not yet been created");
		}
		resultTotalWeight[TON_INDEX] = testCarpet2.totalWeight.getTons();
		resultTotalWeight[KG_INDEX] = testCarpet2.totalWeight.getKilograms();
		resultTotalWeight[GRAM_INDEX] = testCarpet2.totalWeight.getGrams();
		assertArrayEquals(testCarpet2ExpectedResults.expTotalWeight, resultTotalWeight);
	}
}
