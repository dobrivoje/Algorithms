package algs.TIJ4th.BeanInfo;

import java.util.*;

public class DLL {

    Node head;

    public void push(int newData) {
        Node newNode = new Node(newData);
        newNode.next = head;
        newNode.prev = null;
        if (head != null)
            head.prev = newNode;

        head = newNode;
    }

    public void insertAfter(Node prevNode, int newData) {
        Node newNode = new Node(newData);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        newNode.prev = prevNode;
        if (newNode.next != null)
            newNode.next.prev = newNode;
    }

    public static class Node {

        int  data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }

        public Node(int data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "data=" + data + '}';
        }
    }

    public static void printAllStartingWithNode(Node node) {
        for (Node n = node; n != null; n = n.next) {
            System.err.println(n);
        }
    }

    public static void main(String[] args) {
        DLL dll = new DLL();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        dll.head = node1;

        node1.next = node2;
        node2.next = node3;
        dll.push(55);
        dll.insertAfter(node2, 711);
        dll.push(1);
        dll.push(0);

        printAllStartingWithNode(dll.head);

        int n1 = node1.hashCode();
        int n2 = node1.hashCode() ^ node3.hashCode();
        int n3 = node3.hashCode();
        System.err.println(n1);
        System.err.println(n2);
        System.err.println(n3);
        System.err.println("--------");
        int n1rev = n2 ^ n3;
        System.err.println("n1rev = " + n1rev);

        Comparator<Integer> c = Comparator.comparingInt(o -> o);
        List<Integer> l= Arrays.asList(3,1,2);
        l.sort(Comparator.comparingInt(o -> o));
        System.err.println(l);
    }
}
