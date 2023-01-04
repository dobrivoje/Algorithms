package Java67.primer2_dobar;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public interface CallableWithLatch<T> extends Callable<T> {

	void setLatch(CountDownLatch latch);
}
