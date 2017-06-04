package algs.examples.threads;

import java.util.concurrent.TimeUnit;

public class Producer extends Thread {

    public Producer() {

        while (true) {
            System.err.println("#" + (int) (Math.random() * 100));

            try {
                TimeUnit.MILLISECONDS.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException ex) {
            }
        }
    }

}
