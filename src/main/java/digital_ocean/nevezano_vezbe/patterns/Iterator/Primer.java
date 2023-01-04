package digital_ocean.nevezano_vezbe.patterns.Iterator;

public class Primer {

	public static void main(String[] args) {
		String s1 = "123456789";
		char[] v = s1.toCharArray();
		Kolekcija kolekcija = new Kolekcija( v );
		for (String s : kolekcija) {
			System.err.println( s );
		}
	}
}
