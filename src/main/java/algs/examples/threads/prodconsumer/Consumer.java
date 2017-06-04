package algs.examples.threads.prodconsumer;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import algs.examples.threads.prodconsumer.prob.Res;

public class Consumer extends Thread {

    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private final Queue queue;
    private static Map<Object, Integer> result;

    private static int counter = 0;

    private Res res;

    public Consumer(Queue queue, Map result) {
        this.queue = queue;
        Consumer.result = result;
    }

    public Consumer(Queue queue, Map result, Res res) {
        this(queue, result);
        this.res = res;
    }

    @Override
    public void run() {
        for (int k = 0; k < 100; k++) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        System.out.println("Consumer :" + this + ", queue is empty. Waiting...");
                        System.err.println("Is generation ended ? [" + res.toString() + "]");

                        queue.wait();
                    } catch (Exception e) {
                    }
                }

                for (int i = 0; i < Integer.min(4, queue.size()); i++) {
                    System.out.println("Consumer :" + this + ", IS TAKING #" + queue.poll() + ", size[" + queue.size() + "]");

                    if (result.get(this) == null) {
                        result.put(this, 0);
                    }
                    result.replace(this, 1 + result.get(this));

                    System.out.println("Res: " + this + " -> " + result.get(this));

                    System.err.println("-------");
                    System.err.println("--" + (counter++) + "--");
                    System.err.println("-------");

                    queue.notifyAll();
                }
            }

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
            }
        }
    }
}
