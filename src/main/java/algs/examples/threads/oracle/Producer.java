/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algs.examples.threads.oracle;

import java.util.concurrent.TimeUnit;

public class Producer extends Thread {

    private final Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };

        for (String s : importantInfo) {
            drop.put(s);

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }

        drop.put("END");
    }

}
