package algs.examples.threads.OrnamentalGarden;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Count {

    private int count;
    private final Random rand = new Random(47);

    private static final Object LOCK = new Object();

    public Count() {
        this.count = 0;
    }

    public int increment() {
        if (rand.nextBoolean()) {
            Thread.yield();
        }
        synchronized (LOCK) {
            return count++;
        }
    }

    public int value() {
        return count;
    }
}
