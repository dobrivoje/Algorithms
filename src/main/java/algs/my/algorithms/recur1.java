package algs.my.algorithms;

/**
 *
 * @author root
 */
public class recur1 {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.err.println(fib(i));
//        }
        hanoj(3, "2.", "1.", "3.");
//        System.err.println(daLiJePalindrom("anavolimilovana"));
//        System.err.println(euklid(762134675, 2454));

//        permutation("1234");
    }

    public static long stepen(int baza, int stepen) {
        if (stepen == 0) {
            return 1;
        } else {
            return baza * stepen(baza, stepen - 1);
        }
    }

    public static long fib(int n) {
        if (n < 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static void hanoj(int n, String srednja, String levo, String desno) {
        if (n == 1) {
            System.err.println("pomeri sa " + srednja + ", na " + levo);
        } else {
            hanoj(n - 1, srednja, desno, levo);
            hanoj(1, srednja, levo, desno);
            hanoj(n - 1, desno, levo, srednja);
        }
    }

    public static boolean daLiJePalindrom(String rec) {
        if (rec.length() < 2) {
            return true;
        } else {
            System.err.println("[" + rec.substring(0, 1) + "]..[" + rec.substring(rec.length() - 1, rec.length()) + "]");
            System.err.println("{" + rec.substring(1, rec.length() - 1) + "}");

            return rec.substring(0, 1).equals(rec.substring(rec.length() - 1, rec.length()))
                    && daLiJePalindrom(rec.substring(1, rec.length() - 1));
        }
    }

    public static int euklid(int x, int y) {
        if (y == 0) {
            System.err.println("x:" + x + ", y:" + y);
            return x;
        }

        if (x >= y && y > 0) {
            System.err.println("x:" + x + ", y:" + y);
            return euklid(y, x % y);
        }

        System.err.println("x:" + x + ", y:" + y);
        return euklid(x, y);

    }

    public static String permutacija(String rec) {
        if (rec.length() == 1) {
            return rec;
        } else {
            for (char c : rec.toCharArray()) {
                return c + permutacija(ostatak(rec, c));
            }
        }

        return rec;
    }

    private static String ostatak(String rec, char c) {
        int ind = rec.indexOf(c);
        String s1 = rec.substring(0, ind);
        String s2 = rec.substring(ind + 1, rec.length());

        return s1 + s2;
    }

    public static void permutation(String str) {
        permutation("", str);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }
}
