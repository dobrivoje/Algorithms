package digital_ocean.nevezano_vezbe.Filizofi;

/**
 * <a href="https://www.baeldung.com/java-dining-philoshophers">...</a>
 */
public class Filozofi {

	public static void main(String[] args) {
		Counter counter = new Counter( 0 );
		Philosopher[] philosophers = new Philosopher[50];
		Object[] forks = new Object[philosophers.length];

		for (int i = 0; i < forks.length; i++) {
			forks[i] = new Object();
		}

		for (int i = 0; i < philosophers.length; i++) {
			Object leftFork = forks[i];
			// Object rightFork = forks[(i + 1) % forks.length];
			Object rightFork = forks[(i - 1 + philosophers.length) % philosophers.length];
			if (i == philosophers.length - 1) {
				philosophers[i] = new Philosopher( rightFork, leftFork, counter );
			} else {
				philosophers[i] = new Philosopher( leftFork, rightFork, counter );
			}

			Thread t = new Thread( philosophers[i], "Filozof" + i );
			t.start();
		}
	}

	/*static void algoritam() {
		while (true) {
			// think( someRandomTime )

			// pickUpLeft();
			// pickUpRight();
			// eat( someRandomTime  );
			// putDownLeft();
			// putDownRight();

			// opciono : timeTogetHungry (someRandomTime );
		}
	}*/
}
