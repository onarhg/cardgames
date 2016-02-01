package deck;

import java.util.Collections;

import cards.Card;
import cards.util.CardList;

public abstract class Deck<C extends Card> {
	protected CardList<C> cards;
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	public C drawCard() {
		if (isEmpty()) {
			return null;
		} else {
			return cards.remove(cards.size() - 1);
		}
	}
	
	public void addCard(C card) {
		this.cards.add(card);
	}
	
	public void addCards(CardList<C> cards) {
		this.cards.addAll(cards);
	}
}