package hearts.heartsgame;

import hearts.components.HeartsDeck;
import hearts.components.HeartsPlayer;
import hearts.components.HeartsPlayerState;
import hearts.config.HeartsConfig;
import hearts.util.HeartsCardList;
import hearts.util.HeartsPair;
import hearts.util.HeartsPass;
import hearts.util.HeartsTrick;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeartsGameManager {
	private PrintStream stream;
	private List<HeartsPlayerState> playerStates;
	private HeartsDeck deck;
	private HeartsPass currentPass;
	
	public HeartsGameManager(PrintStream stream, List<HeartsPlayer> players) {
		if (players.size() != HeartsConfig.NUM_PLAYERS) {
			throw new IllegalArgumentException(String.format("A game of hearts "
					+ "must have %d players", HeartsConfig.NUM_PLAYERS));
		}
		this.stream = stream;
		this.playerStates = new ArrayList<HeartsPlayerState>();
		for(HeartsPlayer player : players) {
			playerStates.add(new HeartsPlayerState(player));
		}
		deck = null;
	}
	
	// IDK what the best way to structure this is, but if we have printing separated from the the play of the game,
	// we still have to pass game state back and forth to make printing possible
	public void playGame() {
		initGame();
		
		while(!gameOver()) {
			/* 
			 * TODO:
			 * playHand(int passIndex);
			 * printStuff();
			 */
		}
		
		printEndGame();
	}
	
	public void playGames(int n) {
		for (int i = 0; i < n; i++) {
			playGame();
		}
	}
	
	private void initGame() {
		printStartGame();
		
		Collections.shuffle(playerStates);
		for(HeartsPlayerState state : playerStates) {
			state.setScore(0);
		}
		
		deck = new HeartsDeck();
		currentPass = HeartsPass.LEFT;
	}
	
	private void playHand() {
		/* 
		 * TODO:
		 * Deal
		 * Pass
		 * Lead
		 */
		deal();
	}
	
	private void deal() {
		for (HeartsPlayerState state : playerStates) {
			HeartsCardList hand = (HeartsCardList) deck.drawCards(13);
			state.setHand(hand);
		}
	}
	
	private boolean gameOver() {
		for (HeartsPlayerState state : playerStates) {
			if (state.getScore() >= HeartsConfig.MAX_SCORE) {
				return true;
			}
		}
		return false;
	}
	
	private void printStartGame() {
		stream.println(HeartsConfig.START_OF_GAME);
		for(HeartsPlayerState state : playerStates) {
			stream.println(state.getPlayer());
		}
		stream.println(HeartsConfig.END_OF_PLAYER_LIST);
	}
	
	private void printEndGame() {
		// TODO: total scores? other fun stats?
		stream.println(HeartsConfig.END_OF_GAME);
	}
	
	private void printTrick(HeartsTrick trick) {
		for(HeartsPair pair: trick.getPairs()) {
			stream.print(pair.getPlayer());
			stream.print(":");
			stream.print(pair.getCard());
		}
		
		stream.println();
	}
}
