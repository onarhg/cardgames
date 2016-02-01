package hearts.components;

import hearts.util.HeartsCardList;
import hearts.util.HeartsTrick;
import player.Player;


public abstract class HeartsPlayer extends Player {
	private HeartsCardList hand;
	
	public HeartsPlayer() {
		hand = new HeartsCardList();
	}
	
	public void initNewHand(HeartsCardList hand) {
		this.hand = hand;
	}
	
	public abstract HeartsCardList pass();
	
	public void receivePass(HeartsCardList pass) {
		this.hand.addAll(pass);
	}
	
	public abstract HeartsCard playCard(HeartsTrick trick);
	
	public abstract void updateState(HeartsTrick trick);
	
	
}
