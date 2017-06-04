package algs.examples.threads.deadlock;

import java.util.concurrent.TimeUnit;

public class TestIspravakDeadLocka {

    public static void main(String[] args) {
        String res1 = "Tića";
        String res2 = "Dobri";

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (res1) {
                    System.err.println("Thread1 locked res1");

                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException ex) {
                    }
                }

                System.err.println("Thread1 unlocked res1");

                synchronized (res2) {
                    System.err.println("Thread1 locked res2");
                }

                System.err.println("Thread1 unlocked res2");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (res2) {
                    System.err.println("Thread2 locked res2");

                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException ex) {
                    }

                }

                System.err.println("Thread2 unlocked res2");

                synchronized (res1) {
                    System.err.println("Thread2 locked res1");
                }

                System.err.println("Thread2 unlocked res1");
            }
        });

        t1.start();
        t2.start();
    }
}
