package Java67.primer2_dobar;

import java.util.concurrent.CountDownLatch;

public interface RunnableWithLatch extends Runnable {

	void setLatch(CountDownLatch latch);
}
