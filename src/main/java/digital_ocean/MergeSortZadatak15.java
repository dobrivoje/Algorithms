package digital_ocean;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeSortZadatak15 {

	public static void main(String[] args) {
		List<Integer> elements = IntStream.rangeClosed( 20, 10_000_000 ).boxed().parallel().collect( Collectors.toList() );
		Collections.shuffle( elements );

		int[] arr = elements.parallelStream().mapToInt( e -> e ).filter( e -> e % 10 == 0 ).toArray();
		Integer[] arr2 = elements.parallelStream().filter( e -> e % 10 == 0 ).toArray( Integer[]::new );

		int[] merged = mergeSort( arr, 0, arr.length - 1 );
		System.err.println( "gotovo..." );
	}

	public static int[] mergeTwoSortedArrays(int[] one, int[] two) {
		int[] sorted = new int[one.length + two.length];

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < one.length && j < two.length) {
			sorted[k++] = one[i] < two[j] ? one[i++] : two[j++];
		}

		if (i == one.length) {
			while (j < two.length) {
				sorted[k++] = two[j++];
			}
		}

		if (j == two.length) {
			while (i < one.length) {
				sorted[k++] = one[i++];
			}
		}

		return sorted;
	}

	public static int[] mergeSort(int[] arr, int lo, int hi) {
		if (lo == hi) {
			int[] br = new int[1];
			br[0] = arr[lo];

			return br;
		}

		int mid = (lo + hi) / 2;

		int[] fh = mergeSort( arr, lo, mid );
		int[] sh = mergeSort( arr, mid + 1, hi );

		return mergeTwoSortedArrays( fh, sh );
	}

}
