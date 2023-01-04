package algs.intervju2022.tredovi.InterThreadCommunication;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Thread {

	private final Queue<Integer> sharedQueue;

	AtomicInteger counter;

	public Producer(Queue<Integer> sharedQueue, AtomicInteger counter) {
		super( "Producer1" );
		this.sharedQueue = sharedQueue;
		this.counter = counter;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (sharedQueue) {
				while (sharedQueue.size() >= 5) {
					try {
						System.err.println( "Queue is full, waiting" );
						sharedQueue.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}

				int e = new Random().nextInt( 11 );
				System.err.println( "producing : " + e );
				sharedQueue.add( e );
				sharedQueue.notifyAll();

				int i = counter.incrementAndGet();
				System.err.println("############  " + i);
			}
		}
	}
}
