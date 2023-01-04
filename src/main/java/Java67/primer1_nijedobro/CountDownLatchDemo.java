package Java67.primer1_nijedobro;

import algs.Util.Utils;
import Java67.primer2_dobar.Manager;

import java.util.Arrays;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		Utils.executionTime( () -> {
			System.out.println( Thread.currentThread().getName() + " has finished" );

			Worker first = new Worker( 8000, "W-1" );
			Worker second = new Worker( 1500, "W-2" );
			Worker third = new Worker( 2000, "W-3" );
			Worker fourth = new Worker( 2000, "W-4" );

			Manager manager = new Manager( Arrays.asList( first, second, third, fourth ) );
			manager.startAllAndAwaitExecution();

			System.out.println( Thread.currentThread().getName() + " has finished" );
		}, "test 1" );
	}
}
