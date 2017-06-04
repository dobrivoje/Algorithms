package algs.examples.threads.prodconsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import algs.examples.threads.prodconsumer.prob.Res;

public class test {

    public static void main(String[] args) {
        Res resurs = new Res(0);

        Queue<Integer> Q = new ConcurrentLinkedQueue<>();
        Map<Object, Integer> result = new HashMap<>();

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Producer(Q, resurs));

        for (int i = 0; i < 5; i++) {
            es.execute(new Consumer(Q, result, resurs));
        }

        while (!es.isTerminated()) {
            try {
                es.awaitTermination(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException ex) {
            }
        }

        es.shutdown();

        for (Map.Entry<Object, Integer> entry : result.entrySet()) {
            System.err.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
