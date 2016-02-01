package hearts.components;

import hearts.util.HeartsCardList;
import player.Player;


public class HeartsPlayer extends Player {
	private HeartsCardList hand;
	
	public HeartsPlayer() {
		hand = new HeartsCardList();
	}
	
	public void receiveCard(HeartsCard card) {
		this.hand.add(card);
	}
}
