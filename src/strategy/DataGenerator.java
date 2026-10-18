package strategy;

import java.util.Random;

public final class DataGenerator {

    public static final int DEFAULT_SMALL_SIZE = 30;

    public static final int DEFAULT_LARGE_SIZE = 100_000;

    private DataGenerator() {
    }

    public static int[] randomIntArray(int size, long seed) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative: " + size);
        }

        Random random = new Random(seed);
        int[] data = new int[size];
        int bound = Math.max(100, size * 10);

        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(bound);
        }
        return data;
    }

    public static int[] copyOf(int[] source) {
        int[] copy = new int[source.length];
        System.arraycopy(source, 0, copy, 0, source.length);
        return copy;
    }
}
