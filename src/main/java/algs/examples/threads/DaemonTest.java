package algs.examples.threads;

import java.util.concurrent.TimeUnit;

public class DaemonTest {

    public static void main(String[] args) {
        new WorkerThread().start();

        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException ex) {
        }

        System.out.println("Main Thread ending");
    }

}
