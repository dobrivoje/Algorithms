package algs.examples.threads.OrnamentalGarden;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Entrance implements Runnable {

    //<editor-fold defaultstate="collapsed" desc="infra">
    private static final Count COUNT = new Count();
    private static final List<Entrance> ENTRANCES = new ArrayList<>();

    private int noOfPassThroughEntrance = 0;
    private final int id;
    private static volatile boolean canceled = false;

    private static final Object LOCK = new Object();

    public Entrance(int id) {
        this.id = id;
        ENTRANCES.add(this);
    }

    public static void cancel() {
        canceled = true;
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (LOCK) {
                ++noOfPassThroughEntrance;
            }

            System.out.println(this + ", Total : " + COUNT.increment());

            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException ex) {
            }
        }

        System.out.println("Stopping this !");
    }

    public synchronized int getValue() {
        return noOfPassThroughEntrance;
    }

    @Override
    public String toString() {
        return "Entrance #" + id + " : " + getValue();
    }

    public static int getTotalCount() {
        return COUNT.value();
    }

    public static int sumEntrances() {
        int sum = 0;

        for (Entrance E : ENTRANCES) {
            sum += E.getValue();
        }

        return sum;
    }

}
