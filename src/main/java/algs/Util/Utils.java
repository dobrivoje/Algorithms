package algs.Util;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {

    public static class MyMap<K, V> {

        public Map<K, V> getMap() {
            return new HashMap<>();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Random Integer List Factory">
    public static int randomInt(int limit) {
        return (int) (limit * Math.random());
    }

    public static List<Integer> getInefficientRandomList(int limit, int capacity, boolean repeatAllowed, boolean showErrors) {
        List<Integer> L = new ArrayList<>();

        if (capacity < 1) {
            throw new RuntimeException("Capacity must be > 1 !");
        }

        if (limit < capacity) {
            throw new RuntimeException("Upper limit must be lower than Capacity !");
        }

        int dup = 0;
        if (!repeatAllowed) {
            for (int i = 0; i < capacity; i++) {
                boolean goRepeat = false;

                do {
                    int nv = randomInt(limit);
                    goRepeat = L.contains(nv);

                    if (L.contains(nv)) {
                        dup++;
                        if (showErrors) System.err.println("Duplicate ! Value=" + nv + "#" + (dup));
                    } else
                        L.add(nv);
                } while (goRepeat);
            }
        } else for (int i = 0; i < capacity; i++) L.add(randomInt(limit));

        if (!showErrors) System.err.println("Miss rate : " + dup + "/" + capacity);

        return L;
    }

    public static List<Integer> getInefficientRandomList(int limit, int capacity, boolean showErrors) {
        return Utils.getInefficientRandomList(limit, capacity, false, showErrors);
    }

    public static void getInefficientRandomList(int limit, int capacity, List<Integer> result, boolean showErrors) {
        result.addAll(Utils.getInefficientRandomList(limit, capacity, false, showErrors));
    }

    public static List<Integer> getSortedRandomList(int limit, int capacity, boolean showErrors) {
        List<Integer> L = getInefficientRandomList(limit, capacity, false, showErrors);
        Collections.sort(L);

        return L;
    }

    public static void getRandomList(int capacity, List<Integer> result) {
        result = IntStream.range(1, capacity + 1).boxed().collect(Collectors.toList());
        Collections.shuffle(result);
    }

    public static void main(String[] args) {
        List<Integer> L;
        List<Integer> L2;

        try {
            L = getInefficientRandomList(10, 10, false);
            System.err.println(L);
        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        try {
            L2 = Utils.getInefficientRandomList(200, 50, true, false);
            System.err.println(L2);
        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
    //</editor-fold>

    public static void executionTimeInUnitFor(Runnable task, String methodName, TimeUnit timeUnit) {
        long t0 = System.nanoTime();
        task.run();
        long dt = (System.nanoTime() - t0) / timeUnit.toNanos(1);
        System.err.println("Time spent on method [" + methodName + "] " + dt + " " + timeUnit.toString().toLowerCase());
    }

    public static void executionTime(Runnable task, String methodName) {
        long t0 = System.nanoTime();
        task.run();
        long dt = System.nanoTime() - t0;

        String timeStr;
        if (dt / 1_000 < 1) timeStr = " ns.";
        else if (dt / 1_000 >= 1 && dt / 1_000 < 999) {
            timeStr = " us.";
            dt /= 1000;
        } else if (dt / 1_000_000 >= 1 && dt / 1_000_000 < 999) {
            timeStr = " ms.";
            dt /= 1000_000;
        } else if (dt / 1_000_000_000 >= 1) {
            timeStr = " secs.";
            dt /= 1000_000_000;
        } else {
            long mins = TimeUnit.SECONDS.toMinutes(dt / 1000_000_000);
            timeStr = " more than " + mins + " seconds...";
        }

        System.err.println("Time spent on [" + methodName + "] " + dt + timeStr);
    }
}
