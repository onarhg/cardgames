package hearts.util;

import java.util.ArrayList;
import java.util.List;

import hearts.components.HeartsPlayer;
import player.PlayerState;

final public class HeartsPlayerState extends PlayerState {
	private final HeartsPlayer player;
	private int score;
	private HeartsCardList hand;
	private List<HeartsTrick> wonTricks;
	
	public HeartsPlayerState(HeartsPlayer player) {
		this.player = player;
		score = 0;
		hand = new HeartsCardList();
		wonTricks = new ArrayList<HeartsTrick>();
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
	
	public void addTrick(HeartsTrick trick) {
		wonTricks.add(trick);
	}
	
	public List<HeartsTrick> getTricks() {
		return wonTricks;
	}
	
	public void clearTricks() {
		wonTricks.clear();
	}
}
