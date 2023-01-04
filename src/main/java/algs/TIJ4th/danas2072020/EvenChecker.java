package algs.TIJ4th.danas2072020;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EvenChecker implements Runnable {

    private final IntGenerator generator;
    private final int          id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.err.println("Nije paran broj ! Broj je : " + val);
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count) {
        System.out.println("Press ctrl+C to stop.");
        ExecutorService E = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            E.execute(new EvenChecker(generator, i));
        }

        E.shutdown();
    }

    public static void test(IntGenerator gp) {
        test(gp, 10000);
    }

    static class Dobri {

        Integer       x;
        List<Integer> children = new ArrayList<>();

        public Dobri(Integer x, List<Integer> children) {
            this.x = x;
            this.children = children;
        }

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public List<Integer> getChildren() {
            return children;
        }

        public void setChildren(List<Integer> children) {
            this.children = children;
        }
    }

    public static void main(String[] args) {
        List<Dobri> lista = Arrays.asList(
            new Dobri(3, Arrays.asList(1, 2, 3))
            , new Dobri(5, Arrays.asList(3, 5, 7, 11, 13))
            , new Dobri(4, Arrays.asList(2, 5, 8, 15))
        );

        Integer reduceSize = lista.stream().map(Dobri::getChildren).map(List::size).reduce(0, Integer::sum);
        System.err.println("reduceSize = " + reduceSize);

        List<List<Integer>> reduceSize2 = lista.stream().map(Dobri::getChildren).collect(Collectors.toList());
        int sum = reduceSize2.stream().mapToInt(L -> L.stream().reduce(0, Integer::sum)).sum();

        System.err.println("reduceSize = " + sum);

        List<List<Integer>> reduceSize3 = lista.stream().map(Dobri::getChildren).collect(Collectors.toList());
        List<Integer> allInOne = reduceSize3.stream().reduce(new ArrayList<>(), (result, listForAddition) -> {
            result.addAll(listForAddition);
            return result;
        });

        System.err.println(allInOne);

        System.err.println("============== johnny brat zvani frizura gaće same spadaju ==============");
        Set<Integer> bazaSet = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> bazaSetCopy = new HashSet<>(bazaSet);

        Set<Integer> zahtev1Set = new HashSet<>(Arrays.asList(1, 3, 6));
        Set<Integer> zahtev1SetCopy = new HashSet<>(zahtev1Set);

        bazaSet.addAll(zahtev1Set);
        bazaSetCopy.removeAll(zahtev1SetCopy);
        bazaSet.retainAll(zahtev1SetCopy);

        System.err.println("rezultat : " + bazaSet);

        String x = "slavica djukic dejanovic";
        x = x.substring(0, x.indexOf(" "));
        System.err.println(x + "length ok ? " + ("slavica".length() == x.length()) + ": " + "slavica".equals(x));

        System.err.println("------------");

        String[] primer1 = unsafe("seed1", "seed2", "seed3"); // ClassCastException
        System.err.println("primer1 : " + Arrays.toString(primer1));

        Object[] primer2 = unsafe("seed1", new Integer(22), null, new IntGenerator.ObjHolder<>(7)); // ClassCastException
        System.err.println("primer2 : " + Arrays.toString(primer2));

        try {
            String[] plants = broken("seed"); // ClassCastException
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(10, "Aman");
        map.put(20, "Suraj");
        map.put(30, "Harsh");

        System.out.println("HashMap:\n" + map.toString());

        String new40 = map.computeIfAbsent(40, k -> "Sanjeet");
        String new10 = map.computeIfPresent(10, (price, title) -> "new " + title);
        String new50 = map.compute(50, (price, title) -> (title == null ? "no prev. price " : title) + "nov 50");
        System.err.println("*************************");
        System.err.println("new10, 40, 50: " + new10 + ", " + new40 + ", " + new50);
        System.err.println("*************************");

        System.out.println("New HashMap:\n" + map);

        int a1 = 123456;
        int a2 = 700222;
        int xor = a1 ^ a2;
        System.err.println("xor = " + xor);
        int newA1 = a2 ^ xor;
        int newA2 = a1 ^ xor;
        System.err.println("new A1, A2 = " + newA1 + ", " + newA2);

        byte[] s1 = "123456".getBytes();
        byte[] s2 = "700222".getBytes();
        byte[] bytesEncodedS1 = Base64.getEncoder().encode(s1);
        byte[] bytesDecodedS1 = Base64.getDecoder().decode(bytesEncodedS1);
        System.err.println("bytes s1 -> " + new String(bytesEncodedS1));
        System.err.println("decoded s1 -> " + new String(bytesDecodedS1));

        int[] bXor = new int[Math.max(s1.length, s2.length)];
        for (int i = 0; i < s1.length; i++) {
            int tmp = s1[i] ^ s2[i];
            bXor[i] = (byte) tmp;
        }

        StringBuilder sb = new StringBuilder();
        byte[] s1Res = new byte[s1.length];
        for (int i = 0; i < s1.length; i++) {
            int tmp = s2[i] ^ bXor[i];
            s1Res[i] = (byte) tmp;
        }
        System.err.println(Base64.getDecoder().decode(s1Res) );
    }

    public static <T> T[] unsafe(T... elements) {
        return elements; // unsafe! don't ever return a parameterized varargs array
    }

    public static <T> T[] broken(T seed) {
        T[] plant = unsafe(seed, seed, seed); // broken! This will be an Object[] no matter what T is
        return plant;
    }
}
