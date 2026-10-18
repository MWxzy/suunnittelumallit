package strategy;

import java.util.Locale;

public class Main {

    private static final int WARM_UP_SIZE = 2_000;

    public static void main(String[] args) {
        int smallSize = args.length > 0
                ? Integer.parseInt(args[0]) : DataGenerator.DEFAULT_SMALL_SIZE;
        int largeSize = args.length > 1
                ? Integer.parseInt(args[1]) : DataGenerator.DEFAULT_LARGE_SIZE;
        long seed = args.length > 2 ? Long.parseLong(args[2]) : 42L;

        SortStrategy[] strategies = createStrategies();

        System.out.println("==========================================================");
        System.out.println(" Sorting Algorithm Performance Comparison (Strategy pattern)");
        System.out.println("==========================================================");
        System.out.println("Small data set : " + smallSize + " random integers");
        System.out.println("Large data set : " + largeSize + " random integers");
        System.out.println("Random seed    : " + seed);
        System.out.println();

        warmUp(strategies);

        int[] smallDataSet = DataGenerator.randomIntArray(smallSize, seed);
        int[] largeDataSet = DataGenerator.randomIntArray(largeSize, seed + 1);

        runBenchmark("Small data set (" + smallSize + " elements)", smallDataSet, strategies);
        runBenchmark("Large data set (" + largeSize + " elements)", largeDataSet, strategies);

        System.out.println();
        System.out.println("Done. Every result was verified to be in ascending order.");
    }

    private static SortStrategy[] createStrategies() {
        return new SortStrategy[] {
                new InsertionSort(),
                new QuickSort(),
                new MergeSort()
        };
    }

    private static void warmUp(SortStrategy[] strategies) {
        System.out.print("Warming up the JVM... ");
        int[] warmUpData = DataGenerator.randomIntArray(WARM_UP_SIZE, 7L);
        SortContext context = new SortContext();

        for (SortStrategy strategy : strategies) {
            context.setSortStrategy(strategy);
            context.sort(DataGenerator.copyOf(warmUpData));
        }
        System.out.println("ready.");
        System.out.println();
    }

    private static void runBenchmark(String title, int[] masterData, SortStrategy[] strategies) {
        System.out.println(title);
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%-16s %-14s %10s %14s %10s%n",
                "Algorithm", "Complexity", "Time (ms)", "Sorted?", "Relative");
        System.out.println("-------------------------------------------------------------------------------------");

        SortContext context = new SortContext();

        double fastestMillis = Double.MAX_VALUE;
        double[] times = new double[strategies.length];
        boolean[] sorted = new boolean[strategies.length];

        for (int i = 0; i < strategies.length; i++) {
            SortStrategy strategy = strategies[i];
            int[] data = DataGenerator.copyOf(masterData);

            context.setSortStrategy(strategy);

            long startTime = System.nanoTime();
            context.sort(data);
            long elapsedNanos = System.nanoTime() - startTime;

            times[i] = elapsedNanos / 1_000_000.0;
            sorted[i] = SortVerifier.isSorted(data);
            fastestMillis = Math.min(fastestMillis, times[i]);
        }

        for (int i = 0; i < strategies.length; i++) {
            SortStrategy strategy = strategies[i];
            String relative = String.format(Locale.ROOT, "%.1fx", times[i] / fastestMillis);

            System.out.printf(Locale.ROOT, "%-16s %-14s %10.3f %14s %10s%n",
                    strategy.getName(),
                    strategy.getAverageComplexity(),
                    times[i],
                    sorted[i] ? "yes" : "NO",
                    relative);
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
    }
}
