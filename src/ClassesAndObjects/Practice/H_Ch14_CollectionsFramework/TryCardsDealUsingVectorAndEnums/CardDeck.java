package ClassesAndObjects.Practice.H_Ch14_CollectionsFramework.TryCardsDealUsingVectorAndEnums;

import java.util.Stack;
import java.util.Collections;

public class CardDeck {

	private Stack<Card> deck = new Stack<>(); 	//Creates a Stack<> type object
	// Create a deck of 52 cards

	public CardDeck() {
		for (SuitEnum suit : SuitEnum.values()) 	//For each suit
		{
			for (RankEnum rank : RankEnum.values()) //For each rank
			{
				deck.push(new Card(rank, suit));	//Creates a new Card object of the suit and rank
			}
		}
	}

	// Deal a hand
	public Hand dealHand(int numCards) {
		if (deck.size() < numCards) {
			System.err.println("Not enough cards left in the deck!");
			System.exit(1);
		}

		Hand hand = new Hand();
		for (int i = 0; i < numCards; ++i) {
			hand.add(deck.pop());
		}
		return hand;
	}

	// Shuffle the deck
	public void shuffle() {
		Collections.shuffle(deck);
	}

}
