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
public class test {

    public static void main(String[] args) {
        FluentData2 fd = new FluentData2(new Data());

        for (Iterator iterator = fd.iterator(); iterator.hasNext();) {
            System.err.println(iterator.next());
        }


    }
}
