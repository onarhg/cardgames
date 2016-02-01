package cards;

public enum StandardSuit {
	Club('C'), Diamond('D'), Heart('H'), Spade('S');

	private final char symbol;
	private final String name;
	
	StandardSuit(char symbol) {
		this.symbol = symbol;
		switch (symbol) {
		case 'C': name = "Club"; break;
		case 'D': name = "Diamond"; break;
		case 'H': name = "Heart"; break;
		case 'S': name = "Spade"; break;
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