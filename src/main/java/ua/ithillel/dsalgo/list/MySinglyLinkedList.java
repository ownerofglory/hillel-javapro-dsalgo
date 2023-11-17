package ua.ithillel.dsalgo.list;

import java.util.Iterator;

public class MySinglyLinkedList<T> implements MyList<T> {
    private Node head;
    private int size;

    public MySinglyLinkedList() {
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void add(T el) {
        Node newNode = new Node(el);

        // head -> (4) -> (3)
        //                  \ (1) -> *

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        Node curNode = head;
        Node prevNode = curNode;
        while (curNode != null) {
            prevNode = curNode;

            curNode = curNode.next;
        }

        prevNode.next = newNode;
        size++;
    }

    public void addToStart(T el) {
        Node newNode = new Node(el);

        newNode.next = head;
        head = newNode;
    }

    @Override
    public void set(int index, T el) {
        // head -> (4) -> (3) -> (2) -> *
        if (head == null || index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("List is empty or size exceeded");
        }

        int curIdx = 0;
        Node curNod = head;
        while (curNod != null) {
            if (curIdx == index) {
                curNod.value = el;
                return;
            }

            curNod = curNod.next;
            curIdx++;
        }

    }

    @Override
    public T get(int index) {
        if (head == null || index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("List is empty or size exceeded");
        }

        int curIdx = 0;

        Node curNode = head;
        while (curNode != null) {
            if (curIdx == index) {
                return curNode.value;
            }

            curNode = curNode.next;
            curIdx++;
        }

        throw new ArrayIndexOutOfBoundsException("List is empty");
    }

    @Override
    public T remove(int index) {
        // head -> (4) -> (3) -> (2) -> (8) -> *

        // head -> (4) -> (3) -> (8) -> *
        // (2) -> *
        if (head == null || index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("List is empty or size exceeded");
        }

        int curIdx = 0;
        Node curNode = head;
        Node prevNode = curNode;
        while (curNode != null) {
            if (curIdx == index) {
                prevNode.next = curNode.next;
                curNode.next = null;

                return curNode.value;
            }

            prevNode = curNode;
            curNode = curNode.next;
            curIdx++;
        }


        throw new ArrayIndexOutOfBoundsException("List is empty or size exceeded");
    }

    @Override
    public Iterator<T> iterator() {
        return new MySinglyLinkedListIterator();
    }

    private class MySinglyLinkedListIterator implements Iterator<T> {
        private Node curNode = head;

        @Override
        public boolean hasNext() {
            return curNode != null;
        }

        @Override
        public T next() {
            final T value = curNode.value;

            curNode = curNode.next;

            return value;
        }
    }

    private class Node {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" +
                    value +
                    ')';
        }
    }
}
