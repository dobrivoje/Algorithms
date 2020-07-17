package algs.J9DSA;

import algs.Util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
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

    public static void main(String[] args) throws InterruptedException {
        int noOfElements = 20_000;
        Utils.executionTime(() -> Utils.getInefficientRandomList(noOfElements, noOfElements, false, false), "Utils.getInefficientRandomList");

        List<Integer> L1 = new ArrayList<>();
        Utils.executionTime(() -> Utils.getRandomList(noOfElements, L1), "Utils.getRandomList");
        // System.err.println(Arrays.toString(L2.toArray()));

        Integer[] rndInts = L1.toArray(new Integer[0]);

        // System.err.println("Unsorted array -> " + Arrays.toString(IN));
        Supplier<Integer> s = () -> 12;
        Utils.executionTime(() -> selectionSort(rndInts), "selectionSort");
        // System.err.println("Sorted array -> " + Arrays.toString(IN));
    }
}
