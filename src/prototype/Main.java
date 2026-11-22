package prototype;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static RecommendationRepository repository = new RecommendationRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Book Recommendation System ===");
            System.out.println("1. View all recommendations");
            System.out.println("2. Clone a recommendation");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> viewRecommendations();
                case 2 -> cloneRecommendation();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void viewRecommendations() {
        List<Recommendation> all = repository.getAll();
        if (all.isEmpty()) {
            System.out.println("No recommendations available.");
            return;
        }
        for (int i = 0; i < all.size(); i++) {
            System.out.println("\n[" + (i + 1) + "] " + all.get(i));
        }
    }

    private static void cloneRecommendation() {
        List<Recommendation> all = repository.getAll();
        if (all.isEmpty()) {
            System.out.println("Nothing to clone.");
            return;
        }

        System.out.println("\nSelect a recommendation to clone (1-" + all.size() + "): ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= all.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Recommendation original = all.get(index);
        Recommendation clone = original.clone();

        System.out.println("\nCloned recommendation:");
        System.out.println(clone);

        boolean modifying = true;
        while (modifying) {
            System.out.println("\nModify the clone:");
            System.out.println("1. Change target audience");
            System.out.println("2. Add a book");
            System.out.println("3. Remove a book");
            System.out.println("4. Save and return");

            int modChoice = Integer.parseInt(scanner.nextLine());

            switch (modChoice) {
                case 1 -> {
                    System.out.print("New target audience: ");
                    clone.setTargetAudience(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Publication year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    clone.addBook(new Book(title, author, genre, year));
                }
                case 3 -> {
                    if (clone.getBooks().isEmpty()) {
                        System.out.println("No books to remove.");
                        break;
                    }
                    System.out.println("Select a book to remove (1-" + clone.getBooks().size() + "): ");
                    for (int i = 0; i < clone.getBooks().size(); i++) {
                        System.out.println((i + 1) + ". " + clone.getBooks().get(i));
                    }
                    int bookIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    if (bookIndex >= 0 && bookIndex < clone.getBooks().size()) {
                        clone.removeBook(clone.getBooks().get(bookIndex));
                    } else {
                        System.out.println("Invalid book index.");
                    }
                }
                case 4 -> {
                    repository.add(clone);
                    System.out.println("Cloned recommendation saved to the repository.");
                    modifying = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}