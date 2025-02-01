package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final List<Card> deck;
    private final List<Player> players;

    public Game() {
        this.deck = new ArrayList<>();
        this.players = new ArrayList<>();
    }
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void initializeDeck() {
        for (String color : Card.COLORS) {
            for (String value : Card.VALUE_ORDER) {
                this.deck.add(new Card(value, color));
            }
        }
    }

    public void distributeCards() {
        Collections.shuffle(this.deck);
        int currentPlayerIndex = 0;
        for (Card card : this.deck) {
            this.players.get(currentPlayerIndex).addCard(card);
            currentPlayerIndex = (currentPlayerIndex + 1) % this.players.size();
        }
    }

    public void displayHands() {
        for (Player player : this.players) {
            System.out.println("\n" + player.getName() + " : ");
            for (Card card : player.getHand()) {
                System.out.println(card.getValue() + " de " + card.getColor());
            }
            System.out.println(); // Séparation entre les joueurs
        }
    }

    public void showDeck() {
        List<Card> clubCards = new ArrayList<>();
        List<Card> spadeCards = new ArrayList<>();
        List<Card> heartCards = new ArrayList<>();
        List<Card> diamondCards = new ArrayList<>();
        for (Card card : this.deck) {
            if (card.getColor().equals("Trèfle")) {
                clubCards.add(card);
            } else if (card.getColor().equals("Pique")) {
                spadeCards.add(card);
            } else if (card.getColor().equals("Cœur")) {
                heartCards.add(card);
            } else if (card.getColor().equals("Carreau")) {
                diamondCards.add(card);
            }
        }
        for (int i = 0; i < clubCards.size(); i++) {
            System.out.printf("%-25s%-25s%-25s%-25s\n",
                    clubCards.get(i),
                    spadeCards.get(i),
                    heartCards.get(i),
                    diamondCards.get(i));
        }
    }
    public void writeDeckToFile() {
        List<Card> clubCards = new ArrayList<>();
        List<Card> spadeCards = new ArrayList<>();
        List<Card> heartCards = new ArrayList<>();
        List<Card> diamondCards = new ArrayList<>();
        for (Card card : this.deck) {
            if (card.getColor().equals("Trèfle")) {
                clubCards.add(card);
            } else if (card.getColor().equals("Pique")) {
                spadeCards.add(card);
            } else if (card.getColor().equals("Cœur")) {
                heartCards.add(card);
            } else if (card.getColor().equals("Carreau")) {
                diamondCards.add(card);
            }
        }
        // Écriture des cartes dans le fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("deck_output.txt"))) {
            // On parcourt les cartes et on les écrit ligne par ligne
            for (int i = 0; i < clubCards.size(); i++) {
                writer.write(String.format("%-25s%-25s%-25s%-25s\n",
                        clubCards.get(i).getValue() + " de " + clubCards.get(i).getColor(),
                        spadeCards.get(i).getValue() + " de " + spadeCards.get(i).getColor(),
                        heartCards.get(i).getValue() + " de " + heartCards.get(i).getColor(),
                        diamondCards.get(i).getValue() + " de " + diamondCards.get(i).getColor()));
            }
            writer.flush();
            System.out.println("Le deck a été écrit dans le fichier 'deck_output.txt'.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Player playTrick(Player lastWinner) {
        List<Card> playedCards = new ArrayList<>();

        Card playedCard = lastWinner.playCard(null);
        playedCards.add(playedCard);
        Card bestCard = playedCard;
        Player winner = lastWinner;

        for (Player player : this.players) {
            if(!player.getName().equals(lastWinner.getName())) {
                playedCard = player.playCard(playedCard.getColor());
                playedCards.add(playedCard);
                // Comparer les cartes
                if (playedCard.compareTo(bestCard) > 0 && playedCard.getColor().equals(bestCard.getColor())) {
                    bestCard = playedCard;
                    winner = player;
                }

            }
        }
        for (Card card : playedCards) {
            winner.addWonCard(card);
        }
        return winner;
    }
    public Player playGame() {
        Player winnerTrick = this.players.get(0);
        for (int i=0; i< 13; i++) {
            winnerTrick = playTrick(winnerTrick);
        }
        Player winner = this.players.get(0);
        for (int i=1; i< this.players.size(); i++) {
            if (this.players.get(i).getWonCards().size() > winner.getWonCards().size()) {
                winner = this.players.get(i);
            }
        }
        return winner;
    }
}
