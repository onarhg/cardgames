package hearts.util;

import hearts.components.HeartsCard;
import hearts.config.HeartsConfig;


public final class HeartsTrick {
	private HeartsCard[] cards;
	private int leadIndex;
	private int trickNumber;
	private int numPlaysMade;
	
	public HeartsTrick(int leadIndex, int trickNumber) {
		cards = new HeartsCard[HeartsConfig.NUM_PLAYERS];
		this.leadIndex = leadIndex;
		this.numPlaysMade = 0;
	}
	
	public void addCard(HeartsCard card) {
		if (numPlaysMade >= HeartsConfig.NUM_PLAYERS) {
			throw new IllegalStateException("Already " + HeartsConfig.NUM_PLAYERS + " cards in trick");
		}
		
		cards[(leadIndex + numPlaysMade) % HeartsConfig.NUM_PLAYERS] = card;
		numPlaysMade++;
	}
	
	public HeartsCard getLedCard() {
		return cards[leadIndex];
	}
	
	public int getLeadIndex() {
		return leadIndex;
	}
	
	public HeartsCard[] getCards() {
		return cards;
	}
	
	public boolean isOver() {
		return numPlaysMade == HeartsConfig.NUM_PLAYERS;
	}
	
	public int getWinningPlayerIndex() {
		int winningPlayerIndex = leadIndex;
		for (int i = 1; i < numPlaysMade; i++) {
			int index = (leadIndex + 1) % HeartsConfig.NUM_PLAYERS;
			if (cards[index].getSuit().equals(cards[winningPlayerIndex].getSuit()) &&
					cards[index].compareTo(cards[winningPlayerIndex]) > 0) {
				winningPlayerIndex = index; 
			}
		}
		return winningPlayerIndex;
	}
	
	public int getPoints() {
		int points = 0;
		for (HeartsCard card : cards) {
			points += card.getPointValue();
		}
		return points;
	}
	
	/*
	 * Returns a copy of the HeartsTrick that can be modified any amount without affecting this (the original)
	 */
	public HeartsTrick getDeepCopy() {
		HeartsTrick deepCopy = new HeartsTrick(leadIndex, trickNumber);
		for (int i = 0; i < numPlaysMade; i++) {
			int cardIndex = (leadIndex + i) % HeartsConfig.NUM_PLAYERS; 
			deepCopy.addCard(cards[cardIndex].getDeepCopy());
		}
		return deepCopy;
	}
}
