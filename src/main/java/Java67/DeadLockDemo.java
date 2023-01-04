package Java67;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://javarevisited.blogspot.com/2018/08/how-to-avoid-deadlock-in-java-threads.html">...</a>
 */
public class DeadLockDemo {

	final Object lock1 = new Object();
	final Object lock2 = new Object();

	final Queue<Integer> redZaCekanje;

	public Integer getValue(int index) {
		int size = redZaCekanje.size();
		if (index > size) {
			return null;
		}

		int i = 0;
		Integer v = null;
		for (Integer integer : redZaCekanje) {
			if (i++ == index) {
				return integer;
			}
		}

		return v;
	}

	public DeadLockDemo(Queue<Integer> redZaCekanje) {
		this.redZaCekanje = redZaCekanje;
	}

	@Deprecated
	private static void workTime() {
		long workTime = 10 + new Random().nextInt( 50 );
		try {
			TimeUnit.MILLISECONDS.sleep( workTime );
		} catch (InterruptedException e) {
			System.err.println( " work time method errror. Details : " + e.getMessage() );
		}
	}

	public void method1(String tName, Queue<Integer> red) {
		synchronized (lock1) {
			System.out.println( tName + " -> m1 : Acquired lock1" );
			workTime();

			synchronized (lock2) {
				System.out.println( tName + " -> m1 : Acquired lock2" );
				workTime();

				int e = new Random().nextInt( 10 );
				red.add( e );
			}
			System.out.println( tName + " -> m1 : Released lock2" );
		}
		System.out.println( tName + " -> m1 : Released lock1" );

		System.out.println( tName + " -> m1 : Finished :)" );
	}

	public void method2(String tName, Queue<Integer> red) {
		synchronized (lock2) {
			System.out.println( tName + " -> m2 : Acquired lock2" );
			workTime();

			synchronized (lock1) {
				System.out.println( tName + " -> m2 : Acquired lock1" );
				workTime();

				red.add( new Random().nextInt( 10 ) );
			}
			System.out.println( tName + " -> m2 : Released lock1" );
		}
		System.out.println( tName + " -> m2 : Released lock2" );

		System.out.println( tName + " -> m2 : Finished :)" );
	}

	private void zauzmi(Queue<Integer> red) {
		String tName = Thread.currentThread().getName();

		// deadlock : method1 -> method2
		// method1( tName, red );
		// method2( tName, red );

		// everything ok method1 -> method1
		method1( tName, red );
		method1( tName, red );
	}

	public static void main(String[] args) throws InterruptedException {
		Queue<Integer> red = new LinkedList<>();
		DeadLockDemo demo = new DeadLockDemo( red );

		/*Thread t1 = new Thread( () -> demo.zauzmi( red ), "t-1" );
		Thread t2 = new Thread( () -> demo.zauzmi( red ), "t-2" );
		Thread t3 = new Thread( () -> demo.zauzmi( red ), "t-3" );*/

		for (int i = 0; i < 15; i++) {
			Thread thread = new Thread( () -> demo.zauzmi( red ), "t-" + i );
			thread.start();
			//			thread.join();
		}

		System.err.println( Arrays.toString( demo.redZaCekanje.toArray() ) );
	}
}
