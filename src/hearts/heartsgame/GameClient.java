package hearts.heartsgame;

import java.util.ArrayList;
import java.util.List;

import hearts.components.HeartsPlayer;

public class GameClient {
	public static void main(String[] args) {
		List<HeartsPlayer> players = new ArrayList<HeartsPlayer>();
		players.add(new HeartsPlayer());
		players.add(new HeartsPlayer());
		players.add(new HeartsPlayer());
		players.add(new HeartsPlayer());
		HeartsGameManager gameManager = new HeartsGameManager(null, players);
		gameManager.playGames(1);
	}
}