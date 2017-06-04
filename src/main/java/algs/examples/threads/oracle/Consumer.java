/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algs.examples.threads.oracle;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {

    private final Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String message = drop.take();
                !message.equals("END");
                message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
            }
        }
    }

}
