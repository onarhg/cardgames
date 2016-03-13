package player;

import cards.Card;
import cards.util.CardList;

public abstract class Player {
    protected CardList<? extends Card> hand;
    protected String name;

    public String toString() {
        return name;
    }
}
