/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algs.examples.threads.oracle;

public class Drop {

    private String message;

    private boolean empty = true;

    public synchronized String take() {
        while (empty) {
            try {
                wait();
            } catch (Exception e) {
            }
        }

        empty = true;
        notifyAll();

        return message;
    }

    public synchronized void put(String message) {
        while (!empty) {
            try {
                wait();
            } catch (Exception e) {
            }
        }

        empty = false;
        this.message = message;
        notifyAll();
    }

}
