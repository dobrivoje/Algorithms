package Java67.primer1mojeizmene;

import algs.Util.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

	static int threadsNo = 1;
	static int workerNo  = 20;

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch( threadsNo );
		List<Worker> workers = createWorkers( latch );

		Utils.executionTime( () -> {
			executeParallel( workers, latch );
			try {
				latch.await();
			} catch (InterruptedException e) {
				throw new RuntimeException( e );
			}
		}, "Parallel" );

//		Utils.executionTime( () -> executeSerial( workers ), "Serial" );
	}

	private static List<Worker> createWorkers(CountDownLatch latch) {
		Random random = new Random();

		List<Worker> workers = new LinkedList<>();
		for (int i = 0; i < workerNo; i++) {
			int delay = random.nextInt( 100 );
			workers.add( new Worker( delay, latch, "WORKER-" + (1 + i) ) );
		}

		return workers;
	}

	private static void executeSerial(List<Worker> workers) {
		try {
			for (Worker worker : workers) {
				TimeUnit.MILLISECONDS.sleep( worker.getDelay() );
				System.out.println( Thread.currentThread().getName() + " has finished. Delay = " + worker.getDelay() );
			}
		} catch (InterruptedException e) {
			throw new RuntimeException( e );
		}
	}

	private static void executeParallel(List<Worker> workers, CountDownLatch latch) {
		try {
			workers.forEach( Thread::start );
			latch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException( e );
		}
	}
}
