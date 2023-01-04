package algs.intervju2022.java67;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class ThA extends Thread {

    private final String thName;
    private final Runnable runnable;
//    private int val;

    public ThA( Runnable runnable,String thName) {
        this.thName = thName;
        this.runnable = runnable;
//        this.val = val;
    }

    @Override
    public void run() {
        while (true) {
            runnable.run();
        }
    }
}
