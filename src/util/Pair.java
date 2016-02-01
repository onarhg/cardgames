package util;


public abstract class Pair<X, Y> {
	protected X x;
	protected Y y;
	
	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof Pair<?, ?>)) {
			return false;
		}
		Pair<?, ?> otherPair = (Pair<?, ?>)other;
		return this.x.equals(otherPair.x) && this.y.equals(otherPair.y);
	}
}
