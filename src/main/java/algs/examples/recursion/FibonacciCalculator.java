package algs.examples.recursion;

import java.math.BigInteger;
import java.util.Formatter;
import java.util.Scanner;

public class FibonacciCalculator {

    private static long FIBONACCI;

    public static int fibonacci1(long number) {
        if (number < 3) {
            return 1;
        }

        return fibonacci1(number - 1) + fibonacci1(number - 2);
    }

    public long fibonacci2(long number) {
        long f1 = 1, f2 = 1, fibonacci = 1;
        Formatter f = new Formatter(System.out);

        if (number < 3) {
            return 1;

        } else {
            for (long i = 3; i <= number; i++) {
                fibonacci = f1 + f2;

                // f.format("%10.2f", fibonacci/f2);
                // System.out.println(fibonacci/f2);
                double k = fibonacci / f2;
                System.out.printf("%10.5f", k);

                f1 = f2;
                f2 = fibonacci;
            }

            FIBONACCI = fibonacci;
            return fibonacci;
        }
    }

    public long getFibRes() {
        return FIBONACCI;
    }

    public static long lucasNumber(long number) {
        int f1 = 2, f2 = 1;
        double lucas = 3;

        for (long i = 3; i <= number; i++) {
            lucas = f1 + f2;

            f1 = f2;
            f2 = (int) lucas;

            System.err.println("[" + f2 + ", " + f1 + "]");
            System.err.printf("%2.18f", (lucas / f1));
        }

        return (int) lucas;
    }

    public static BigInteger fibonacci3(BigInteger number) {
        BigInteger F1 = BigInteger.ONE, F2 = BigInteger.ONE, FIB = BigInteger.ONE;

        if (number.compareTo(BigInteger.valueOf(3)) < 0) {
            return BigInteger.ONE;

        } else {
            for (BigInteger i = BigInteger.valueOf(3L); i.compareTo(number) < 0; i.add(BigInteger.ONE)) {
                FIB = F1.add(F2);
                F1 = F2;
                F2 = FIB;
            }

            return FIB;
        }
    }

    public static Integer dajRandom() {
        try {
            for (int i = 0; i < 5; i++) {
                if (i%2==0) throw new Exception("666");
                else
                    return 223;
            }
        } catch (Exception e) {
            System.err.println(">>> greška : "+e.getMessage() );
        }

        return 1117;
    }

    public static void main(String args[]) {
        int xx = dajRandom();
        System.err.println("main >>> "+xx);

        System.err.format("%5.3f", (double) 20_633_239 / 12_752_043);
        System.err.println();

        System.out.print("Unesite gornju granicu Fibonacci serije : ");
        int number = new Scanner(System.in).nextInt();

        System.out.println("Fibonacci serija do granice " + number + " je : ");
        FibonacciCalculator fc = new FibonacciCalculator();

        for (long i = 3; i <= number; i++) {
            // System.out.print(fibonacci3(BigInteger.valueOf(i)) + " ");
            // System.out.print(lucasNumber(i) + " ");
            System.out.print(FibonacciCalculator.lucasNumber(i) + " ");
        }
    }
}
