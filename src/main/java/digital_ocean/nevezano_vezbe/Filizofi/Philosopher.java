package digital_ocean.nevezano_vezbe.Filizofi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {

	private final Object leftFork;
	private final Object rightFork;

	private final Counter counter;

	public Philosopher(Object leftFork, Object rightFork, Counter counter) {
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		this.counter = counter;
	}

	private void doAction(String action) throws InterruptedException {
		String timeFormat = LocalDateTime.now().format( DateTimeFormatter.ofPattern( "mm:ss.ss" ) );
		String thName = Thread.currentThread().getName();
		System.err.println( timeFormat + " : " + thName + ", iteration: " + counter + " -> " + action );
		// TimeUnit.MILLISECONDS.sleep( 8 /*800*/ + new Random().nextInt( 15 /*1500*/ ) );
		TimeUnit.MILLISECONDS.sleep( 1 );

		counter.increment();
	}

	@Override
	public void run() {
		while (true) {
			try {
				doAction( " Thinking..." );

				synchronized (leftFork) {
					doAction( ": Picked up left fork" );

					/*synchronized (rightFork) {
						doAction( ": Picked up right fork" );
						System.err.println( Thread.currentThread().getName() + " >>>>>>>>>>>>> Eating..." );
					}*/
					doAction( ": Put down up right fork" );
				}

				synchronized (rightFork) {
					doAction( ": Picked up right fork" );
					System.err.println( Thread.currentThread().getName() + " >>>>>>>>>>>>> Eating..." );
				}

				doAction( ": Put down up left fork" );
				System.err.println( "--------------- #" + counter.getCounter() + "# ---------------" );
			} catch (InterruptedException e) {
				System.err.println( "interrupting...Thread : " + Thread.currentThread().getName() );
				System.err.println( ">>> Greška : " + e.getMessage() );
				Thread.currentThread().interrupt();
			}
		}
	}
}
