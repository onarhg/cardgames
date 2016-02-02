package hearts.components;

import hearts.util.HeartsCardList;
import player.PlayerState;

public class HeartsPlayerState extends PlayerState {
	private final HeartsPlayer player;
	private int score;
	private HeartsCardList hand;
	
	public HeartsPlayerState(HeartsPlayer player) {
		this.player = player;
		score = 0;
		hand = null;
	}
	
	public HeartsPlayer getPlayer() {
		return player;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public HeartsCardList getHand() {
		return hand;
	}
	
	public void setHand(HeartsCardList hand) {
		this.hand = hand;
	}
}
