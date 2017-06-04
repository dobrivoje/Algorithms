package algs.examples.rootoptimization;

import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) {
        MyNode a = new MyNode("A");
        MyNode b = new MyNode("B");
        MyNode c = new MyNode("C");
        MyNode d = new MyNode("D");
        MyNode e = new MyNode("E");
        MyNode f = new MyNode("F");
        MyNode g = new MyNode("G");

        a.setNextNodes(Arrays.asList(b, c));
        b.setNextNodes(Arrays.asList(a, e));
        c.setNextNodes(Arrays.asList(a, e));
        d.setNextNodes(Arrays.asList(f, e));
        e.setNextNodes(Arrays.asList(b, c, d, g));
        f.setNextNodes(Arrays.asList(d, g));
        g.setNextNodes(Arrays.asList(e, f));

        System.err.println("test1 :");
        System.err.println("------------------------------------------");
        List<MyNode> L = Arrays.asList(a, b, c, d, e, f, g);

        L.stream().forEach((n) -> {
            System.out.println(n);
        });

        System.err.println("");
        System.err.println("test2 :");
        System.err.println("------------------------------------------");

        Routes r = new Routes();
        List<MyNode> putanja = null;
        r.makePath(d, putanja);

        int i = 0;
        for (List<MyNode> LL : r.getAllPaths()) {
            for (MyNode m : LL) {
              System.err.println("Lista #" + (++i) + LL);  
            }
            
        }
    }

}
