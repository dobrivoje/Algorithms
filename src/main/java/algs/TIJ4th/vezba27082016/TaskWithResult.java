package algs.TIJ4th.vezba27082016;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TaskWithResult implements Callable<String> {

    protected int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.MILLISECONDS.sleep((int) (10000 * Math.random()));
        return String.valueOf(2 * id);
    }

    public static void main(String[] args) {
        System.out.println("waiting 4 thread...");
        ExecutorService e = Executors.newCachedThreadPool();
        List<Future<String>> answer = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TaskWithResult task = new TaskWithResult(10);
            answer.add(e.submit(task));
        }

        e.execute(new Runnable() {
            @Override
            public void run() {
                for (Future<String> f : answer) {
                    System.err.println("-------------------------------");
                    try {
                        System.err.println(f.get());
                    } catch (InterruptedException | ExecutionException ex) {
                    }
                }
            }
        });

        for (int i = 0; i < 10; i++) {
            e.execute(new LiftOff(20));
        }

        e.shutdown();
    }

}
