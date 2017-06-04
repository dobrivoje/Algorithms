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
public class FluentData implements Iterator<Integer> {

    private final Data data = new Data();
    private int index = 0;

    @Override
    public boolean hasNext() {
        return data.getContent().length > 0;
    }

    @Override
    public Integer next() {
        int[] c = data.getContent();

        if (index++ > c.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return c[index++];

    }

}
