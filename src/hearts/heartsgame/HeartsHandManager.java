package hearts.heartsgame;

import java.io.PrintStream;

import cards.StandardRank;
import cards.StandardSuit;
import hearts.components.HeartsCard;
import hearts.components.HeartsDeck;
import hearts.config.HeartsConfig;
import hearts.util.HeartsCardList;
import hearts.util.HeartsPass;
import hearts.util.HeartsPlayerState;
import hearts.util.HeartsTrick;

public class HeartsHandManager {
	private PrintStream stream;
	private HeartsPlayerState[] states;
	private HeartsPass passDirection;
	private boolean heartsBroken;
	
	public HeartsHandManager(HeartsPlayerState[] players, PrintStream stream, HeartsPass passDirection) {
		this.states = players;
		this.stream = stream;
		this.passDirection = passDirection;
		this.heartsBroken = false;
	}
	
	public void playHand() {
		int leadPlayerIndex = deal();
		int twoOfClubsIndex = pass();
		leadPlayerIndex = (twoOfClubsIndex == -1 ? leadPlayerIndex : twoOfClubsIndex);
		for (int i = 0; i < HeartsDeck.MAX_SIZE / HeartsConfig.NUM_PLAYERS; i++) {
			playTrick(leadPlayerIndex, i + 1);
		}
		finishHand();
	}
	
	private int deal() {
		HeartsDeck deck = new HeartsDeck();
		deck.shuffle();
		int twoOfClubsIndex = 0;
		for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			HeartsPlayerState state = states[i];
			HeartsCardList hand = (HeartsCardList) deck.drawCards(HeartsDeck.MAX_SIZE / HeartsConfig.NUM_PLAYERS);
			state.setHand(hand);
			state.getPlayer().initNewHand(hand.getDeepCopy());
			if (hand.contains(new HeartsCard(StandardRank.Two, StandardSuit.Club))) {
				twoOfClubsIndex = i;
			}
		}
		//TODO: initial pot if players don't divide cards evenly
		return twoOfClubsIndex;
	}
	
	private int pass() {
		return distributePasses(getPasses());
	}
	
	private HeartsCardList[] getPasses() {
		HeartsCardList[] passes = new HeartsCardList[HeartsConfig.NUM_PLAYERS];
		
		for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			HeartsCardList pass = states[i].getPlayer().pass(passDirection);
			while (!passIsValid(i, pass)) {
				pass = states[i].getPlayer().pass(passDirection);
			}
			
			// Notify the player that the specified cards have been passed from their hand 
			states[i].getPlayer().acceptPass(pass.getDeepCopy());
			// Update the hand of the player held by the manager
			states[i].getHand().removeAll(pass);
			passes[i] = pass;
		}
		return passes;
	}
	
	private boolean passIsValid(int playerNumber, HeartsCardList pass) {
		// Check to see that pass is the proper size and only contains cards from the players hand
		if (pass.size() != HeartsConfig.PASS_SIZE || !states[playerNumber].getHand().containsAll(pass)) {
			return false;
		}
		
		// Check to see that the player doesn't try to pass the same card multiple times
		for (int i = 0; i < HeartsConfig.PASS_SIZE; i++) {
			for (int j = i + 1; j < HeartsConfig.PASS_SIZE; j++) {
				if (pass.get(i).equals(pass.get(j))) {
					return false;
				}
			}
		}
		return true;
	}
	
	private int distributePasses(HeartsCardList[] passes) {
		int twoOfClubsIndex = -1;
		for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			int passReceiver = (i + passDirection.getClockwisePasses()) % HeartsConfig.NUM_PLAYERS;
			
			// If the pass contains the Two of Clubs, update where the manager tracks it to
			if (passes[i].contains(new HeartsCard(StandardRank.Two, StandardSuit.Club))) {
				twoOfClubsIndex = passReceiver;
			}
			
			// Notify the player of the pass they are receiving
			states[passReceiver].getPlayer().receivePass(passes[i].getDeepCopy());
			// Update the hand of the player held by the manager
			states[passReceiver].getHand().addAll(passes[i]);
		}
		return twoOfClubsIndex;
	}
	
	private void playTrick(int leadPlayerIndex, int trickNumber) {
		HeartsTrick trick = new HeartsTrick(leadPlayerIndex, trickNumber);
		for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			int currentPlayerIndex = (leadPlayerIndex + i) % HeartsConfig.NUM_PLAYERS;
			HeartsCard playedCard = states[currentPlayerIndex].getPlayer().playCard(trick.getDeepCopy());
			while (!playIsValid(currentPlayerIndex, playedCard, trick.getLedCard(), trickNumber)) {
				playedCard = states[currentPlayerIndex].getPlayer().playCard(trick.getDeepCopy());
			}
			
			// Add card to the trick
			trick.addCard(playedCard);
			// Notify the player of the card that they've played
			states[currentPlayerIndex].getPlayer().acceptPlay(playedCard.getDeepCopy());
			// Update the hand of the player held by the manager
			states[currentPlayerIndex].getHand().remove(playedCard);
		}
		
		// Show each player the result of the trick
		for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			states[(leadPlayerIndex + i) % HeartsConfig.NUM_PLAYERS].getPlayer().getFinishedTrick(trick.getDeepCopy());
		}
		// Give the player who won the trick the trick
		states[trick.getWinningPlayerIndex()].addTrick(trick.getDeepCopy());
		//TODO: print stuff?
	}
	
	private boolean playIsValid(int playerIndex, HeartsCard playedCard, HeartsCard ledCard, int trickNumber) {
		// Must play a card from hand
		if (!states[playerIndex].getHand().contains(playedCard)) {
			return false;
		}
		// Must follow the led suit
		if (ledCard != null &&
				states[playerIndex].getHand().containsSuit(ledCard.getSuit()) &&
				playedCard.getSuit() != ledCard.getSuit()) {
			return false;
		}
		// May not lead hearts unless broken or only suit in hand
		if (ledCard == null && playedCard.getSuit() == StandardSuit.Heart) {
			return states[playerIndex].getHand().getCardsOfSuit(StandardSuit.Heart).size() ==
					states[playerIndex].getHand().size() || heartsBroken;
		}
		// Must play the two of clubs on lead of first trick
		if (trickNumber == 1 && ledCard == null) {
			return playedCard.equals(new HeartsCard(StandardRank.Two, StandardSuit.Club));
		}
		// May not play points on first trick
		if (trickNumber == 1 && playedCard.getPointValue() > 0) {
			for (HeartsCard card : states[playerIndex].getHand()) {
				if (card.getPointValue() <= 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void finishHand() {
		giveScores();
		cleanStates();
		//TODO: Other stuff?
		//TODO: print stuff?
	}
	
	private void giveScores() {
		int shoot = -1;
		for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			int points = 0;
			for (HeartsTrick trick : states[i].getTricks()) {
				points += trick.getPoints();
			}
			states[i].setScore(states[i].getScore() + points);
			if (points == HeartsConfig.MAX_HAND_POINTS) {
				shoot = i;
			}
		}
		
		if (shoot >= 0) {
			for (int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
				if (i == shoot) {
					states[i].setScore(states[i].getScore() - HeartsConfig.MAX_HAND_POINTS);
				} else {
					states[i].setScore(states[i].getScore() + HeartsConfig.MAX_HAND_POINTS);
				}
			}
		}
	}
	
	private void cleanStates() {
		for (HeartsPlayerState state : states) {
			state.clearTricks();
		}
	}
}
