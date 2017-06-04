package algs.examples.threads.Bank;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private String name;
    private int ammount;
    private List<Integer> uplate;
    private List<Integer> isplate;

    public Client(String name) {
        this.name = name;

        uplate = new ArrayList<>();
        isplate = new ArrayList<>();
    }

    public Client(String name, int ammount) {
        this(name);
        this.ammount = ammount;
    }

    public synchronized int getAmmount() {
        return ammount;
    }

    public synchronized void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized void deposit(int amount) {
        this.ammount += amount;
        uplate.add(amount);
    }

    public synchronized void withdraw(int amount) throws Exception {
        if (this.ammount < amount) {
            throw new Exception("Nema dovoljno para !");
        }

        this.ammount -= amount;
        isplate.add(amount);
    }

    public List<Integer> getUplate() {
        return uplate;
    }

    public List<Integer> getIsplate() {
        return isplate;
    }

}
