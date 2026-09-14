package state;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your character's name: ");
        String name = scanner.nextLine();
        GameCharacter character = new GameCharacter(name);

        while (!(character.getState() instanceof MasterState)) {
            character.displayStatus();
            System.out.print("Choose an action: ");
            String action = scanner.nextLine().trim().toLowerCase();

            switch (action) {
                case "train":
                    character.train();
                    break;
                case "meditate":
                    character.meditate();
                    break;
                case "fight":
                    character.fight();
                    break;
                default:
                    System.out.println("Invalid action. Try again.");
            }
        }

        character.displayStatus();
        System.out.println("\nGame Over! You have reached Master level.");
        scanner.close();
    }
}