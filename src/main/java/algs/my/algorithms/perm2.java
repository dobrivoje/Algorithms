package algs.my.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author root
 */
public class perm2 {

    public static void main(String[] args) {
        List<String> L = Arrays.asList("1", "2", "3", "4", "5");
        
        // System.err.println(getPermSubList(L));

        System.out.println(Perm(L));
        //System.out.println(getPermSubList(L));
    }

    private static String Perm(List<String> elem) {

        String ret = "";

        if (elem.size() == 1) {
            ret = elem.get(0);
        } else {
            int ind = 0;
            for (String e : elem) {
                ret = e + Perm(getPermSubList(elem).get(ind++));
            }
        }

        return ret;

    }

    private static Map<Integer, List<String>> getPermSubList(List<String> elem) {
        Map<Integer, List<String>> R = new HashMap<>();

        int i = 0;
        for (String e : elem) {
            List<String> permSubList = new ArrayList<>(elem);
            permSubList.remove(e);

            // R.add(e + "[" + (permSubList) + "]");
            R.put(i++, permSubList);
        }

        return R;
    }

}
