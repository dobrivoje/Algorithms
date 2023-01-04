package Java67.konkurentno;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Lowerer extends ConverterTask {

	protected Lowerer(CountDownLatch latch, String value) {
		super( latch, value );
		System.err.println( "Lowerer - init." );
	}

	@Override
	protected String convert(String value) {
		System.err.println( ">>> Lowerer - begin." );
		try {
			TimeUnit.SECONDS.sleep( 5 );
		} catch (InterruptedException e) {
		}
		System.err.println( "<<< Lowerer - end." );

		return value == null ? "" : value.toLowerCase();
	}
}
