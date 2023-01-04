package digital_ocean.nevezano_vezbe.TIJ4;

public class SpecialGenerator extends IntGenerator {

	private       int    currVal = 0;
	private final Object lock    = new Object();

	@Override
	public int next() {
		synchronized (lock) {
			currVal++;
			currVal++;
			currVal++;
			currVal++;
			currVal++;
			currVal++;

			return currVal;
		}
	}

	public static void main(String[] args) {
		EvenChecker.test( new SpecialGenerator(), 10 );
	}
}
