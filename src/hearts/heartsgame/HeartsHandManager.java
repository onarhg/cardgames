package hearts.heartsgame;

import java.io.PrintStream;

import cards.StandardRank;
import cards.StandardSuit;
import hearts.components.HeartsCard;
import hearts.components.HeartsDeck;
import hearts.components.HeartsPlayerState;
import hearts.config.HeartsConfig;
import hearts.util.HeartsCardList;
import hearts.util.HeartsPass;

public class HeartsHandManager {
	private PrintStream stream;
	private HeartsPlayerState[] states;
	private HeartsPass passDirection;
	
	public HeartsHandManager(HeartsPlayerState[] players, PrintStream stream, HeartsPass passDirection) {
		this.states = players;
		this.stream = stream;
		this.passDirection = passDirection;
	}
	
	public void playHand() {
		int leadPlayerIndex = deal();
		pass();
		while (states[0].getHand().size() != 0) {
			playTrick(leadPlayerIndex);
		}
		finishHand();
	}
	
	private int deal() {
		HeartsDeck deck = new HeartsDeck();
		deck.shuffle();
		int leadPlayerIndex = 0;
		for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			HeartsPlayerState state = states[i];
			HeartsCardList hand = (HeartsCardList) deck.drawCards(HeartsDeck.MAX_SIZE / HeartsConfig.NUM_PLAYERS);
			state.setHand(hand);
			state.getPlayer().initNewHand(hand);
			if (hand.contains(new HeartsCard(StandardRank.Two, StandardSuit.Club))) {
				leadPlayerIndex = i;
			}
		}
		//TODO: initial pot if players don't divide cards evenly
		return leadPlayerIndex;
	}
	
	private void pass() {
		distributePasses(getPasses());
	}
	
	private HeartsCardList[] getPasses() {
		HeartsCardList[] passes = new HeartsCardList[HeartsConfig.NUM_PLAYERS];
		
		for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			HeartsCardList pass = states[i].getPlayer().pass(passDirection);
			while (!passIsValid(i, pass)) {
				pass = states[i].getPlayer().pass(passDirection);
			}
			states[i].getHand().removeAll(pass);
			passes[i] = pass;
		}
		return passes;
	}
	
	private boolean passIsValid(int playerNumber, HeartsCardList pass) {
		if (pass.size() != HeartsConfig.PASS_SIZE || !states[playerNumber].getHand().containsAll(pass)) {
			return false;
		}
		for (int i = 0; i < HeartsConfig.PASS_SIZE; i++) {
			for (int j = i; j < HeartsConfig.PASS_SIZE; j++) {
				if (pass.get(i).equals(pass.get(j))) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void distributePasses(HeartsCardList[] passes) {
		for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			// Update our state
			states[(i + passDirection.getClockwisePasses()) % HeartsConfig.NUM_PLAYERS].getHand().addAll(passes[i]);
			if (passes[i].contains()) {//TODO:
				
			}
			
			// Give player the pass
			states[(i + passDirection.getClockwisePasses()) % HeartsConfig.NUM_PLAYERS].getPlayer()
					.receivePass(passes[i]);
		}
	}
	
	private void playTrick(int leadPlayerIndex) {
		//TODO:
	}
	
	private void finishHand() {
		//TODO:
	}
}
