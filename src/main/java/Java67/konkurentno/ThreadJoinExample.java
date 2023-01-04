package Java67.konkurentno;

import java.util.concurrent.TimeUnit;

public class ThreadJoinExample {

	public static void main(String[] args) {
		Thread t1 = new Thread( new MyRunnable(), "t1" );
		Thread t2 = new Thread( new MyRunnable(), "t2" );
		Thread t3 = new Thread( new MyRunnable(), "t3" );

		try {
			t1.join( 2000 );
			t1.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// start second thread after waiting for 2 seconds or if it's dead
		t2.start();

		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// start third thread only when first thread is dead
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println( "All threads are dead, exiting main thread" );
	}

	//<editor-fold desc="MyRunnable">
	private static class MyRunnable implements Runnable {

		@Override
		public void run() {
			System.out.println( "Thread started:::" + Thread.currentThread().getName() );
			try {
				TimeUnit.SECONDS.sleep( 4 );
			} catch (Exception e) {
				System.err.println( "greška : " + e );
			}
			System.out.println( "Thread ended:::" + Thread.currentThread().getName() );
		}
	}
	//</editor-fold>
}
