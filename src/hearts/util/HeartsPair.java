package hearts.util;

import hearts.components.HeartsCard;
import hearts.components.HeartsPlayer;
import util.Pair;

public class HeartsPair extends Pair<HeartsPlayer, HeartsCard> {

	public HeartsPair(HeartsPlayer x, HeartsCard y) {
		super(x, y);
	}
	
	public HeartsPlayer getPlayer() {
		return x;
	}
	
	public HeartsCard getCard() {
		return y;
	}
	
}
