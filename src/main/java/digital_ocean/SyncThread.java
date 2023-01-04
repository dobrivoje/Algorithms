package digital_ocean;

import java.util.concurrent.TimeUnit;

public class SyncThread implements Runnable {

	private final Object obj1;
	private final Object obj2;

	public SyncThread(Object obj1, Object obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;

	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();

		System.out.println( threadName + " is trying to acquire lock 1" );
		synchronized (obj1) {
			System.out.println( threadName + " is ACQUIRED lock 1" );
			System.err.println( threadName + ">>> work1 begins..." );
			work();
			System.err.println( threadName + "<<< work1 ends." );

			System.out.println( threadName + " is trying to acquire lock 2" );
			synchronized (obj2) {
				System.out.println( threadName + " is ACQUIRED lock 2" );
				System.err.println( "work lock2.......end." );

				System.err.println( threadName + ">>> work2 begins..." );
				work();
				System.err.println( threadName + "<<< work2 ends." );
			}
			System.out.println( threadName + " is RELEASED lock 2" );
		}
		System.out.println( threadName + " is RELEASED lock 2" );

		System.out.println( threadName + " FINISHED." );
	}

	private void work() {
		try {
			TimeUnit.MILLISECONDS.sleep( 500 );
		} catch (InterruptedException e) {
		}
	}
}
