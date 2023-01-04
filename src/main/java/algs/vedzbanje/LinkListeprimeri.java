package algs.vedzbanje;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class LinkListeprimeri {

	public static void main(String[] args) {
		LinkedList<Integer> LL = new LinkedList<>( Arrays.asList( 1, 2, 3, 4, 5 ) );

		int fromTheEnd = 0;
		Iterator<Integer> descIterator = LL.descendingIterator();
		while (descIterator.hasNext()) {
			Integer element = descIterator.next();
			// 3. s kraja, dakle 2 po indeksu
			if (fromTheEnd++ == 2) {
				System.err.println( element );
				break;
			}
		}

		Map<Character, Integer> m = new LinkedHashMap<>();
		String word = "abcAB".toLowerCase();
		for (char c : word.toCharArray()) {
			m.compute( c, (newCharFromArray, currOcc) ->
					newCharFromArray.equals( c ) ? currOcc == null /*|| currOcc == 0 */? 1 : currOcc + 1 : 0 );
		}

		System.err.println( m );
	}
}
