package algs.examples.threads;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class test {

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("cmd");
        
        Multi m = new Multi();
        System.err.println("stanje : " + m.getState());
        m.start();
        System.err.println("stanje posle m.start() : " + m.getState());
        System.err.println("is alive ? " + m.isAlive());
        System.err.println("is daemon ? " + m.isDaemon());

        System.err.println("------------------------------------------------");
        System.err.println("");
        /*
        System.err.println("test 2");
        Producer p = new Producer();
        p.start();
         */
        ExecutorService e = Executors.newCachedThreadPool();
        e.execute(() -> {
            int counter = 5;

            while (--counter > 0) {
                System.err.println("#" + (int) (Math.random() * 100));
                try {
                    TimeUnit.MILLISECONDS.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException ex) {
                }
            }
        });
        e.shutdown();

    }

}
