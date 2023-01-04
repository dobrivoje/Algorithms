package algs.TIJ4th.ForkJoinTasks.primer1;

import algs.Util.Utils;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Primer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService E = Executors.newFixedThreadPool(4);
        Future<Integer> futureRes1 = E.submit(() -> {
            System.err.println("idem da spavam 1...");
            TimeUnit.MILLISECONDS.sleep(130);
            System.err.println("probudio sam se 1!");

            return Integer.valueOf("222");
        });

        Future<Integer> futureRes2 = E.submit(() -> {
            System.err.println("idem da spavam 2...");
            TimeUnit.MILLISECONDS.sleep(120);
            System.err.println("probudio sam se 2!");

            return Integer.valueOf("222");
        });

        System.err.println("nešto..");
//        Integer result = futureRes.get();
//        System.err.println("result = " + result);
        E.shutdown();

        AtomicInteger a = new AtomicInteger();
        ScheduledExecutorService S = Executors.newScheduledThreadPool(2);
        S.schedule(() -> {
            int helloNo = a.incrementAndGet();
//            System.err.println("Hello " + helloNo);
        }, 300, TimeUnit.MILLISECONDS);

        S.shutdown();

        CountDownLatch lock = new CountDownLatch(10);
        ScheduledExecutorService se = Executors.newScheduledThreadPool(2);
        se.scheduleAtFixedRate(() -> {
            int helloNo = a.incrementAndGet();
            System.err.println("Hello " + helloNo);
            lock.countDown();
        }, 2000, 100, TimeUnit.MILLISECONDS);

        lock.await(3000, TimeUnit.MILLISECONDS);
        se.shutdown();

        class TreeNode {

            final int           value;
            final Set<TreeNode> children;

            public TreeNode(int value, TreeNode... children) {
                this.value = value;
                this.children = new HashSet<>(Arrays.asList(children));
            }
        }

        class CountingTask extends RecursiveTask<Integer> {

            final TreeNode node;

            public CountingTask(TreeNode node) {
                this.node = node;
            }

            @Override
            protected Integer compute() {
                return node.value
                    + node.children.stream()
                                   .map(c -> new CountingTask(c).fork())
                                   .mapToInt(ForkJoinTask::join)
                                   .sum();
            }
        }

        TreeNode tree = new TreeNode(5,
                                     new TreeNode(3), new TreeNode(2,
                                                                   new TreeNode(2), new TreeNode(8)));

        ForkJoinPool pool = ForkJoinPool.commonPool();
        int sum = pool.invoke(new CountingTask(tree));
        System.err.println("sum = " + sum);

        System.err.println("--- primer 2");

        int nThreads = Runtime.getRuntime().availableProcessors();
        System.err.println("no of cpus: " + nThreads);

        class Sum extends RecursiveTask<Long> {

            private final int processingUpperLimit;

            private final int       low;
            private final int       high;
            private final Integer[] array;

            Sum(int processingUpperLimit, Integer[] array, Integer low, int high) {
                this.processingUpperLimit = processingUpperLimit;
                this.array = array;
                this.low = low;
                this.high = high;
            }

            @Override
            protected Long compute() {
                if (high - low <= processingUpperLimit) {
                    long sum = 0;
                    for (int i = low; i < high; ++i)
                        sum += array[i];

                    return sum;
                } else {
                    int mid = low + (high - low) / 2;
                    Sum left = new Sum(processingUpperLimit, array, low, mid);
                    Sum right = new Sum(processingUpperLimit, array, mid, high);
                    left.fork();
                    long rRes = right.compute();
                    long lRes = left.join();
                    return lRes + rRes;
                }
            }
        }

        final int UK1 = 10_000_000;
        Integer[] numbers = new Integer[UK1];

        Utils.executionTime(() -> {
            for (int i = 0; i < UK1; i++) {
                numbers[i] = i;
            }
            System.err.println("kreiranje niza od " + UK1 + " elemenata.");
        }, "ForkJoin example 0 with - " + UK1 + " elements");

        Utils.executionTime(() -> {
            long res0 = Arrays.stream(numbers).mapToLong(Long::valueOf).parallel().sum();
            System.err.println("res0 (parallel stream): " + res0);
        }, "ForkJoin example 0 with - " + UK1 + " elements");

        ForkJoinPool forkJoinPool = new ForkJoinPool(8);

        for (int testNo = 0; testNo < 20; testNo++) {
            System.err.println("-------------------------------------------");
            System.err.println("              test no: " + (1 + testNo));
            System.err.println("-------------------------------------------");
            int step = 100_000;
            for (int processingUpperLimit = step; processingUpperLimit < 1000_000; processingUpperLimit += step) {
                int finalProcessingUpperLimit = processingUpperLimit;
                Utils.executionTime(() -> {
                    long result1 = forkJoinPool.invoke(new Sum(finalProcessingUpperLimit, numbers, 0, numbers.length));
                    System.err.println("result1 = " + result1);
                }, "ForkJoin example 1 with - " + UK1 + " elements, and " + processingUpperLimit + " elements");
            }
        }
    }

    static int[] create(int noOfElements) {
        int[] x = new int[noOfElements];
//        CountDownLatch latch = new CountDownLatch()

        return x;
    }
}
