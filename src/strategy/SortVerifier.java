package strategy;

public final class SortVerifier {

    private SortVerifier() {
        // Utility class: no instances needed.
    }

    public static boolean isSorted(int[] array) {
        if (array == null) {
            return false;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
