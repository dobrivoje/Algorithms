package algs.examples.threads.prodconsumer;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import algs.examples.threads.prodconsumer.prob.Res;

public class Producer extends Thread {

    private final Logger LOGGER = Logger.getLogger(Producer.class.getSimpleName());
    private final Queue queue;
    private Res resurs;

    public Producer(Queue queue) {
        this.queue = queue;
    }

    public Producer(Queue queue, Res resurs) {
        this(queue);
        this.resurs = resurs;
    }

    @Override
    public void run() {
        for (int k = 0; k < 100; k++) {
            synchronized (queue) {
                while (queue.size() > 20) {
                    try {
                        LOGGER.info("Producer : Queue is full [" + queue.size() + "]. Waiting...");
                        System.err.println("###" + queue);
                        queue.wait();
                    } catch (Exception e) {
                    }
                }

                int e = (int) (100 * Math.random());
                LOGGER.info("Producer PRODUCES #" + e + ", size[" + queue.size() + "]");
                queue.add(e);
                queue.notify();
            }

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException ex) {
            }
        }

        resurs.incRes();
    }
}
