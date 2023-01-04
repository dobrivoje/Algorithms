package algs.TIJ4th.ForkJoinTasks.primer1;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class primer25_11_2020 {

    static class CustomRecursiveTask extends RecursiveTask<Long> {

        private final        List<Integer> arr;
        private static final int           THRESHOLD = 400;

        public CustomRecursiveTask(List<Integer> arr) {
            this.arr = arr;
        }

        @Override
        protected Long compute() {
            if (arr.size() > THRESHOLD) {
                return ForkJoinTask.invokeAll(createSubTasks())
                                   .stream()
                                   .mapToLong(ForkJoinTask::join)
                                   .sum();
            } else {
                return arr.stream()
                          // .filter(elem -> elem >= 10 && elem <= 20)
                          .mapToLong(elem -> elem)
                          .sum();
            }
        }

        private Collection<CustomRecursiveTask> createSubTasks() {
            List<CustomRecursiveTask> L = new ArrayList<>();

            int arrayLength = arr.size();
            int noOfIterations = arrayLength / THRESHOLD;

            int startIndex, endIndex;
            for (int i = 0; i < noOfIterations; i++) {
                startIndex = i * THRESHOLD;
                endIndex = startIndex + THRESHOLD - 1;
                L.add(new CustomRecursiveTask(arr.subList(startIndex, endIndex)));
            }

            // L.add(new CustomRecursiveTask(arr.subList(0, size / 2)));
            // L.add(new CustomRecursiveTask(arr.subList(size / 2, size)));

            return L;
        }
    }

    public static void main(String[] args) {
        int MILLIONS = 5_000_000;
        Random random = new Random();

        // List<Integer> elements = IntStream.range(0, MILLIONS).boxed().collect(Collectors.toList());
        List<Integer> elements = IntStream.generate(() -> random.nextInt(100))
                                          .limit(MILLIONS)
                                          // .parallel()
                                          .boxed().collect(Collectors.toList());

        long finalSum = ForkJoinPool.commonPool()
                                    .invoke(new CustomRecursiveTask(elements));

        System.err.println("Final sum: " + finalSum);
    }
}
