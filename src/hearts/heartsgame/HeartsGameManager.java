package hearts.heartsgame;

import hearts.components.HeartsDeck;
import hearts.components.HeartsPlayer;
import hearts.config.HeartsConfig;
import hearts.util.HeartsPair;
import hearts.util.HeartsPlayerPointPair;
import hearts.util.HeartsTrick;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeartsGameManager {
	private PrintStream stream;
	private List<HeartsPlayerPointPair> pairs;
	private HeartsDeck deck;
	
	public HeartsGameManager(PrintStream stream, List<HeartsPlayer> players) {
		if (players.size() != HeartsConfig.NUM_PLAYERS) {
			throw new IllegalArgumentException(String.format("A game of hearts "
					+ "must have %d players", HeartsConfig.NUM_PLAYERS));
		}
		this.stream = stream;
		this.pairs = new ArrayList<HeartsPlayerPointPair>();
		for(HeartsPlayer player : players) {
			pairs.add(new HeartsPlayerPointPair(player, 0));
		}
		deck = new HeartsDeck();
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
	}
	
	public void playGames(int n) {
		for (int i = 0; i < n; i++) {
			playGame();
		}
	}
	
	private void initGame() {
		printStartGame();
		Collections.shuffle(pairs);
		for(HeartsPlayerPointPair pair : pairs) {
			pair.setPoints(0);
		}
	}
	
	private void playHand() {
		/* 
		 * TODO:
		 * Deal
		 * Pass
		 * Lead
		 */
	}
	
	private boolean gameOver() {
		for (HeartsPlayerPointPair pair : pairs) {
			if (pair.getPoints() >= HeartsConfig.MAX_SCORE) {
				return true;
			}
		}
		return false;
	}
	
	private void printStartGame() {
		stream.println(HeartsConfig.START_OF_GAME);
		for(HeartsPlayerPointPair pair : pairs) {
			stream.println(pair.getPlayer());
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
