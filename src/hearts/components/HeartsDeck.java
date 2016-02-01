package hearts.components;

import java.util.Collections;

import cards.StandardSuit;
import cards.StandardRank;
import deck.Deck;

public class HeartsDeck extends Deck<HeartsCard> {
	public HeartsDeck() {
		super();
		for (StandardSuit suit : StandardSuit.values()) {
			for (StandardRank rank : StandardRank.values()) {
				cards.add(new HeartsCard(rank, suit));
			}
		}
		Collections.shuffle(cards);
	}
}