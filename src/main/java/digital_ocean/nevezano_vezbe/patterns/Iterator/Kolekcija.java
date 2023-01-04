package digital_ocean.nevezano_vezbe.patterns.Iterator;

import java.util.Iterator;

public class Kolekcija implements Iterable<String> {

	private final char[] karakteri;

	public Kolekcija(char[] karakteri) {
		this.karakteri = karakteri;
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			int pozicija = 0;

			@Override
			public boolean hasNext() {
				return pozicija < karakteri.length;
			}

			@Override
			public String next() {
				return !hasNext() ? null : Character.toString( karakteri[pozicija++] );
			}
		};
	}
}
