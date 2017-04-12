package FilesAndDirectories.Practice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
public class ReadingRandomPositionTest {

	static Path inputFile = Paths.get("E:\\javaFileOpTest\\ReadingFiles\\replacePrimesTest.bin");
	static String toAppendToFilename = "_rplcLongJunit";
	static Path resultFile = AppendStringToFileName.appendStringToFileName(inputFile, toAppendToFilename);
	static int thisManyLongsToReplace = 10;
	static long inputFileSize;
	static long resultFileSize;

	public ReadingRandomPositionTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		ReadingRandomPosition.replaceLongAtRandomPosition(inputFile, toAppendToFilename, thisManyLongsToReplace);
		try (FileChannel inputFileChannel = (FileChannel) Files.newByteChannel(inputFile);
				FileChannel resultFileChannel = (FileChannel) Files.newByteChannel(resultFile);) {
			inputFileSize = inputFileChannel.size();
			resultFileSize = resultFileChannel.size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("@Before Class");
		System.out.println("Input file:\n" + inputFile);
		System.out.println("Result file:\n" + resultFile);
		System.out.println("Number of longs to replace: " + thisManyLongsToReplace);
		System.out.println("ReplaceLongAtRandomPosition method run successfully.");
		System.out.println("Input file size:" + inputFileSize);
		System.out.println("Result file size: " + resultFileSize);
		System.out.println("Number of Longs in result file: " + resultFileSize / 8);
		System.out.println("***@BeforeClass method completed\n");
	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("@AfterClass");
		try {
			Files.deleteIfExists(resultFile);
			System.out.println("Result file deleted.");
			System.out.println("***@After class method completed");
		} catch (IOException e) {
//			e.printStackTrace();
		}

	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test throwing IllegalArgumentException when input file does not exists
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testReadLongFromRandom_WhenFileDoesntExist() {
		Path badInputFile = Paths.get("E:\\alma.txt");
		ReadingRandomPosition.readLongFromRandomPosition(badInputFile);
	}

	/**
	 * Test whether the correct result file is created
	 *
	 * Test of replaceLongAtRandomPosition method, of class ReadingRandomPosition. The correct result file is created
	 */
	@Test
	public void testReplacelongAtRandomPosition_TestIfCorrectResultFileCreated() {
		boolean isNewFileCreated = Files.exists(resultFile);
		assertTrue(isNewFileCreated);
	}

	/**
	 * Test whether the result file length in byte equals the input file's length
	 */
	@Test
	public void testReplacelongAtRandomPosition_ResultFileSizeSameAsInputFileSize() {
		assertEquals(inputFileSize, resultFileSize);
	}

	@Test
	public void testReplaceLongAtRandomPosition_TotalLongSizeIsCorrect() {
		int resultLongCount = ReadingRandomPosition.totalNumberOfLongsInTheFile;
		int expectedLongCount = (int) resultFileSize / 8;
		assertEquals(expectedLongCount, resultLongCount);
		assertEquals(resultFileSize % 8, 0); //Validate if the result file has only n*8 amount of bytes in it
	}

	@Test
	public void testREplacelongAtRandomPosition_CorrectNumberWereReplaced() {
		int actualDifferences = ReadingRandomPosition.compareResults(inputFile, resultFile);
		int expectedDifferences = thisManyLongsToReplace;
		assertEquals(expectedDifferences, actualDifferences);
	}

}
