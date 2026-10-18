package strategy;

public class SortContext {

    private SortStrategy sortStrategy;

    public SortContext() {
    }

    public SortContext(SortStrategy sortStrategy) {
        setSortStrategy(sortStrategy);
    }

    public void setSortStrategy(SortStrategy sortStrategy) {
        if (sortStrategy == null) {
            throw new IllegalArgumentException("Sort strategy cannot be null.");
        }
        this.sortStrategy = sortStrategy;
    }

    public SortStrategy getSortStrategy() {
        return sortStrategy;
    }

    public void sort(int[] array) {
        if (sortStrategy == null) {
            throw new IllegalStateException("No sort strategy has been set.");
        }
        sortStrategy.sort(array);
    }
}
