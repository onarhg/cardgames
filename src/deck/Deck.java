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
	
	public CardList<C> drawCards(int numCards) {
		if (numCards > cards.size()) {
			throw new IllegalStateException("The deck does not have the "
					+ "requisite number of cards");
		}
		
		CardList<C> drawnCards = (CardList<C>) cards.subList(0, numCards);
		cards.removeAll(drawnCards);
		return drawnCards;
	}
	
	public void addCard(C card) {
		this.cards.add(card);
	}
	
	public void addCards(CardList<C> cards) {
		this.cards.addAll(cards);
	}
}