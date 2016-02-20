package hearts.components;

import java.util.Random;

import hearts.config.HeartsConfig;
import hearts.util.HeartsCardList;
import hearts.util.HeartsPass;
import hearts.util.HeartsTrick;
import player.Player;

/*
 * Methods in this class are guaranteed to be called in the listed order by the HeartsHandManager and HeartsGameManager
 * There will be repeated calls as necessary (e.g. playCard, acceptedCard and getFinishedTrick repeated 13 times per
 * hand). The methods acceptPass, receivePass, and acceptPlay are final methods that update the state of the hand as
 * perceived by the HeartsHandManager, other modifications to hand may result in a state that is not consistent with
 * the HeartsHandManager.
 */
public class HeartsPlayer extends Player {
	protected HeartsCardList hand;
	protected int playerNumber;
	
	public HeartsPlayer() {
		this.hand = null;
		this.playerNumber = -1;
	}
	
	public void setPlayerNumber(int num) {
		this.playerNumber = num;
	}
	
	public void initNewHand(HeartsCardList hand) {
		this.hand = hand;
	}
	
	public HeartsCardList pass(HeartsPass passDirection) {
		HeartsCardList pass = new HeartsCardList();
		for (int i = 0; i < HeartsConfig.PASS_SIZE; i++) {
			pass.add(hand.get(new Random().nextInt(hand.size())));
		}
		return pass;
	}
	
	public void acceptPass(HeartsCardList pass) {
		this.hand.removeAll(pass);
	}
	
	public void receivePass(HeartsCardList pass) {
		this.hand.addAll(pass);
	}
	
	public HeartsCard playCard(HeartsTrick currentTrick) {
		return hand.get(new Random().nextInt(hand.size()));
	}
	
	public void acceptPlay(HeartsCard card) {
		this.hand.remove(card);
	}
	
	public void getFinishedTrick(HeartsTrick finishedTrick) {
		
	}
	
	public void finishHand(int[] scores) {
		this.hand = null;
	}
	
	public void finishGame() {
		
	}
}
