package algs.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Utils {

    public class MyMap<K, V> {

        public <K, V> Map<K, V> getMap() {
            return new HashMap<>();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Random Integer List Factory">
    public static int randomInt(int limit) {
        return (int) (limit * Math.random());
    }

    public static List<Integer> getRandomList(int limit, int capacity, boolean repeatAllowed) throws Exception {
        List<Integer> L = new ArrayList<>();

        if (capacity < 1) {
            throw new Exception("Capacity must be > 1 !");
        }

        if (limit < capacity) {
            throw new Exception("Upper limit must be lower than Capacity !");
        }

        if (!repeatAllowed) {
            int dup = 0;
            for (int i = 0; i < capacity; i++) {
                boolean goRepeat = false;

                do {
                    int nv = randomInt(limit);

                    if (L.contains(nv)) {
                        goRepeat = true;
                        System.err.println("Duplicate ! Value=" + nv + "#" + (++dup));
                    } else {
                        L.add(nv);
                        goRepeat = false;
                    }
                } while (goRepeat);
            }
        } else {
            for (int i = 0; i < capacity; i++) {
                L.add(randomInt(limit));
            }
        }

        return L;
    }

    public static List<Integer> getRandomList(int limit, int capacity) throws Exception {
        return Utils.getRandomList(limit, capacity, false);
    }

    public static List<Integer> getSortedRandomList(int limit, int capacity) throws Exception {
        List<Integer> L = getRandomList(limit, capacity, false);
        Collections.sort(L);

        return L;
    }

    public static void main(String[] args) {
        List<Integer> L;
        List<Integer> L2;

        try {
            L = getRandomList(10, 10);
            System.err.println(L);
        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        try {
            L2 = Utils.getRandomList(200, 50, true);
            System.err.println(L2);
        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
    //</editor-fold>

}
