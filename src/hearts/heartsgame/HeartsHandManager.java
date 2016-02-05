package hearts.heartsgame;

import java.io.PrintStream;
import java.util.ArrayList;

import hearts.components.HeartsDeck;
import hearts.components.HeartsPlayerState;
import hearts.config.HeartsConfig;
import hearts.util.HeartsCardList;
import hearts.util.HeartsPair;
import hearts.util.HeartsPass;
import hearts.util.HeartsTrick;

public class HeartsHandManager {
	private PrintStream stream;
	private ArrayList<HeartsPlayerState> players;
	private HeartsDeck deck;
	
	public HeartsHandManager(ArrayList<HeartsPlayerState> players, PrintStream stream) {
		this.players = players;
		this.stream = stream;
		this.deck = new HeartsDeck();
	}
	
	public void initNewHand(HeartsPass passDirection) {
		ArrayList<HeartsCardList> passes = new ArrayList<HeartsCardList>();
		
		for (HeartsPlayerState player : players) {
			HeartsCardList pass = player.getPlayer().pass();
			
		}
	}
	
	private boolean checkPass(HeartsCardList pass, HeartsCardList oldHand) {
		if (pass.size() != HeartsConfig.PASS_SIZE) {
			return false;
		} else if(!oldHand.contains(pass)) {
			return false;
		}
		
		for (int i = 0; i < HeartsConfig.PASS_SIZE; i++) {
			for (int j = i; j < HeartsConfig.PASS_SIZE; j++) {
				if(pass.get(i).equals(pass.get(j))) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void deal() {
		for (HeartsPlayerState state : players) {
			HeartsCardList hand = (HeartsCardList) deck.drawCards(13);
			state.setHand(hand);
		}
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
