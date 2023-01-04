package digital_ocean;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PedesetPitanjaIntervju {

	public static void main(String[] args) {
		// 1. How to reverse a String in Java?
		String ime = "Ana voli Milovana";
		String naopacke = naopacke( ime );
		System.err.println( naopacke );

		// 2. How to swap two numbers without using a third variable?
		int a = 10;
		int b = 20;
		int x = a + b;
		a = x - a;
		b = x - b;
		System.err.printf( "a=%d, b =%d", a, b );
		System.err.println();

		// 3. Java Program to check if a vowel is present in the string?
		boolean hasVowel = "qwrtzpsApp".toLowerCase().matches( ".*[aeiou].*" );
		System.err.println( "hasVowel ? " + hasVowel );

		// 5. Fibonacci Series using recursion
		System.err.println( "fibb( 4 ) = " + fibb( 4 ) );
		System.err.println( "fibb( 8 ) = " + fibb( 8 ) );

		// 7. Palindrome Check
		boolean pali = isPalindrome( "anavolimilovana" );
		System.err.println( "pali ? " + pali );

		// 8. How to remove Whitespaces from String
		String wsStr = "ja sam ja Jeremija     prezivam se Krstić";
		String wsStrNS = wsStr.replaceAll( "\\s+", " " );
		System.err.println( "wsStrNS = " + wsStrNS );

		// 9. How to remove leading and trailing whitespaces from a string?
		String ls1 = "    neki string.. i ostalo     ";
		System.err.println( ls1.replaceAll( "^\\s+|\\s+$", "" ) );

		// 10. Sorting an array in Java?
		int[] elements1 = new int[] { 6, 3, 89, 2, 69, 87, 2, 45, 99, 1, 2 };
		Arrays.sort( elements1 );
		System.err.println( Arrays.toString( elements1 ) );

		// 12. Find factorial of an integer?
		System.err.println( fact( 5 ) );

		// 13. Revese a Linked List
		LinkedList<Integer> rl = new LinkedList<>( Arrays.asList( 1, 2, 3, 4 ) );
		rl.forEach( System.err::println );
		System.err.println( "naopako1 : " );
		rl.descendingIterator().forEachRemaining( System.out::println );

		System.err.println( "napred : " );
		ListIterator<Integer> rlListIterator = rl.listIterator();
		while (rlListIterator.hasNext()) {
			System.err.println( rlListIterator.next() );
		}

		System.err.println( "naopako2 : " );
		while (rlListIterator.hasPrevious()) {
			System.err.println( rlListIterator.previous() );
		}

		// ok : removeFromList( "Java");
		removeFromList( "Complete" );
		removeFromList( "Pearls" );
		removeFromList( "Code" );

		// 14. How to implement Binary Search?
		Integer[] brojevi = IntStream.rangeClosed( 1, 100 ).boxed().toArray( Integer[]::new );
		int binRes1 = binarySearch( brojevi, 34 );
		System.err.println( "bin res 1 : " + binRes1 );
		int binRes2 = binarySearch( brojevi, 200 );
		System.err.println( "bin res 2 : " + binRes2 );
		int binRes3 = binarySearch( brojevi, 0 );
		System.err.println( "bin res 3 : " + binRes3 );
		int binRes4 = binarySearch( brojevi, 100 );
		System.err.println( "bin res 4 : " + binRes4 );

		// 15. Merge Sort in Java?
		MergeSortZadatak15 mergeSort = new MergeSortZadatak15();

		// 24. How to Sort HashMap by values?
		Map<String, Integer> scores = new LinkedHashMap<>();
		scores.put( "David", 95 );
		scores.put( "Jane", 80 );
		scores.put( "Mary", 97 );
		scores.put( "Lisa", 78 );
		scores.put( "Dino", 65 );

		List<Integer> scoreValues = scores.values().stream()
										  .sorted( Comparator.comparing( Integer::intValue )
															 .thenComparing( Integer::intValue ) )
										  .collect( Collectors.toList() );
		System.err.println( "scoreValues : " + scoreValues );

		List<Pair<String, Integer>> scoreValues2 =
				scores.entrySet().stream().map( e -> Pair.of( e.getKey(), e.getValue() ) )
					  .sorted( Comparator.comparing( Pair::getRight ) )
					  .collect( Collectors.toList() );
		System.err.println( "scoreValues : " );
		scoreValues2.forEach( System.out::println );

		System.err.println( "----------" );
		scores.entrySet().stream().sorted( Map.Entry.comparingByKey() ).forEach( System.out::println );
		System.err.println( "----2-----" );
		scores.entrySet().stream().sorted( Map.Entry.comparingByValue() ).forEach( System.out::println );
		Set<Integer> si1 = new TreeSet<>( scores.values() );
		System.err.println( "si1 = " + si1 );

		// 25. remove all occurrences of a given character from input String?
		String forReplacing = "abcdABCDabcdABCD";
		String replaced1 = forReplacing.replaceAll( "[aAdD]", "." );
		System.err.println( "replaced1 = " + replaced1 );

		// 26. How to get distinct characters and their count in a String?
		char[] forReplacingCharArray = "dobrivoje prtenjak".toCharArray();
		Map<Character, Integer> charStat = new LinkedHashMap<>();
		for (char c : forReplacingCharArray) {
			/*if (charStat.get( c ) == null) {
				charStat.put( c, 0 );
			} else {
				Integer val = charStat.get( c );
				if (val == null) {
					charStat.put( c, 1 );
				} else {
					charStat.put( c, val + 1 );
				}
			}*/

			charStat.merge( c, 1, Integer::sum );
		}
		System.err.println( "charStat : " + charStat );

		// 27. How to prove String is immutable programatically?
		String str1Imm = "123";
		String str2Imm = str1Imm;
		str1Imm = "1234";

		System.err.println( str1Imm == str2Imm );
		System.err.println( str2Imm );
	}

	public static int binarySearch(Integer[] array, int key) {
		return binarySearch( array, 0, array.length, key );
	}

	public static int binarySearch(Integer[] array, int low, int high, int key) {
		if (array == null || array.length == 0) {
			return -1;
		}

		int highNormalized = high - 1;

		while (low <= highNormalized) {
			int mid = (low + highNormalized) / 2;
			Integer arrVal = array[mid];

			if (arrVal == key) {
				return key;
			} else if (arrVal < key) {
				low = mid + 1;
			} else {
				highNormalized = mid - 1;
			}
		}

		return -1;
	}

	public static List<String> removeFromList(String whatToReplace) {
		List<String> listOfBooks = new ArrayList<>();
		listOfBooks.add( "Programming Pearls" );
		listOfBooks.add( "Clean Code" );
		listOfBooks.add( "Effective Java" );
		listOfBooks.add( "Code Complete" );

		System.err.println( "Before deleting : " + listOfBooks );

		// ovo je najbolje rešenje : radi remove na iteratoru !
		listOfBooks.removeIf( element -> element.contains( whatToReplace ) );

		// Concurrent modification exception !!
		// https://stackoverflow.com/questions/52689189/weird-java-concurrent-modification-exception-example
		// https://stackoverflow.com/questions/8189466/why-am-i-not-getting-a-java-util-concurrentmodificationexception-in-this-example/8189786#8189786
		/*for (String book : listOfBooks) {
			if (book.contains( whatToReplace )) {
				listOfBooks.remove( book );
			}
		}*/
		System.err.println( "After deleting : " + listOfBooks );

		return listOfBooks;
	}

	private static int fact(int n) {
		if (n < 2) {
			return 1;
		} else {
			return n * fact( n - 1 );
		}
	}

	private static boolean isPalindrome(String anavolimilovana) {
		return isPalindrome2( anavolimilovana );
	}

	private static boolean isPalindrome2(String anavolimilovana) {
		if (anavolimilovana.isEmpty() || anavolimilovana.length() == 1) {
			return true;
		}

		char prvi = anavolimilovana.charAt( 0 );
		char posl = anavolimilovana.charAt( anavolimilovana.length() - 1 );
		if (prvi != posl) {
			return false;
		}
		String dalje = anavolimilovana.substring( 1, anavolimilovana.length() - 1 );
		return isPalindrome2( dalje );
	}

	private static boolean isPalindrome(String anavolimilovana, int pos) {
		if (anavolimilovana.isEmpty()) {
			return true;
		}

		for (int i = 0; i < anavolimilovana.length() / 2; i++) {
			char prvi = anavolimilovana.charAt( i );
			char posl = anavolimilovana.charAt( anavolimilovana.length() - 1 - i );
			if (prvi != posl) {
				return false;
			}
		}

		return true;
	}

	private static int fibb(int n) {
		if (n < 2) {
			return 1;
		}
		return fibb( n - 1 ) + fibb( n - 2 );
	}

	private static String naopacke(String input) {
		if (input == null || input.isEmpty()) {
			return "";
		}
		return naopacke( input.substring( 1 ) ) + input.charAt( 0 );
	}
}
