package algs.examples.threads.OrnamentalGarden;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrnamentalTest {

    public static void main(String... args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            exec.execute(new Entrance(i));
        }

        TimeUnit.SECONDS.sleep(5);
        Entrance.cancel();
        exec.shutdown();

        if (!exec.awaitTermination(1, TimeUnit.SECONDS)) {
            System.err.println("Some task are not yet finished !");
        }

        System.out.println("Total from counter : " + Entrance.getTotalCount());
        System.out.println("Total from Entrances : " + Entrance.sumEntrances());

    }
}
