package algs.examples.threads;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UnresponsiveUI implements Runnable {

    private volatile double d;

    @Override
    public void run() {
        while (d > 0) {
            d += Math.PI * Math.E / (37 * d + (1 / d));
        }

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ex) {
        }
        
        Thread.yield();

        try {
            System.in.read();
        } catch (IOException ex) {
        }
    }

    public static void main(String... args) {
        Executor e = Executors.newSingleThreadExecutor();
        e.execute(new UnresponsiveUI());
    }
}
