package java9datastructs.and.algs.ch2;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    protected static class Node<E> {

        protected E value;
        protected Node next;

        @Override
        public String toString() {
            return value.toString();
        }

    }

    protected int len = 0;
    protected Node<E>[] lastModifiedNode;
    protected Node<E> first;
    protected Node<E> last;

    protected Node<E> getNewNode() {
        Node<E> node = new Node<>();
        lastModifiedNode = new Node[]{node};
        return node;
    }

    public Node<E> appendLast(E value) {
        Node n = getNewNode();
        n.value = value;

        if (last != null) {
            last.next = n;
        }
        last = n;

        if (first == null) {
            first = n;
        }

        len++;

        return n;
    }

    public Node<E> appendFirst(E value) {
        Node<E> n = getNewNode();
        n.value = value;
        n.next = first;
        first = n;
        if (len == 0) {
            last = n;
        }
        len++;

        return n;
    }

    public Node<E> insert(int index, E value) {
        if (index < 0 || index > len) {
            return null;
        } else if (index == 0) {
            return appendFirst(value);
        } else if (index == len) {
            return appendLast(value);
        } else {
            Node<E> result = first.next;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            Node<E> newNode = getNewNode();
            newNode.value = value;
            newNode.next = result.next;
            result.next = newNode;
            len++;

            return newNode;
        }
    }

    public Node<E> removeFirst() {
        if (first != null) {
            first = first.next;
        }
        len--;

        if (len == 0) {
            last = null;
        }

        return first;
    }

    public Node<E> removeAt(int index) {
        if (index < 0 || index >= len) {
            return null;
        } else if (index == 0) {
            Node<E> removedNode = first;
            removeFirst();
            return removedNode;
        } else {
            Node<E> prevNode = first;
            while (--index > 0) {
                prevNode = prevNode.next;
            }

            Node<E> nodeForRemoval = prevNode.next;
            Node<E> nextNode = nodeForRemoval.next;
            prevNode.next = nextNode;

            len--;
            return nodeForRemoval;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> n = first;

            @Override
            public boolean hasNext() {
                return n.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    return null;
                }

                Node<E> nextNode = n.next;
                n = nextNode.next;
                return nextNode.value;
            }
        };
    }
}
