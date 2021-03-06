package ClassesAndObjects.Practice.H_Ch14_CollectionsFramework.TryCardsDealUsingVectorAndEnums;

// Class defining a hand of cards
import java.util.Vector;
import java.util.Collections;

public class Hand {

	private Vector<Card> hand = new Vector<>();      // Stores a hand of cards
	// Add a card to the hand

	public void add(Card card) {
		hand.add(card);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		boolean first = true;
		for (Card card : hand) {
			if (first) {
				first = false;
			} else {
				str.append(", ");
			}
			str.append(card);
		}
		return str.toString();
	}

	// Sort the hand
	public Hand sort() {
		Collections.sort(hand);
		return this;
	}

}
