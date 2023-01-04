package Java67;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://javarevisited.blogspot.com/2013/02/how-to-join-multiple-threads-in-java-example-tutorial.html">...</a>
 */
public class MultiThreadsJoin {

	//	static final Logger logger = LogManager.getLogger( MultiThreadsJoin.class );

	public static void main(String[] args) throws InterruptedException {
		System.err.println( ">>>>>>>>> Start...." + new Date() );

		System.err.println( Thread.currentThread().getName() + " : thread begins." );

		Thread t1 = new Thread( workWithTime( 4000 ), "thread-0" );
		t1.start();

		Thread t2 = new Thread( workWithTime( 2000 ), "thread-1" );
		t2.start();

		Thread t3 = new Thread( workWithTime( 1000 ), "thread-2" );
		t3.start();

		t1.join();
		t2.join();
		t3.join();

		System.err.println( Thread.currentThread().getName() + " : thread ends." );

		System.err.println( "<<<<<<<<< End.  " + new Date() );
	}

	private static Runnable workWithTime(int time) {
		return () -> {
			System.err.println( Thread.currentThread().getName() + " : thread begins >>>" );
			try {
				TimeUnit.MILLISECONDS.sleep( time );
			} catch (InterruptedException e) {
			}

			System.err.println( Thread.currentThread().getName() + " : thread ends <<<" );
		};
	}
}
