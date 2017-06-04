package algs.examples.threads.Bank;

public class test {

    public static void main(String[] args) {
        Bank uniCredit = new Bank();
        uniCredit.addNewClient(new Client("D. Prtenjak", 1000));
        uniCredit.addNewClient(new Client("A. Prtenjak", 500));
        uniCredit.addNewClient(new Client("K. Prtenjak", 100));

        Client u = new Client("D. Prtenjak", 4000);
        Client k1 = new Client("A. Prtenjak", 500);
        Client k2 = new Client("K. Prtenjak", 300);

        Thread uplata1 = new Thread(new Uplata(u, k1));
        Thread uplata2 = new Thread(new Uplata(u, k2));

        uplata1.start();
        uplata2.start();

    }

}
