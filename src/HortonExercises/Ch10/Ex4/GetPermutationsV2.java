/*

Build the output based on the input of the next round will be the output of the previous round

base array: A B C D

round1: 
	inputArray:
		input[] 	 0 1 2 3
		input[][][]  0 0 0 0
			value		 A B C D	
		outputArray:
			output[]	0  1  2 
			output[][]	01 01 01
			value		AB AC AD BA BC BD CA CB CD DA DB DC   

	round2:
		inputArray
			output[]	0  1  2 
			output[][]	01 01 01
			value		AB AC AD BA BC BD CA CB CD DA DB DC   
		outputArray:
			output[]	0	1				
			output[][]	012 012
			value		ABC ABD ..... DBA DBC
	round3:
		inputArray
			input[]		0	1				
			input[][]	012 012
			value		ABC ABD ..... DBA DBC
		OutputArray
			output[]	0	 1				
			output[][]	0123 0123
			value		ABCD ABDC ..... DBAC DBCA

	//Create the outest loop, that will last until the output array length equals the initial input array length
	//Create a builder method, that gets an input array and provides an output array based on input.

 */
package HortonExercises.Ch10.Ex4;

import java.util.Scanner;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class GetPermutationsV2 {
//<<editor-fold desc="Algorithm 1 backup">

	/*
		Backup
		WHILE repeat until all results found (RESULT_INDEX)
			FOR walk through all items in the input array (INPUT_ARRAY_FIRST_INDEX) input[3]={ABC...}
				FOR walk through the array on the current first index (INPUT_ARRAY_SECOND_INDEX) {ABC...}
					For each item in the current SecondIndex array check if it is included in the base array
						set boolean to false (assume there is no match) (IS_THERE_A_MATCH)
						FOR walk through all items in the base array (BASE_ARRAY_INDEX) [A][B][C][D]
							2ndArray 	BaseArray
							ABC 		A -> a match found, set boolean and go to the next BASE item -> B
							ABC			B -> a match found, set boolean and go to the next BASE item -> C
							ABC			C -> a match found, set boolean and go to the next BASE item -> D
							ABC			D -> no match found
							CDB			A -> no match found
							CAB			A -> match found
						Check if isThereAMatch is FALSE
							YES - 	Create a new temp array that merges the InputArray on Second Index 
									Add the temp array to the Result array and increment the result index	
							No - 	Continue with the next First Index array (ABD...)
	 */
//</editor-fold>
//<editor-fold desc="Pseudo code">
/*

		(0) WHILE repeat until all results found (RESULT_INDEX)
			(1) FOR walk through all items in the input array (INPUT_ARRAY_FIRST_INDEX) input[3]={ABC...}
				(2) FOR walk through all items in the base array (BASE_ARRAY_INDEX) [A][B][C][D]
					Check if the current BASE item is included in the Current First Index array
						(3) For walk through the elements of the 2nd index on the input array
							Base 		2ndArray (ABC)
							A			A -> A match found! Skip to the next BASE ARRAY (2)
							B			A -> No match so far. Let's see the next item in input array (3)
										B -> Match. Check the next base (2)
							C			A-> No match so far. Let's see the next item in input array (3)
										B-> No match so far. Let's see the next item in input array (3)
										C -> Match. Check the next base (2)
							D			A-> No match so far. Let's see the next item in input array (3)
										B-> No match so far. Let's see the next item in input array (3)
										C-> No match so far. Let's see the next item in input array (3)
										Walked through the 2nd array without a match:
											The Base Array item D is not included in the 2nd array.
											Add it and go to the next First Item (1)

							Base		2nd (CBD)
							A			C-> No match so far. Let's see the next item in input array
										B-> No match so far. Let's see the next item in input array
										D-> No match so far. Let's see the next item in input array
										Walked through the 2nd array without a match:
											The Base Array item A is not included in the 2nd array
											Add it and go to the next First Item (1)
	 */
//</editor-fold>
	public static void waitForEnter() {
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}

	//use the RemoveDuplicates method to simply find out which is the missing item
	public static String[][] buildArrayV2(String[][] inputArray, String[] baseArray) {
		int resultArrayLength = inputArray[0].length * (inputArray.length * (baseArray.length - 1));
		String[][] result = new String[resultArrayLength][inputArray[0].length + 1];
		int resultIndex = 0;
		while (resultIndex < result.length) {
			for (int inputArrayFirstIndex = 0; inputArrayFirstIndex < inputArray.length; inputArrayFirstIndex++) {
				//Merge the current string and the pool together	
				String[] mergedArray = new String[inputArray[inputArrayFirstIndex].length + baseArray.length];
				int mergedIndex = 0;
				for (; mergedIndex < inputArray[inputArrayFirstIndex].length; mergedIndex++) {
					mergedArray[mergedIndex] = inputArray[inputArrayFirstIndex][mergedIndex];
				}
				for (int i = 0; i < baseArray.length; i++) {
					mergedArray[mergedIndex++] = baseArray[i];
				}
				String[] removedDuplicates = arrayMethods.removeDuplicates(mergedArray, true, false);
				//Create the new string ABCD+ABC -> D
				int tempIndex = 0;
				for (; tempIndex < inputArray[inputArrayFirstIndex].length; tempIndex++) {
					result[resultIndex][tempIndex] = inputArray[inputArrayFirstIndex][tempIndex];
				}
				for (int i = 0; i < removedDuplicates.length; i++) {
					result[resultIndex][tempIndex+i] = removedDuplicates[i];
				}
				resultIndex++;
				//increment result index only if there is available more index, if not, break out from the WHILE loop -> outer:
//				if (resultIndex < result.length - 1) {
//					resultIndex++;
//				} else {
//					break breakFromWhile;
//				}
			}//end for
		}//end while
		System.out.println("Output array");
		for (int i = 0; i < result.length; i++) {
			System.out.printf("[%d]  ", i);
			for (int j = 0; j < result[i].length; j++) {
				System.out.printf("[%d]=%s", j, result[i][j]);
			}
			System.out.println("");
		}
		return result;
	}

	public static String[][] buildArray(String[][] inputArray, String[] baseArray) {
		boolean debug = false;
		System.out.println("Base array");
		for (int i = 0; i < baseArray.length; i++) {
			System.out.printf("[%d]]=%s%n", i, baseArray[i]);
		}
		System.out.println("Input array");
		for (int i = 0; i < inputArray.length; i++) {
			System.out.printf("[%d]  ", i);
			for (int j = 0; j < inputArray[i].length; j++) {
				System.out.printf("[%d]=%s", j, inputArray[i][j]);
			}
			System.out.println("");
		}
		int resultArrayLength = inputArray[0].length * (inputArray.length * (baseArray.length - 1));
		System.out.println("Result array length: " + resultArrayLength);
		String[][] result = new String[resultArrayLength][inputArray[0].length + 1];
		System.out.println("Result[] length: " + result.length);
		System.out.println("Result[][] length: " + result[0].length);
		int resultIndex = 0;
		//(0) - Continue until all results found
		breakFromWhile:
		while (resultIndex < result.length) { //Repeat until all results created

			//(1) - Walk through the array on the first index of InputArray
			for (int inputArrayFirstIndex = 0; inputArrayFirstIndex < inputArray.length; inputArrayFirstIndex++) {

				//(3) - Walk through the array on the second index of InputArray to compare with the current BASE item
				for (int inputArraySecondIndex = 0; inputArraySecondIndex < inputArray[inputArrayFirstIndex].length; inputArraySecondIndex++) {
					//(2) - Walk through all items in the Base array to find the one that is not included in the input array
					boolean isThereMatch = false; //if going through the input array there is no match from base...
					//ABC ABCD
					//Base: ABCD 
					//Test: ABC 
					/*
					t: A
					b: A -> its in the base, check next test
					t: B
					b: A -> not in the base, check next BASE
					t: B 
					b: B -> its in the base, check next test
					t: C 
					b: A -> test(c) not in the base
					b: B
					b: C 

					TO BE Continued: find a method to find out which letter of the base is NOT included in the input
					input: ABC
					base: ABCD
					
					 */
					boolean itsInTheInput = false;
					breakToSecond:
					for (int baseIndex = 0; baseIndex < baseArray.length; baseIndex++) {
						if (debug) {
							System.out.println("**");
							System.out.println("Result index:\t" + resultIndex);
							System.out.printf("Base item:\t[%d]=%s%n", baseIndex, baseArray[baseIndex]);
							System.out.printf("inputArray:\t[%d][%d]=%s", inputArrayFirstIndex, inputArraySecondIndex, inputArray[inputArrayFirstIndex][inputArraySecondIndex]);
							System.out.println("\nPress enter to evaulate!");
							waitForEnter();
						}
						//If the current SecondItem equals a BASE item -> there is a match! Continue with next Base item
						if (baseArray[baseIndex].equals(inputArray[inputArrayFirstIndex][inputArraySecondIndex])) {
							itsInTheInput = true;
							if (debug) {
								System.out.println("Base item EQUALS input item");
								waitForEnter();
							}
							break breakToSecond; //Continue with loop (2)
						} else {

							if (debug) {
								System.out.println("Base item DOES NOT equal input item");
								waitForEnter();
							}

							//This point is only reached if there was no match found. Add the item to the result array
//						String[] temp = new String[inputArray.length + 1];
							//clone the current input array's content
							if (debug) {
								System.out.println("I will create the new array now!");
							}

							int tempIndex = 0;
							//walk through the array on the inputArrayFirstIndex
							for (; tempIndex < inputArray[inputArrayFirstIndex].length; tempIndex++) {
//							System.out.println("InputArrayFirstIndex: " + inputArrayFirstIndex);
//							System.out.println("tempIndex" + tempIndex);
								result[resultIndex][tempIndex] = inputArray[inputArrayFirstIndex][tempIndex];
							}
							//add the extra item
//						System.out.println("tempIndex" + tempIndex);
//						System.out.println("ResultIndex: " + resultIndex);
							result[resultIndex][tempIndex] = baseArray[baseIndex];
							//increment result index only if there is available more index, if not, break out from the WHILE loop -> outer:
							if (resultIndex < result.length - 1) {
								resultIndex++;
							} else {
								break breakFromWhile;
							}
						}//end else
//end equality chekc
					}//end baseIndex walkthrough

				}//end InputArray2nd index walkthrough
			}//end InputArray1st index walkthrough
		}//end While
		System.out.println("Output array");
		for (int i = 0; i < result.length; i++) {
			System.out.printf("[%d]  ", i);
			for (int j = 0; j < result[i].length; j++) {
				System.out.printf("[%d]=%s", j, result[i][j]);
			}
			System.out.println("");
		}
		return result;
	}//end buildArray method

//	public static String[][] buildPermutations(String[] inputArray) {
//	}
	public static void main(String[] args) {
		String[] test = {"A", "B", "C", "D"};
		int testCase = 3;
		switch (testCase) {
			case 1:
				String[] a = {"A"};
				String[] b = {"B"};
				String[] c = {"C"};
				String[] d = {"D"};
				String[][] buildTest1 = new String[test.length][test.length];
				buildTest1[0] = a;
				buildTest1[1] = b;
				buildTest1[2] = c;
				buildTest1[3] = d;
				buildArray(buildTest1, test);
				break;
			case 2:
				String[] a2 = {"A", "B"};
				String[] b2 = {"B", "A"};
				String[] c2 = {"A", "C"};
				String[] d2 = {"C", "A"};
				String[][] buildTest2 = new String[test.length][test.length];
				buildTest2[0] = a2;
				buildTest2[1] = b2;
				buildTest2[2] = c2;
				buildTest2[3] = d2;
				buildArray(buildTest2, test);
				break;
			case 3:
				String[] a3 = {"A", "B"};
				String[] b3 = {"B", "A"};
				String[] c3 = {"A", "C"};
				String[] d3 = {"C", "A"};
				String[][] buildTest3 = new String[test.length][test.length];
				buildTest3[0] = a3;
				buildTest3[1] = b3;
				buildTest3[2] = c3;
				buildTest3[3] = d3;
				buildArrayV2(buildTest3, test);
				break;
		}
	}
}
