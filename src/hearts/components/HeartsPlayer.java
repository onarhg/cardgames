package hearts.components;

import hearts.util.HeartsCardList;
import hearts.util.HeartsPass;
import hearts.util.HeartsTrick;
import player.Player;


public abstract class HeartsPlayer extends Player {
	private HeartsCardList hand;
	
	public HeartsPlayer(int playerNumber) {
		this.hand = new HeartsCardList();
	}
	
	public void initNewHand(HeartsCardList hand) {
		this.hand = hand;
	}
	
	public abstract HeartsCardList pass(HeartsPass passDirection);
	
	public void receivePass(HeartsCardList pass) {
		this.hand.addAll(pass);
	}
	
	public abstract HeartsCard playCard(HeartsTrick currentTrick);
	
	public abstract void getFinishedTrick(HeartsTrick finishedTrick);
	
	public abstract void finishHand(int[] scores);
}
