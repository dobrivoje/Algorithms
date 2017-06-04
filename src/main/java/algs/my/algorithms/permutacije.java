package algs.my.algorithms;

import java.util.ArrayList;
import java.util.List;

public class permutacije {

    public static void main(String[] args) throws java.lang.Exception {
        List<String> list = permutations("123");
        for (String s : list) {
            System.out.println(s);
        }
    }

    static List<String> permutations(String s) {
        List<String> result = new ArrayList<>();
        permutation(s.toCharArray(), 0, result);
        return result;
    }

    public static void permutation(char[] arr, int pos, List<String> result) {
        if (arr.length - pos == 1) {
            result.add(new String(arr));
        } else {
            for (int i = pos; i < arr.length; i++) {
                swap(arr, pos, i);
                permutation(arr, pos + 1, result);
                swap(arr, pos, i);
            }
        }
    }

    public static void swap(char[] arr, int pos1, int pos2) {
        char h = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = h;
    }

}
