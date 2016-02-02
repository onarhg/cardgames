package hearts.config;

import java.util.HashMap;
import java.util.Map;

import cards.StandardCard;
import cards.StandardRank;
import cards.StandardSuit;

public class HeartsConfig {
	public static final int PASS_SIZE = 3;
	public static final int MAX_SCORE = 100;
	public static final int NUM_PLAYERS = 4;
	
	public static final String START_OF_GAME = ">";
	public static final String END_OF_PLAYER_LIST = "=";
	public static final String END_OF_GAME = "<";
	
	public static final Map<StandardCard, Integer> STANDARD_CARD_POINTS = new HashMap<StandardCard, Integer>();
	static {
		for (StandardSuit suit : StandardSuit.values()) {
			for (StandardRank rank : StandardRank.values()) {
				StandardCard card = new StandardCard(rank, suit);
				if (suit == StandardSuit.Heart) {
					STANDARD_CARD_POINTS.put(card, 1);
				} else if (suit == StandardSuit.Spade && rank == StandardRank.Queen) {
					STANDARD_CARD_POINTS.put(card,  13);
				} else {
					STANDARD_CARD_POINTS.put(card, 0);
				}
			}
		}
	}
}
