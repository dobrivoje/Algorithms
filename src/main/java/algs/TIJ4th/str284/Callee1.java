/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algs.TIJ4th.str284;

/**
 *
 * @author root
 */
public class Callee1 implements IIncrementable {

    private int i = 0;

    @Override
    public void increment() {
        i++;
        System.err.println("Callee1.i: " + i);
    }

}
