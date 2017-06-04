package algs.examples.threads.deadlock.oracle;

import java.util.concurrent.TimeUnit;

public class TestIspravakDeadLocka {

    public static void main(String[] args) {
        String res1 = "Tića";
        String res2 = "Dobri";

        final Object naklon = new Object();

        Thread dobrivoje = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (naklon) {
                    System.err.println("Dobri - počinje naklon !");

                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException ex) {
                    }
                }

                System.err.println("Dobri se ispravlja !");
            }
        });

        Thread ticata = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (naklon) {
                    System.err.println("Tićata - počinje naklon !");

                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException ex) {
                    }

                }

                System.err.println("Tićata se ispravlja !");
            }
        });

        dobrivoje.start();
        ticata.start();
    }
}
