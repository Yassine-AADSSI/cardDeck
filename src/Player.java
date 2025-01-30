package src;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private List<Card> wonCards;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.wonCards = new ArrayList<>();
    }

    public List<Card> getWonCards() {
        return wonCards;
    }

    public void setWonCards(List<Card> wonCards) {
        this.wonCards = wonCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
    public void addCard(Card card) {
        hand.add(card);
    }
    public void removeCard(Card card) {
        hand.remove(card);
    }
    public void addWonCard(Card card) {
        wonCards.add(card);
    }
    public Card playCard(String leadingColor) {
        if(leadingColor == null) {
            return hand.remove(0);
        }
        if (!hand.isEmpty()) {
            for(Card card : hand) {
                if (card.getColor().equals(leadingColor)) {
                    return card;
                }
            }
            return hand.remove(0);
        }
        return null;
    }
}
