package cards.util;

import cards.StandardCard;
import cards.StandardSuit;

public class StandardCardList extends CardList<StandardCard> {
    private static final long serialVersionUID = 1L;

    public StandardCardList() {
        super();
    }

    StandardCardList getCardsOfSuit(StandardSuit suit) {
        StandardCardList newCardList = new StandardCardList();
        for (StandardCard card : this) {
            if (card.getSuit() == suit) {
                newCardList.add(card);
            }
        }
        return newCardList;
    }
}
