package HortonExercises.Ch14.Ex2;

import java.util.LinkedList;
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
public class Random_AndrasTest {

	public Random_AndrasTest() {
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

	@Test
	public void testGenerateCardsSize() {
		Deck testDeck = Random_Andras.generateDeck();
		assertEquals(52, testDeck.cards.size());
	}
	@Test
	public void testGenerateCards() {
		Deck testDeck = Random_Andras.generateDeck();
		assertTrue(testDeck.cards.contains("TP"));
		System.out.println("TP exists");
		assertFalse(testDeck.cards.contains("PT"));
		System.out.println("PT does not exists");
	}

	@Test
	public void testRandomDealer(){
		Deck testDeck = Random_Andras.generateDeck();
		//Pick random number of cards from deck
		LinkedList<String> getRandomCards =Random_Andras.dealRandom(testDeck, ((int) (Math.random()*53))) ;
		//Verify if none of the random cards are included in deck
		boolean isInTheDeck=false;
		for (int i=0;i<getRandomCards.size();i++){
			if (testDeck.cards.contains(getRandomCards.get(i))){
				isInTheDeck=true;
				break;
			}
		}
		assertFalse(isInTheDeck);
	}

}
