package algs.examples.threads.Bank;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<Client> CLIENTS = new ArrayList<>();

    public void addNewClient(Client c) {
        CLIENTS.add(c);
    }

    public Client getClient(int i) {
        return CLIENTS.get(i);
    }

}
