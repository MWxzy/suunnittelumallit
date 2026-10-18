package strategy;

public class MergeSort implements SortStrategy {

    @Override
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            // Safe middle calculation that avoids overflow on huge arrays.
            int middle = left + (right - left) / 2;

            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

    private void merge(int[] array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftHalf = new int[leftSize];
        int[] rightHalf = new int[rightSize];

        System.arraycopy(array, left, leftHalf, 0, leftSize);
        System.arraycopy(array, middle + 1, rightHalf, 0, rightSize);

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                array[k] = leftHalf[i];
                i++;
            } else {
                array[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = leftHalf[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public String getAverageComplexity() {
        return "O(n log n)";
    }
}
