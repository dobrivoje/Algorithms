package algs.examples.rootoptimization;

import java.util.List;

public class MyNode {

    private static int _cn;
    private final int id = _cn++;

    private String name;
    private List<MyNode> nextNodes;

    public MyNode(String name) {
        this.name = name;
    }

    private String nodeList() {
        if (nextNodes != null) {
            String n = "";

            for (MyNode nn : nextNodes) {
                n += nn.name + ", ";
            }

            return n.substring(0, n.length() - 2);
        }
        return "no nodes!";
    }

    //<editor-fold defaultstate="collapsed" desc="getters/setters">
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MyNode> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<MyNode> nextNodes) {
        this.nextNodes = nextNodes;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return name;
        // return name + " [" + nodeList() + "]";
    }

}
