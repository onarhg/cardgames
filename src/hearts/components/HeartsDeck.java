package hearts.components;

import cards.StandardRank;
import cards.StandardSuit;
import deck.Deck;

public class HeartsDeck extends Deck<HeartsCard> {
	public static final int MAX_SIZE = 52;
	
	public HeartsDeck() {
		super();
		for (StandardSuit suit : StandardSuit.values()) {
			for (StandardRank rank : StandardRank.values()) {
				cards.add(new HeartsCard(rank, suit));
			}
		}
	}
}