package algs.TIJ4th.collections;

import algs.TIJ4th.BeanInfo.ProcesorAnotacija.Transformator;
import lombok.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author root
 */
@Data
public class Person implements Comparable<Person> {

	@Transformator(process = "---> ")
	@Transformator.Include(name = "oket")
	private String name;

	private int  id;
	private Date dob;

	public Person(String name, int id, Date dob) {
		this.name = name;
		this.id = id;
		this.dob = dob;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || this.getClass() != other.getClass()) {
			return false;
		}

		Person guest = (Person) other;

		return this.id == guest.id
				&& this.name != null && this.name.equals( guest.name )
				&& this.dob != null && this.dob.equals( guest.dob );
	}

	@Override
	public int hashCode() {
		int res = 0;

		res = 31 * res + id;
		res = 31 * res + (name != null ? name.hashCode() : 0);
		res = 31 * res + (dob != null ? dob.hashCode() : 0);

		return res;
	}

	@Override
	public int compareTo(Person other) {
		return this.id - other.id;
	}

	public static void main(String args[]) throws FileNotFoundException, IOException {
		boolean bxxx = Stream.of( null, null, true ).filter( Objects::nonNull ).anyMatch( e -> e.booleanValue() );
		System.err.println( "bxxx = " + bxxx );

		String origString = "Dobri";
		String reversedStr;
		System.out.println( "Original String: " + origString );

		reversedStr = obrniNaopako( origString );
		System.out.println( "Reverse String : " + reversedStr );
		//
        /*int n1 = 3, n2 = 7;
        for (int slucajniBroj = n1 + n2; slucajniBroj < 1001; slucajniBroj++) {
        if (!sastaviIznos(slucajniBroj, false, n1, n2)) {
        System.err.println("Broj " + slucajniBroj + ", ne može se sastaviti od " + n1 + " i " + n2);
        }
        }
         */
		//System.err.println(printBar(5, 0));

		List<Integer> s = new ArrayList<>( Arrays.asList( 1, 2, 3, 4 ) );
		List<Integer> sc = new ArrayList<>( Arrays.asList( 1, 2, 3, 4 ) );
		boolean b = sc.containsAll( Arrays.asList( 6, 2, 3 ) );
		System.err.println( b );
		Scanner scanner = new Scanner( System.in );
		while (scanner.hasNextInt()) {
			System.err.println( scanner.nextInt() );
		}
	}

	public static String obrniNaopako(String str) {
		if (str.length() < 2) {
			return str;
		} else {
			return obrniNaopako( str.substring( 1 ) ) + str.charAt( 0 );
		}
	}

	public static boolean sastaviIznos(int koliko, boolean stampa, int novcic1, int novcic2) {
		if (koliko == 0) {
			//System.err.println("Gotovo !");
			return true;
		} else if (koliko >= novcic1 && sastaviIznos( koliko - novcic1, stampa, novcic1, novcic2 )) {
			if (stampa) {
				System.err.println( "Jedan novčić od " + novcic1 );
			}
			return true;
		} else if (koliko >= novcic2 && sastaviIznos( koliko - novcic2, stampa, novcic1, novcic2 )) {
			if (stampa) {
				System.err.println( "Jedan novčić od " + novcic2 );
			}
			return true;
		} else {
			return false;
		}
	}

	private static String printBar(int len, int indeks) {
		if (indeks == len) {
			return "";
		} else {
			return "*" + printBar( len, indeks + 1 );
		}
	}

	private static int zbir(int doKogBroja) {
		if (doKogBroja == 0) {
			return 0;
		}

		return doKogBroja + zbir( doKogBroja - 1 );
	}

	private static String permutuj(List<String> niz) {
		if (niz.size() == 1) {
			return niz.get( 0 );
		} else {

		}

		return niz.get( 0 ) + permutuj( niz.subList( 1, niz.size() ) );
	}

}
