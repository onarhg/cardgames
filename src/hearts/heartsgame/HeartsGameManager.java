package hearts.heartsgame;

import java.io.PrintStream;
import java.util.List;

import hearts.components.HeartsPlayer;
import hearts.components.HeartsPlayerState;
import hearts.config.HeartsConfig;
import hearts.util.HeartsPass;

public class HeartsGameManager {
	private PrintStream stream;
	private HeartsPlayerState[] states;
	private HeartsPass currentPass;
	
	public HeartsGameManager(PrintStream stream, List<HeartsPlayer> players) {
		if (players.size() != HeartsConfig.NUM_PLAYERS) {
			throw new IllegalArgumentException(String.format("A game of hearts "
					+ "must have %d players", HeartsConfig.NUM_PLAYERS));
		}
		this.stream = stream;
		this.states = new HeartsPlayerState[HeartsConfig.NUM_PLAYERS];
		for(int i = 0; i < HeartsConfig.NUM_PLAYERS; i++) {
			states[i] = new HeartsPlayerState(players.get(i));
		}
	}
	
	public void playGame() {
		initGame();
		
		while(!gameOver()) {
			new HeartsHandManager(states, stream, currentPass).playHand();
			//TODO: printStuff();
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
		
		for(HeartsPlayerState state : states) {
			state.setScore(0);
		}
		
		currentPass = HeartsPass.LEFT;
	}
	
	private boolean gameOver() {
		for (HeartsPlayerState state : states) {
			if (state.getScore() >= HeartsConfig.MAX_SCORE) {
				return true;
			}
		}
		return false;
	}
	
	private void printStartGame() {
		stream.println(HeartsConfig.START_OF_GAME);
		for(HeartsPlayerState state : states) {
			stream.println(state.getPlayer());
		}
		stream.println(HeartsConfig.END_OF_PLAYER_LIST);
	}
	
	private void printEndGame() {
		// TODO: total scores? other fun stats?
		stream.println(HeartsConfig.END_OF_GAME);
	}
}
