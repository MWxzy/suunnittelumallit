package prototype;

import java.util.ArrayList;
import java.util.List;

public class RecommendationRepository {
    private List<Recommendation> recommendations = new ArrayList<>();

    public RecommendationRepository() {
        Recommendation fantasy = new Recommendation("Fantasy Fans");
        fantasy.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 1937));
        fantasy.addBook(new Book("A Game of Thrones", "George R.R. Martin", "Fantasy", 1996));

        Recommendation sciFi = new Recommendation("Sci‑Fi Enthusiasts");
        sciFi.addBook(new Book("Dune", "Frank Herbert", "Sci‑Fi", 1965));
        sciFi.addBook(new Book("Neuromancer", "William Gibson", "Sci‑Fi", 1984));

        recommendations.add(fantasy);
        recommendations.add(sciFi);
    }

    public List<Recommendation> getAll() {
        return recommendations;
    }

    public void add(Recommendation rec) {
        recommendations.add(rec);
    }
}