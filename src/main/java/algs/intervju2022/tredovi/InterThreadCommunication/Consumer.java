package algs.intervju2022.tredovi.InterThreadCommunication;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {

	private final String         name;
	private final Queue<Integer> sharedQueue;

	public Consumer(String name, Queue<Integer> sharedQueue) {
		super( "Consumer1" );
		this.sharedQueue = sharedQueue;
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (sharedQueue) {
				while (sharedQueue.size() == 0) {
					try {
						System.err.println( name + " : Queue is empty, waiting" );
						sharedQueue.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}

				int number = sharedQueue.poll();
				System.err.println( name + " >>> consuming : " + number );
				sharedQueue.notifyAll();

				/*if (number == 10) {
					System.err.println( "Exiting..." );
					break;
				}*/
			}

			/*try {
				TimeUnit.MILLISECONDS.sleep( new Random().nextInt( 10 ) );
			} catch (InterruptedException e) {
			}*/
		}
	}
}
