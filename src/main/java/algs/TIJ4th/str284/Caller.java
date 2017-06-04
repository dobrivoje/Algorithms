package algs.TIJ4th.str284;

/**
 *
 * @author root
 */
public class Caller {

    private final IIncrementable callBackRef;

    public Caller(IIncrementable callBackRef) {
        this.callBackRef = callBackRef;
    }

    public void go() {
        callBackRef.increment();
    }
}
