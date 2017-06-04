package algs.examples.threads;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ResponsiveUI extends Thread {

    private static double d = 1;

    public ResponsiveUI() {
        //setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            d += Math.PI * Math.E / d;

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
            }

            Thread.yield();

            System.err.println("rezultat : " + d);
        }
    }

    public static void main(String... args) throws IOException {
        Executor e = Executors.newSingleThreadExecutor();
        e.execute(new ResponsiveUI());

        int ch;
        while ((ch = System.in.read()) != '$') {
            System.err.println(">> " + ch);
        }

    }
}
