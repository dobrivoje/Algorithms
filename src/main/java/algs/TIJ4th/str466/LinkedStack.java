package algs.TIJ4th.str466;

/**
 *
 * @author root
 */
public class LinkedStack<T> {

    private Node<T> top = new Node<>();

    public void push(T item) {
        top = new Node<>(item, top);
    }

    public T pop() {
        T result = top.item;

        if (!top.end()) {
            top = top.next;
        }

        return result;
    }

    private static class Node<U> {

        U item;
        Node<U> next;

        public Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        public Node() {
            this.item = null;
            this.next = null;
        }

        boolean end() {
            return item == null && next == null;
        }
    }

    public static void main(String[] args) {
        LinkedStack<String> LS = new LinkedStack<>();

        for (String s : "18.7.2016 radom sa počinje Prtenjak Dobrivoje ".split(" ")) {
            LS.push(s);
        }

        String ss = "";
        while ((ss = LS.pop()) != null) {
            System.out.println(ss + " ");
        }
    }
}
