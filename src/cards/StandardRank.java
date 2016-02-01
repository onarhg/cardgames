package cards;

public enum StandardRank {
	Ace('A'), Two('2'), Three('3'), Four('4'), Five('5'), Six('6'), Seven('7'),
	Eight('8'), Nine('9'), Ten('T'), Jack('J'), Queen('Q'), King('K');
	
	private final char symbol;
	private final String name;
	
	StandardRank(char symbol) {
		this.symbol = symbol;
		switch (symbol) {
		case 'A': name = "Ace"; break;
		case '2': name = "Two"; break;
		case '3': name = "Three"; break;
		case '4': name = "Four"; break;
		case '5': name = "Five"; break;
		case '6': name = "Six"; break;
		case '7': name = "Seven"; break;
		case '8': name = "Eight"; break;
		case '9': name = "Nine"; break;
		case 'T': name = "Ten"; break;
		case 'J': name = "Jack"; break;
		case 'Q': name = "Queen"; break;
		case 'K': name = "King"; break;
		default: name = "Undefined";
		}
	}

	public char getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}