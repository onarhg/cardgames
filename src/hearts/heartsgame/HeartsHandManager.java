package hearts.heartsgame;

import hearts.components.HeartsPlayerState;
import hearts.config.HeartsConfig;
import hearts.util.HeartsCardList;
import hearts.util.HeartsPass;

import java.util.ArrayList;

public class HeartsHandManager {
	private ArrayList<HeartsPlayerState> players;
	
	public HeartsHandManager(ArrayList<HeartsPlayerState> players) {
		this.players = players;
	}
	
	public void initNewHand(HeartsPass passDirection) {
		ArrayList<HeartsCardList> passes = new ArrayList<HeartsCardList>();
		
		for (HeartsPlayerState player : players) {
			HeartsCardList pass = player.getPlayer().pass();
			
		}
	}
	
	public boolean checkPass(HeartsCardList pass, HeartsCardList oldHand) {
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
}
