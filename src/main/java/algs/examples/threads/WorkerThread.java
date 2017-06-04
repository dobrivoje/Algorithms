package algs.examples.threads;

import java.util.concurrent.TimeUnit;

public class WorkerThread extends Thread {

    public WorkerThread() {
        setDaemon(true);
    }

    @Override
    public void run() {
        int count = 0;

        while (true) {
            System.err.println("Worker thread #" + count++);

            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException ex) {
            }
        }
    }

}
