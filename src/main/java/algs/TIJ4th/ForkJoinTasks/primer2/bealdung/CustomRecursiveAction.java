package algs.TIJ4th.ForkJoinTasks.primer2.bealdung;

import algs.Util.Utils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomRecursiveAction extends RecursiveAction {

    private static final Logger logger      = Logger.getAnonymousLogger();
    final                String workload;
    final                int    THRESHHOLD  = 8;
    private static final int    SUBTASKS_NO = 2;

    private final Random r = new Random();

    public CustomRecursiveAction(String workload) {
        this.workload = workload;
    }

    private static void accept(String req) {
        throw new IllegalArgumentException();
    }

    @Override
    protected void compute() {
        if (workload.length() <= THRESHHOLD) {
            try {
                process(workload, this::processor);
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        } else
            ForkJoinTask.invokeAll(createSubTasks());
    }

    public void process(String work) {
        try {
            int workTime = 1000 + r.nextInt(1000);
            TimeUnit.MILLISECONDS.sleep(workTime);
            logger.info("[" + Thread.currentThread().getName() + "][" + workTime + "] ms.");

            process(work, this::processor);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void process(String work, Function<String, String> processor) throws Exception {
//        if (!r.nextBoolean())
//            throw new RuntimeException("data is NOT processed.                    details " + work);

        String result = processor.apply(work);
        logger.info("[" + Thread.currentThread().getName() + "] : ///// " + work + " -> " + result);
    }

    private List<CustomRecursiveAction> createSubTasks() {
        List<CustomRecursiveAction> tasks = new ArrayList<>();

        for (Pair<Integer, Integer> P : exy(workload.length(), SUBTASKS_NO)) {
            String substring = workload.substring(P.getLeft(), P.getRight());
            tasks.add(new CustomRecursiveAction(substring));
        }

        return tasks;
    }

    private String processor(String input) {
        //<editor-fold desc="Simulate time consuming task...">
        try {
            TimeUnit.MILLISECONDS.sleep(3000 + r.nextInt(2000));
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        //</editor-fold>

        return r.nextBoolean() ? input.toUpperCase() : input.toLowerCase();
    }

    public static void exa(int lenght, int subSteps) {
        int PARTS_NO = lenght / subSteps;
        int REMINDER = lenght % subSteps;

        for (int i = 0; i < lenght; i++) {
            if (i % subSteps == 0) {
                int endInd = i + subSteps - 1;

                System.err.println("s/e: " + i + " - " + endInd);
                System.err.println();
            }
        }
    }

    public static List<Pair<Integer, Integer>> exy(int lenght, int divideNo) {
        List<Pair<Integer, Integer>> indexes = new ArrayList<>();

        int partsNo = lenght / divideNo;
        int rem = lenght % divideNo;
        int si = 0;
        int ei;
        do {
            ei = si + partsNo - 1;
            indexes.add(Pair.of(si, ei));
            si += partsNo;
        } while (si + rem < lenght);
        if (rem != 0) indexes.add(Pair.of(si, lenght - 1));

        return indexes;
    }

    private static Consumer<String> getDefaultValue() {
        Consumer<String> defaultValue = s -> System.err.println("No  !");
        return defaultValue;
    }

    static void execute(String param) {
        System.err.println(">>> do somethin with " + param);
    }

    public static void main(String[] args) {
        Map<String, Consumer<String>> handlers = new HashMap<>();
        handlers.put("A", CustomRecursiveAction::execute);
        handlers.put("B", CustomRecursiveAction::execute);
        handlers.put("C", CustomRecursiveAction::execute);

        handlers.getOrDefault("A", getDefaultValue()).accept("opcija A");
        handlers.getOrDefault("a", getDefaultValue()).accept("opcija B");
        handlers.getOrDefault("C", getDefaultValue()).accept("opcija C !");

        /*
        String address = "Charter Row, Sheffield, South Yorkshire, S1 3EF";
        int lastIndex = address.lastIndexOf(',');
        Pattern pattern = Pattern.compile("^[A-Za-z]{1,2}[0-9Rr][0-9A-Za-z]?\\s*\\d[A-Za-z]{2}$");
        String trimmed = address.substring(lastIndex + 1).trim();
        Matcher matcher = pattern.matcher(trimmed);
        if (matcher.find()) {
            System.err.println(matcher.group(0));
        }
        */

        /*
        double time = Double.parseDouble("19288.98");
        double hours = time / 3600;
//        hours = Math.floor(hours);
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.FLOOR);
//        String res = hours == 0.00 ? "0.00" : String.format("%.2f",(hours)) ;
        String res = hours == 0.00 ? "0.00" : df.format(hours);
        System.err.println(res);

        System.exit(0);
        */

        //<editor-fold desc="forkjoin...">
        ForkJoinPool pool = ForkJoinPool.commonPool();

        String input = "1234abcd5678xyqzBFPQXYcvbnklmN";
        System.err.println("************************************");
        System.err.println();
        System.err.println("input.length() = " + input.length());
        System.err.println("--------");
        System.err.println("ForkJoinPool.getParallelism()  = " + ForkJoinPool.getCommonPoolParallelism());
        System.err.println();
        System.err.println("************************************");
        Utils.executionTime(() -> pool.invoke(new CustomRecursiveAction(input)), "ForkJoin execution");

        System.err.println();
        System.err.println();
        System.err.println();

        System.err.println("************************************");
        System.err.println("Linear");
        System.err.println("************************************");

        Utils.executionTime(() -> {
            CustomRecursiveAction lineartask = new CustomRecursiveAction(input);
            lineartask.process(input);

        }, "Linear execution");
        //</editor-fold>

//        exa(11, 3);
//        List<Pair<Integer, Integer>> indexes = exy(11, 2);
//        System.err.println(indexes);

        /*
        for (int m = 'a', v = 'A'; m <= 'z' && v <= 'Z'; v++, m++)
            System.err.println("[" + m + "]" + (char) m + "       " + ("[" + v + "]" + (char) v));
        */
    }
}
