package algs.J9DSA;

import algs.Util.Utils;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class SelectionSort141 {

    private static <E extends Comparable<E>> int findMin(E[] array, int start) {
        if (start == array.length - 1) {
            return start;
        }

        int restMinIndex = findMin(array, start + 1);
        E restMin = array[restMinIndex];
        if (restMin.compareTo(array[start]) < 0) {
            return restMinIndex;
        } else {
            return start;
        }
    }

    private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        if (i != j) {
            E temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static <E extends Comparable<E>> void selectionSort(E[] array, int start) {
        if (start >= array.length) {
            return;
        }

        int min = findMin(array, start);
        swap(array, start, min);
        selectionSort(array, start + 1);
    }

    private static <E extends Comparable<E>> void selectionSort(E[] array) {
        selectionSort(array, 0);
    }

    public static void main(String[] args) throws Exception {
        List<Integer> L = Utils.getRandomList(50000, 200);
        Integer[] IN = L.toArray(new Integer[0]);

        System.err.println("Unsorted array -> " + Arrays.toString(IN));
        long t0 = System.currentTimeMillis();
        System.err.println("time started at: " + t0);

        selectionSort(IN);

        long t1 = System.currentTimeMillis();
        System.err.println("time ended at: " + t1);

        System.err.println("Sorted array -> " + Arrays.toString(IN));
    }

}
