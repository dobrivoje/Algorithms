package Java67.konkurentno;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public abstract class ConverterTask implements Callable<String> {

	private final CountDownLatch latch;
	private       String         value;

	protected ConverterTask(CountDownLatch latch, String value) {
		this.latch = latch;
		this.value = value;
	}

	protected abstract String convert(String value);

	@Override
	public String call() throws Exception {
		value = convert( value );
		latch.countDown();

		return value;
	}
}
