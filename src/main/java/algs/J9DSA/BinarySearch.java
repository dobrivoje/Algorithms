package algs.J9DSA;

import algs.Util.Utils;
import java.util.List;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class BinarySearch {

    private static <E extends Comparable<E>, F extends E> int binarySearch(E[] sortedValues, F valueToSearch) {
        return binarySearch(sortedValues, valueToSearch, 0, sortedValues.length - 1);
    }

    private static <E extends Comparable<E>, F extends E> int binarySearch(E[] sortedValues, F valueToSearch, int start, int end) {
        if (start >= end) {
            return -1;
        }
        int midIndex = (start + end) / 2;
        int comparasion = sortedValues[midIndex].compareTo(valueToSearch);

        if (comparasion == 0) {
            return midIndex;
        } else if (comparasion > 0) {
            return binarySearch(sortedValues, valueToSearch, start, midIndex);
        } else {
            return binarySearch(sortedValues, valueToSearch, midIndex + 1, end);
        }
    }

    public static void main(String[] args) throws Exception {
        List<Integer> L = Utils.getRandomList(10, 10);
        List<Integer> L2 = Utils.getRandomList(10, 10, false);

        long t0 = System.currentTimeMillis();
        System.err.println("time started!");
        List<Integer> L3 = Utils.getSortedRandomList(20000, 20000);
        System.err.println("time ended -> " + (System.currentTimeMillis() - t0));

        System.err.println("L: " + L);
        System.err.println("L2: " + L2);
        System.err.println("L3: " + L3);

        System.err.println("--------------");

        int testValue = L3.get(25);
        System.err.println("Binary search. Value= "
                + testValue
                + ", found ? " + (binarySearch(L3.toArray(new Integer[0]), testValue)));
    }

}
