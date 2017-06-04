package algs.examples.threads.deadlock.oracle;

public class DeadLock {

    public static void main(String[] args) {
        Friend dobri = new Friend("Dobri");
        Friend ticata = new Friend("Tićata");

        new Thread(() -> {
            dobri.bow(ticata);
        }).start();
        new Thread(() -> {
            ticata.bow(dobri);
        }).start();
    }
}
