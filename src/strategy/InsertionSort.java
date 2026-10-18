package strategy;

public class InsertionSort implements SortStrategy {

    @Override
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        insertionSort(array);
    }

    private void insertionSort(int[] array) {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public String getAverageComplexity() {
        return "O(n^2)";
    }
}
