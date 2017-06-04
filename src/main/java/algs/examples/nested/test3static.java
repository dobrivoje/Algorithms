package algs.examples.nested;

public class test3static {

    public static class Parent {

        public void m() throws Exception {
            System.err.println("parent m metod");
        }
    }

    public static class SubParent extends Parent {

        @Override
        public void m() {
            System.err.println("subparent m metod");
        }
    }

    static {
        System.err.println("izvršavanje programa !");
    }

    public static void main(String[] args) {
        //System.err.println("234");

        Parent p = new Parent();
        try {
            p.m();
        } catch (Exception e) {
        }

        Parent p2 = new SubParent();
        try {
            p2.m();
        } catch (Exception ex) {
        }
    }

}
