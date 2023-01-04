package algs.intervju2022.java67;

import org.openjdk.jmh.annotations.Threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadThisTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService e = Executors.newFixedThreadPool( 8 );

		SynchronizedMethods sm = new SynchronizedMethods();

		//<editor-fold desc="1.">
		/*List<Thread> incThreads = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			incThreads.add( new Thread( () -> {
				while (true) {
					sm.increment();
				}
			}, "th-inc-"+i ) );
		}
		List<Thread> getThreads = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			getThreads.add( new Thread( () -> {
				while (true) {
					System.err.println(">>>> " +sm.getCounter());
				}
			}, "th-get-"+i ) );
		}

		incThreads.forEach( Thread::start );
		getThreads.forEach( Thread::start );*/
		//</editor-fold>

		//<editor-fold desc="2.">
		/*IntStream.rangeClosed( 1, 4 ).boxed().forEach( ind -> {
			e.submit(
					new Thread( () -> {
						while (true) {
							sm.increment();
							if (sm.getCounter() % 100_000_000 == 0) {
								System.err.println( "wow, 100M inc.  " + new Date() );
							}
						}
					} ), "inc-th-" + 1 );
		} );

		IntStream.rangeClosed( 1,4 ).boxed().forEach( ind->{
			e.submit(
					new Thread( () -> {
						while (true) {
							System.err.println(">>>> " +sm.getCounter());
						}
					} ), "get-th-" + 2 );
		} );*/
		//</editor-fold>

		Thread incTh = new Thread( () -> {
			/*while (true) {
				sm.increment();
			}*/
			sm.increment();
		}, "th-inc." );

		Thread getTh = new Thread( () -> {
			/*while (true) {
				System.err.println( ">>> " + sm.getCounter() );
			}*/
			System.err.println( ">>> " + sm.getCounter() );
		}, "th-get." );

		incTh.start();
		TimeUnit.MILLISECONDS.sleep( 5 );
		getTh.start();
	}
}
