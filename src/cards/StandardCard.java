package cards;

public class StandardCard extends Card implements Ranked<StandardRank>,
		Suited<StandardSuit> {
	
	protected StandardRank rank;
	protected StandardSuit suit;
	
	public StandardCard(StandardRank rank, StandardSuit suit) {
		this.rank = rank;
		this.suit = suit;
		this.name = rank + " of " + suit + "s";
	}
	
	public StandardSuit getSuit() {
		return suit;
	}

	public StandardRank getRank() {
		return rank;
	}
	
	public String getShortName() {
		return "" + rank.getSymbol() + suit.getSymbol();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof StandardCard)) {
			return false;
		}
		StandardCard standardCard = (StandardCard)other;
		return this.rank == standardCard.rank && this.suit == standardCard.suit;
	}
}
