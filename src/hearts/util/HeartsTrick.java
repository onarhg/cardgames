package hearts.util;

import hearts.components.HeartsCard;
import hearts.components.HeartsPlayer;

import java.util.ArrayList;
import java.util.List;

import cards.StandardSuit;


public class HeartsTrick {
	private List<HeartsPair> pairs;
	
	public HeartsTrick() {
		pairs = new ArrayList<HeartsPair>();
	}
	
	public void addCard(HeartsPlayer player, HeartsCard card) throws IllegalStateException {
		if (pairs.size() >= 4) {
			throw new IllegalStateException("Already 4 cards in trick");
		}
		
		for (HeartsPair pair : pairs) {
			if (player.equals(pair.getPlayer())) {
				throw new IllegalStateException("Player already played a card");
			}
		}
		
		pairs.add(new HeartsPair(player, card));
	}
	
	public List<HeartsPair> getPairs() {
		return pairs;
	}
	
	public boolean isComplete() {
		return pairs.size() >= 4;
	}
	
	public HeartsPair getWinningPair() {
		StandardSuit ledSuit = pairs.get(0).getCard().getSuit();
		HeartsPair winningPair = pairs.get(0); 
		for (HeartsPair pair : pairs) {
			if (pair.getCard().getSuit() == ledSuit && pair.getCard().compareTo(winningPair.getCard()) > 0) {
				winningPair = pair;
			}
		}
		
		return winningPair;
	}
}
