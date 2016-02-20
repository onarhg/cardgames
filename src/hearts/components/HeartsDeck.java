package hearts.components;

import cards.StandardRank;
import cards.StandardSuit;
import cards.util.CardList;
import deck.Deck;
import hearts.util.HeartsCardList;

final public class HeartsDeck extends Deck<HeartsCard> {
	public static final int MAX_SIZE = 52;
	
	public HeartsDeck() {
		this.cards = new HeartsCardList();
		for (StandardSuit suit : StandardSuit.values()) {
			for (StandardRank rank : StandardRank.values()) {
				this.cards.add(new HeartsCard(rank, suit));
			}
		}
	}
	
	@Override
	public HeartsCardList drawCards(int numCards) {
		if (numCards > cards.size()) {
			throw new IllegalStateException("The deck does not have the "
					+ "requisite number of cards");
		}
		
		HeartsCardList drawnCards = new HeartsCardList();
		for (int i = 0; i < numCards; i++) {
			drawnCards.add(this.drawCard());
		}
		return drawnCards;
	}
}