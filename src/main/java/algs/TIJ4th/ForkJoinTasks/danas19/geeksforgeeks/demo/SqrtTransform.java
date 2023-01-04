package algs.TIJ4th.ForkJoinTasks.danas19.geeksforgeeks.demo;

import java.util.concurrent.RecursiveAction;

public class SqrtTransform extends RecursiveAction {

    static final int      seqThreshold = 1_000;
    final        double[] data;
    final        int      start;
    final        int      end;

    private boolean isEleigible() {
        return end - start >= seqThreshold;
    }

    public SqrtTransform(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (isEleigible()) {
            int mid = (start + end) / 2;

            invokeAll(
                new SqrtTransform(data, start, mid),
                new SqrtTransform(data, mid, end)
            );
        } else {
            singleComputation(data, start, end);
        }
    }

    public static void singleComputation(double[] data, int start, int end) {
        for (int i = start; i < end; i++) {
            data[i] = Math.sqrt(data[i]);
        }
    }
}
