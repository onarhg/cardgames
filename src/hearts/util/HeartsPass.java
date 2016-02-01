package hearts.util;

public enum HeartsPass {
	LEFT("Left", 1), RIGHT("Right", 3), ACROSS("Across", 2), HOLD("Hold", 0);
	
	private String name;
	private int clockwisePasses;
	
	HeartsPass(String name, int clockwisePasses) {
		this.name = name;
		this.clockwisePasses = clockwisePasses;
	}
	
	public String getName() {
		return name;
	}
	
	public int getClockwisePasses() {
		return clockwisePasses;
	}
	
	public HeartsPass getNextPass() {
		switch (this) {
		case LEFT: return HeartsPass.RIGHT;
		case RIGHT: return HeartsPass.ACROSS;
		case ACROSS: return HeartsPass.HOLD;
		case HOLD: return HeartsPass.LEFT;
		default: return null;
		}
	}
	
	public String toString() {
		return name;
	}
}
