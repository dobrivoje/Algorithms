package algs.TIJ4th.vezba27082016;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class LiftOff implements Runnable {

    protected int countDown = 10;
    private static int taskNo = 0;
    private final int ID = taskNo++;

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    private String status() {
        try {
            TimeUnit.MILLISECONDS.sleep((int) (1000 * Math.random()));
        } catch (InterruptedException ex) {
        }
        
        return "#" + ID + "(" + (countDown > 0 ? countDown : "Lift off!") + ")";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
        }
    }

    public static void main(String[] args) {
        System.out.println("waiting 4 thread...");
        ExecutorService e = Executors.newFixedThreadPool(1);
        
        for (int i = 0; i < 2; i++) {
            LiftOff l = new LiftOff(10);
            e.execute(l);
        }
        
        e.shutdown();
    }

}
