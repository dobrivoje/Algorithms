/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algs.examples.threads.Bank;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author root
 */
public class Uplata implements Runnable {

    private final Client uplatilac;
    private final Client kome;
    private int iznos;

    public Uplata(Client uplatilac, Client kome) {
        this.uplatilac = uplatilac;
        this.kome = kome;
    }

    public Uplata(Client uplatilac, Client kome, int iznos) {
        this(uplatilac, kome);
        this.iznos = iznos;
    }

    @Override
    public void run() {
        while (uplatilac.getAmmount() > 0) {
            iznos = 100 * (int) (1 + Math.round(2 * Math.random()));
            izvrsiTransakciju(uplatilac, kome, iznos);
        }
    }

    private synchronized void izvrsiTransakciju(Client uplatilac, Client kome, int iznos) {
        try {
            uplatilac.withdraw(iznos);
            kome.deposit(iznos);

            System.err.println(uplatilac.getName() + " - " + kome.getName());
            System.err.println("iznos: " + iznos);
            System.err.println("------------------------------");
            System.err.println("Stanja : ");
            System.err.println(uplatilac.getName() + uplatilac.getAmmount());
            System.err.println(kome.getName() + kome.getAmmount());
            System.err.println("------------------------------");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println("");

            System.err.println("Isplatilac : " + uplatilac.getName());
            System.err.println(uplatilac.getIsplate());
            System.err.println("TOTAL No of deposits : " + uplatilac.getIsplate().stream().count());

            int total = 0;
            System.err.println("TOTAL No of deposits : "
                    + uplatilac.getIsplate().stream().map((i) -> i).reduce(total, Integer::sum)
            );

            System.err.println("Kome : " + kome.getName());
            System.err.println(kome.getUplate());
        }

        try {
            TimeUnit.MILLISECONDS.sleep((int) (1000 * Math.random()));
        } catch (InterruptedException ex) {
        }
    }

}
