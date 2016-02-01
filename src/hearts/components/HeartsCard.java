package hearts.components;

import hearts.config.HeartsConfig;
import cards.StandardCard;
import cards.StandardRank;
import cards.StandardSuit;

public class HeartsCard extends StandardCard implements Comparable<HeartsCard> {
	private final int pointValue;

	public HeartsCard(StandardRank rank, StandardSuit suit) {
		super(rank, suit);
		pointValue = HeartsConfig.STANDARD_CARD_POINTS.get(new StandardCard(rank, suit));
	}
	
	private int getRankValue() {
		switch (this.rank) {
		case Two: return 2;
		case Three: return 3;
		case Four: return 4;
		case Five: return 5;
		case Six: return 6;
		case Seven: return 7;
		case Eight: return 8;
		case Nine: return 9;
		case Ten: return 10;
		case Jack: return 11;
		case Queen: return 12;
		case King: return 13;
		case Ace: return 14;
		default: return 0;
		}
	}
	
	public int getPointValue() {
		return pointValue;
	}

	@Override
	public int compareTo(HeartsCard other) {
		return this.getRankValue() - other.getRankValue();
	}
}