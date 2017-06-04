package algs.TIJ4th.str284;

public class Callee2 extends MyIncrement {

    private int i = 0;

    @Override
    public void increment() {
        super.increment();
        i++;
        System.err.println("Callee2.i: " + i);
    }

    private class Closure implements IIncrementable {

        @Override
        public void increment() {
            Callee2.this.increment();
        }
    }

    IIncrementable getCallbackRef() {
        return new Closure();
    }
}
