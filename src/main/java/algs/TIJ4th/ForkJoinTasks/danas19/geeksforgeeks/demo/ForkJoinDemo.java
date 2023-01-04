package algs.TIJ4th.ForkJoinTasks.danas19.geeksforgeeks.demo;

import algs.Util.Utils;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ForkJoinDemo {

    public static void main(String[] args) {
        for (int testNo = 1; testNo <= 20; testNo++) {
            System.err.println("test : " + testNo);
            System.err.println();

            //<editor-fold desc="data init">
            int NUM_NO = 100_000_000;
            double[] nums = new double[NUM_NO];
            for (int i = 0; i < NUM_NO; i++) {
                nums[i] = i;
            }
            //</editor-fold>
            Utils.executionTime(() -> {
                SqrtTransform.singleComputation(nums, 0, NUM_NO);
            }, "Linear execution");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }

            Utils.executionTime(() -> {
                ForkJoinPool fjp = new ForkJoinPool();
                fjp.invoke(new SqrtTransform(nums, 0, NUM_NO));
            }, "Parallel execution");

            System.err.println("------------------------------------");
        }
    }
}
