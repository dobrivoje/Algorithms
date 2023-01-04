package Java67.konkurentno;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CountDownLatchExample {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl = new CountDownLatch( 2 );
		ExecutorService E = Executors.newCachedThreadPool();

		String message = "Hello, World";

		List<Callable<String>> tasks = Arrays.asList( new Lowerer( cdl, message ),
													  new Upperer( cdl, message ) );
		System.err.println( "---------------------------------------------" );
		System.err.println( "početak" );
		System.err.println( "---------------------------------------------" );

		List<Future<String>> result = tasks.stream().map( E::submit ).collect( Collectors.toList() );
		cdl.await();
		E.shutdown();
		System.err.println( "---------------------------------------------" );
		System.err.println( "kraj" );
		System.err.println( "---------------------------------------------" );
		System.err.println();
		System.err.println( "rezultat:" );
		System.err.println();
		for (Future<String> stringFuture : result) {
			try {
				System.err.println( stringFuture.get() );
			} catch (ExecutionException e) {
			}
		}
	}
}
