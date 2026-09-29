package temple;

import java.util.Random;

public class DiceGame extends Game {

    private int numberOfPlayers;
    private int[] scores;
    private int[] diceRolls;

    private final int winningScore = 3;

    private Random random = new Random();

    @Override
    public void initializeGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;

        scores = new int[numberOfPlayers];
        diceRolls = new int[numberOfPlayers];

        System.out.println("=== Dice Game ===");
        System.out.println("First player to reach " + winningScore + " points wins!");
        System.out.println();
    }

    @Override
    public boolean endOfGame() {
        for (int score : scores) {
            if (score >= winningScore) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void playSingleTurn(int player) {
        int roll = random.nextInt(6) + 1;

        diceRolls[player] = roll;

        System.out.println(
                "Player " + (player + 1) + " rolled a " + roll
        );

        // After the last player has rolled,
        // determine the winner of the round.
        if (player == numberOfPlayers - 1) {
            determineRoundWinner();
        }
    }

    private void determineRoundWinner() {
        int highestRoll = 0;
        int roundWinner = -1;

        for (int i = 0; i < numberOfPlayers; i++) {
            if (diceRolls[i] > highestRoll) {
                highestRoll = diceRolls[i];
                roundWinner = i;
            }
        }

        if (roundWinner != -1) {
            scores[roundWinner]++;

            System.out.println();
            System.out.println(
                    "Player " + (roundWinner + 1)
                            + " wins the round!"
            );

            System.out.println("Score:");

            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println(
                        "Player " + (i + 1) + ": " + scores[i]
                );
            }

            System.out.println();
        }
    }

    @Override
    public void displayWinner() {
        for (int i = 0; i < numberOfPlayers; i++) {
            if (scores[i] >= winningScore) {
                System.out.println(
                        "Player " + (i + 1) + " wins the game!"
                );
                return;
            }
        }
    }
}