package hearts.util;

import hearts.components.HeartsCard;
import cards.StandardSuit;
import cards.util.CardList;

public class HeartsCardList extends CardList<HeartsCard> {
	// We have no idea what this is, but don't touch it
	private static final long serialVersionUID = -309781332966744309L;
	
	public HeartsCardList getCardsOfSuit(StandardSuit suit) {
		HeartsCardList subList = new HeartsCardList();
		
		for (HeartsCard card: this) {
			if (card.getSuit() == suit) {
				subList.add(card);
			}
		}
		
		return subList;
	}
	
	public HeartsCard getLowestCard() {
		HeartsCard lowestCard = this.get(0);
		
		for (HeartsCard card: this) {
			if (card.compareTo(lowestCard) < 0) {
				lowestCard = card;
			}
		}
		
		return lowestCard;
	}
	
	public HeartsCard getHighestCard() {
		HeartsCard highestCard = this.get(0);
		
		for (HeartsCard card: this) {
			if (card.compareTo(highestCard) > 0) {
				highestCard = card;
			}
		}
		
		return highestCard;
	}
	
	public boolean containsSuit(StandardSuit suit) {
		for (HeartsCard card: this) {
			if (card.getSuit() == suit) {
				return true;
			}
		}
		
		return false;
	}
}
