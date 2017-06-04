package algs.examples.threads;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TaskWithResult implements Callable<String> {

    private int i = 0;

    public TaskWithResult(int id) {
        i = id;
    }

    @Override
    public String call() throws Exception {
        try {
            TimeUnit.SECONDS.sleep((long) (3 * Math.random()));
        } catch (Exception e) {
        }

        return "Task produced : #" + i;
    }

    public static void main(String... args) {
        ExecutorService E = Executors.newSingleThreadExecutor();
        ArrayList<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            results.add(E.submit(new TaskWithResult(i)));
        }

        results.stream().forEach((r) -> {
            try {
                System.err.println(r.get());
            } catch (InterruptedException | ExecutionException ex) {
            } finally {
                E.shutdown();
            }
        });
    }

}
