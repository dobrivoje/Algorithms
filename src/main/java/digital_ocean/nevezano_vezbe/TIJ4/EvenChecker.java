package digital_ocean.nevezano_vezbe.TIJ4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {

	private final int          id;
	private final IntGenerator generator;

	public EvenChecker(int id, IntGenerator generator) {
		this.id = id;
		this.generator = generator;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val >= Integer.MAX_VALUE >> 3) {
				System.err.println( "Maximum achieved, no errors :) Existing." );
				generator.cancel();
			}

			if (val > 0 && (val % 100_000 == 0 || val % 100_0000 == 0)) {
				System.err.println( Thread.currentThread().getName() + " # Current element processed : " + val );
			}

			if (val % 2 != 0) {
				System.err.println( "val is not even ! val = " + val );
				generator.cancel();
			}
		}
	}

	public static void test(IntGenerator generator, int threadCount) {
		ExecutorService E = Executors.newCachedThreadPool();
		for (int i = 0; i < threadCount; i++) {
			E.execute( new EvenChecker( i, generator ) );
		}

		E.shutdown();
	}
}
