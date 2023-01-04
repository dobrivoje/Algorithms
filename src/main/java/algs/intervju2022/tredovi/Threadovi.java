package algs.intervju2022.tredovi;

import java.util.concurrent.TimeUnit;

public class Threadovi {

	public static void main(String[] args) {
		Thread t1 = new Thread( () -> {
			System.err.println( "init...." );
			try {
				TimeUnit.MILLISECONDS.sleep( 1200 );
			} catch (InterruptedException e) {
				throw new RuntimeException( e );
			}
			System.err.println( "done." );
		}, "th-ime-1" );

		Thread t2 = new Thread( () -> {
			System.err.println( "init2...." );
			try {
				TimeUnit.MILLISECONDS.sleep( 800 );
			} catch (InterruptedException e) {
				throw new RuntimeException( e );
			}
			System.err.println( "done2." );
		}, "th-ime-2" );

		t1.start();
		t2.start();

		System.err.println("kraj");
	}

}
