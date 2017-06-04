/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algs.examples.threads.oracle;


public class test {

    public static void main(String[] args) {
        Drop drop = new Drop();
        new Producer(drop).start();
        new Consumer(drop).start();
    }

}
