package ClassesAndObjects.Practice.H_Ch14_CollectionsFramework.TryCardsDealUsingVectorAndEnums;

public class Card implements Comparable<Card> {

	private SuitEnum thisCardSuit;
	private RankEnum thisCardRank;

	//Constructor
	public Card(RankEnum rank, SuitEnum suit) {
		this.thisCardRank = rank;
		this.thisCardSuit = suit;
	}

	@Override
	public String toString() {
		return thisCardSuit + " " + thisCardRank;
	}

	// Required because of implementation of Comparable
	@Override
	public int compareTo(Card card) {
		if (thisCardSuit.equals(card.thisCardSuit)) {                       // First compare suits
			if (thisCardRank.equals(card.thisCardRank)) {                   // So check face values
				return 0;                                                   // They are equal
			}
			return thisCardRank.compareTo(card.thisCardRank) < 0 ? -1 : 1;	// Same thisCardSuit, different ranks
		} else {                                                           	// Suits are different
			return thisCardSuit.compareTo(card.thisCardSuit) < 0 ? -1 : 1;  // SuitEnum sequence is C<D<H<S as in the enum class
		}
	}

}
