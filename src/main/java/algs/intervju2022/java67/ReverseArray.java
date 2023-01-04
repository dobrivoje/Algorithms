package algs.intervju2022.java67;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <a href="https://javarevisited.blogspot.com/2015/03/how-to-reverse-array-in-place-in-java.html">...</a>
 */
public class ReverseArray {

	public static void main(String[] args) {
		String str = "1234567";
		char[] strAsChars = str.toCharArray();

		reverseArray( strAsChars );
		System.err.println( strAsChars );

		// Integer[] ints = new Integer[] { 5, 6, 7, 8 };
		int noOfElements = 100_000_000;
		Integer[] ints = IntStream.range( 1, noOfElements )/*.parallel()*/.boxed().toArray( Integer[]::new );

		reverseArray( ints );
		// System.err.println( Arrays.toString( Arrays.stream( ints ).toArray() ) );
	}

	public static void reverseArray(char[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		for (int i = 0; i < array.length / 2; i++) {
			char cur = array[i];
			array[i] = array[array.length - i - 1];
			array[array.length - i - 1] = cur;
		}
	}

	public static void reverseArray(Integer[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		for (int i = 0; i < array.length / 2; i++) {
			int cur = array[i];
			array[i] = array[array.length - i - 1];
			array[array.length - i - 1] = cur;
		}
	}
}
