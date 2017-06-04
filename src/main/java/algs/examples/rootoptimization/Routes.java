package algs.examples.rootoptimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Routes {

    private final List<List<MyNode>> allPaths;

    public Routes() {
        allPaths = new ArrayList<>();
    }

    public List<List<MyNode>> getAllPaths() {
        return allPaths;
    }

    /**
     * Metoda za izbacivanje svih putanja nekog čvora u grafu.
     *
     * @param node Posmatrani čvor
     * @param nodePath Putanja čvora
     */
    public void makePath(MyNode node, List<MyNode> nodePath) {
        if (nodePath == null) {
            nodePath = Arrays.asList(node);
        }

        if (node.getNextNodes() != null) {
            for (MyNode n1 : node.getNextNodes()) {
                if (!nodePath.contains(n1)) {
                    // List<MyNode> nList = new ArrayList<>(nodePath);
                    // nList.add(n1);
                    // makePath(n1, nList);
                    nodePath.add(n1);
                    makePath(n1, nodePath);
                }
            }
        }

        allPaths.add(nodePath);
    }
}
