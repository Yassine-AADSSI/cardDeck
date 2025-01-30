package src;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player("Joueur 1"));
        game.addPlayer(new Player("Joueur 2"));
        game.addPlayer(new Player("Joueur 3"));
        game.addPlayer(new Player("Joueur 4"));

        game.initializeDeck();
        game.showDeck();
        //game.writeDeckToFile();
        game.distributeCards();
        //game.displayHands();
        Player winner = game.playGame();

        System.out.println("Félicitations au " + winner.getName() + " !");
        System.out.println(winner.getName() + " est le grand gagnant du Jeu");

    }
}
