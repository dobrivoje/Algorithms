package algs.TIJ4th.collections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public class Person implements Comparable<Person> {

    private String name;
    private int id;
    private Date dob;

    public Person(String name, int id, Date dob) {
        this.name = name;
        this.id = id;
        this.dob = dob;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        Person guest = (Person) other;

        return this.id == guest.id
                && this.name != null && this.name.equals(guest.name)
                && this.dob != null && this.dob.equals(guest.dob);
    }

    @Override
    public int hashCode() {
        int res = 0;

        res = 31 * res + id;
        res = 31 * res + (name != null ? name.hashCode() : 0);
        res = 31 * res + (dob != null ? dob.hashCode() : 0);

        return res;
    }

    @Override
    public int compareTo(Person other) {
        return this.id - other.id;
    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
        String origString = "Dobri";
        String reversedStr;
        System.out.println("Original String: " + origString);

        reversedStr = obrniNaopako(origString);
        System.out.println("Reverse String : " + reversedStr);
        //
        /*int n1 = 3, n2 = 7;
        for (int slucajniBroj = n1 + n2; slucajniBroj < 1001; slucajniBroj++) {
        if (!sastaviIznos(slucajniBroj, false, n1, n2)) {
        System.err.println("Broj " + slucajniBroj + ", ne može se sastaviti od " + n1 + " i " + n2);
        }
        }
         */
        //System.err.println(printBar(5, 0));
    }

    public static String obrniNaopako(String str) {
        if (str.length() < 2) {
            return str;
        } else {
            return obrniNaopako(str.substring(1)) + str.charAt(0);
        }
    }

    public static boolean sastaviIznos(int koliko, boolean stampa, int novcic1, int novcic2) {
        if (koliko == 0) {
            //System.err.println("Gotovo !");
            return true;
        } else if (koliko >= novcic1 && sastaviIznos(koliko - novcic1, stampa, novcic1, novcic2)) {
            if (stampa) {
                System.err.println("Jedan novčić od " + novcic1);
            }
            return true;
        } else if (koliko >= novcic2 && sastaviIznos(koliko - novcic2, stampa, novcic1, novcic2)) {
            if (stampa) {
                System.err.println("Jedan novčić od " + novcic2);
            }
            return true;
        } else {
            return false;
        }
    }

    private static String printBar(int len, int indeks) {
        if (indeks == len) {
            return "";
        } else {
            return "*" + printBar(len, indeks + 1);
        }
    }

    private static int zbir(int doKogBroja) {
        if (doKogBroja == 0) {
            return 0;
        }

        return doKogBroja + zbir(doKogBroja - 1);
    }

    private static String permutuj(List<String> niz) {
        if (niz.size() == 1) {
            return niz.get(0);
        } else {

        }

        return niz.get(0) + permutuj(niz.subList(1, niz.size()));
    }

}
