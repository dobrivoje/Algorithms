package java9datastructs.and.algs.ch2;

public class DoubleLinkedList<E> extends LinkedList<E> {

    protected static class DoubleNode<E> extends Node<E> {

        protected DoubleNode<E> prev;
    }

    @Override
    protected Node<E> getNewNode() {
        DoubleNode<E> n = new DoubleNode<>();
        lastModifiedNode = new DoubleNode[]{n};
        return n;
    }

    @Override
    public Node<E> appendFirst(E value) {
        Node<E> newNode = super.appendFirst(value);
        if (first.next != null) {
            ((DoubleNode<E>) newNode.next).prev = (DoubleNode<E>) first;
        }

        return newNode;
    }

    @Override
    public Node<E> appendLast(E value) {
        Node<E> newNode = super.appendLast(value);
        DoubleNode<E> origlast = (DoubleNode<E>) this.last;

        if (origlast == null) {
            origlast = (DoubleNode<E>) first;
        }
        ((DoubleNode<E>) this.last).next = (DoubleNode<E>) newNode;
        ((DoubleNode<E>) newNode).prev = origlast;

        return newNode;
    }

    @Override
    public Node<E> insert(int index, E value) {
        DoubleNode<E> newNode = (DoubleNode<E>) super.insert(index, value);
        if (index != 0 && index != len) {
            if (newNode.next != null) {
                newNode.prev = ((DoubleNode<E>) newNode.next).prev;
                ((DoubleNode<E>) newNode.next).prev = newNode;
            }
        }

        return newNode;
    }

    @Override
    public Node<E> removeFirst() {
        super.removeFirst();
        if (first != null) {
            ((DoubleNode<E>) first).prev = null;
        }

        return first;
    }

    @Override
    public Node<E> removeAt(int index) {
        DoubleNode<E> removedNode = (DoubleNode<E>) super.removeAt(index);
        DoubleNode<E> finalPrevNode = removedNode.prev;
        DoubleNode<E> finalNextNode = (DoubleNode<E>) removedNode.next;
        finalNextNode.next = finalNextNode;

        return removedNode;
    }
}
