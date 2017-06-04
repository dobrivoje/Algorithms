package algs.examples.nested;

import java.util.Arrays;

public class test2 {

    static class IQ {

        int iq;
        String Name;

        public IQ() {
            this(150, "Deda Moca");
        }

        public IQ(int iq, String Name) {
            this.iq = iq;
            this.Name = Name;
        }

        public int getIq() {
            return iq;
        }

        public void setIq(int iq) {
            this.iq = iq;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        @Override
        public String toString() {
            return Name + ", IQ: " + iq;
        }

    }

    public static void main(String[] args) {
        IQ iq1 = new IQ(120, "Ljubo");
        IQ iq2 = new IQ();

        System.err.println("iq1 : " + iq1.toString());
        System.err.println("iq2 : " + iq2.toString());
        
        Class iqClass = IQ.class;

        System.err.println();
        try {
            IQ iqq = (IQ) iqClass.newInstance();
            iqq.setIq(160);
            iqq.setName("Dobri genije");
            System.out.println("kreirani objekat : " + iqq.getName() + " -> " + iqq.getIq());
            System.err.println("------------------------------");
            System.err.println("");
            System.err.println(Arrays.toString(iqClass.getConstructors()));
            System.err.println(Arrays.toString(iqClass.getDeclaredFields()));
            System.err.println(Arrays.toString(iqClass.getDeclaredMethods()));
            
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("greška !");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }

}
