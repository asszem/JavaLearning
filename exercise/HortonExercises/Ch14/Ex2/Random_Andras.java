/*
Requirement:

Write a program to store a deck of 52 cards in a linked list in random sequence using a Card class
object. You can represent a card as a two-character string —”1C” for the ace of clubs, “JD” for the jack of
diamonds, and so on. Output the cards from the linked list as four hands of 13 cards.
 */
package HortonExercises.Ch14.Ex2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Random_Andras {

	public static Deck generateDeck() {
		Deck newDeck = new Deck();
		LinkedList<String> suits = new LinkedList<>();
		LinkedList<String> ranks = new LinkedList<>();
		suits.add("P"); //pikk
		suits.add("T"); //treff
		suits.add("S"); //szív - kőr
		suits.add("K"); //káró
		//Generate rank values
		for (int i = 2; i < 10; i++) {
			ranks.add(String.valueOf(i));
		}
		ranks.add("T");   	//tíz
		ranks.add("J");		//jumbo
		ranks.add("D");		//dáma
		ranks.add("K");		//király
		ranks.add("A");		//ász

		//Generate the deck
		for (String suit : suits) {
			for (String rank : ranks) {
				newDeck.cards.add(rank + suit);
			}
		}
		return newDeck;
	}

	public static void printDeck(Deck deck) {
		int counter = 1;
		for (String card : deck.cards) {
			System.out.print(card + " ");
			if (counter++ % 13 == 0) {
				System.out.println("");
			}
		}
		System.out.println("");
	}

	public static void printHand(LinkedList<String> hand) {
		Collections.sort(hand);
		for (String card : hand) {
			System.out.print(card + " ");
		}
		System.out.println("");
	}

	public static LinkedList<String> dealRandom(Deck deck, int cardsToDeal) {
		LinkedList<String> dealtCards = new LinkedList<>();
		Random dealer = new Random();
		for (int i = 0; i < cardsToDeal; i++) {
			dealtCards.add(deck.cards.remove(dealer.nextInt(deck.cards.size()))); //0(inclusive)-52(exclusive)
		}
		return dealtCards;
	}

	public static void main(String[] args) {
		//Create a Deck of all 52 valid cards - 52 Card objects stored in a Deck object
		Deck deck = generateDeck();
		System.out.println("The deck before shuffle");
		printDeck(deck);

		//Deal 13 random cards 
		LinkedList[] dealtHands = new LinkedList[4];
		for (int i = 0; i < 4; i++) {
			dealtHands[i] = dealRandom(deck, 13);
			System.out.print("Hand "+(i+1)+": ");
			printHand(dealtHands[i]);
			System.out.println("The deck after dealing 13 random cards");
			printDeck(deck);
			System.out.println("");
		}
		//Shuffle the cards in the Linked List using Collections
//		Collections.shuffle(deck.cards);
//		System.out.println("The deck after shuffle");
		//Output 4 hands of 13 cards each (52 card total)
		printDeck(deck);
	}
}

//Not inner classes! They are just classes in the same file
class Card {

	String card;
}

class Deck {

	LinkedList<String> cards;

	public Deck() {
		cards = new LinkedList<>();
	}
}
