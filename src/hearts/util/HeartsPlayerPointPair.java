package hearts.util;

import hearts.components.HeartsPlayer;
import util.Pair;

public class HeartsPlayerPointPair extends Pair<HeartsPlayer, Integer> {

	public HeartsPlayerPointPair(HeartsPlayer x, Integer y) {
		super(x, y);
	}
	
	public HeartsPlayer getPlayer() {
		return this.x;
	}
	
	public Integer getPoints() {
		return this.y;
	}
	
	public void setPoints(Integer points) {
		y = points;
	}
	
	public void addPoints(Integer points) {
		y += points;
	}
}
