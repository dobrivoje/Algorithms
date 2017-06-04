/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algs.examples.Iterator;

import java.util.Iterator;

/**
 *
 * @author root
 */
public class FluentData2 implements Iterable<Integer> {

    private final Data data;
    private int indeks = 0;

    public FluentData2(Data data) {
        this.data = data;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return indeks < data.getContent().length;
            }

            @Override
            public Integer next() {
                int[] c = data.getContent();
                return c[indeks++];
            }
        };
    }

}
