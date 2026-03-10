package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            // TODO
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        // TODO
        return size;
    }

    //@Override
    public boolean isEmpty() {
        // TODO
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public E get(int position) {
        // TODO
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node <E> node = head;

        for (int i = 0; i < position; i++) {
            node = node.getNext();
        }
        return node.getElement();
    }

    @Override
    public void add(int position, E e) {
        // TODO
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException();
        }

        if (position == 0) {
            head = new Node<>(e, head);
        } else {
            Node<E> node = head;

            for (int i = 0; i < position - 1; i++) {
                node  = node.getNext();
            }
            Node<E> newNode = new Node<>(e, node.getNext());
            node.setNext(newNode);
        }
        size++;
    }


    @Override
    public void addFirst(E e) {
        // TODO
        head = new Node<E>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        Node<E> newest = new Node<E>(e, null);
        Node<E> last = head;
        if (last == null) {
            head = newest;
        }
        else {
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newest);
        }
        size++;
    }

    @Override
    public E remove(int position) {
        // TODO
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (position == 0) {
            return removeFirst();
        }

        Node<E> node = head;
        for (int i = 0; i < position - 1; i++) {
            node = node.getNext();
        }

        Node<E> element = node.getNext();
        E val = element.getElement();
        node.setNext(null);
        size--;

        return val;
    }

    @Override
    public E removeFirst() {
        // TODO
        if (head == null) {
            return null;
        }
        E element = head.getElement();
        head = head.getNext();
        size--;

        return element;
    }

    @Override
    public E removeLast() {
        // TODO
        if (head == null) {
            return null;
        }

        if (head.getNext() == null) {
            E element = head.getElement();
            head = null;
            size--;

            return element;
        }

        Node<E> node = head;
        // loop until second last element reached
        for (int i = 0; i < size - 2; i++) {
            node = node.getNext();
        }
        Node<E> element = node.getNext();
        E removed = element.getElement();
        node.setNext(element.getNext());

        size--;

        return removed;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public void reverse() {
        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> next;
        while(curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
        Node<E> tmp = head;
        while (tmp != null) {
            twin.addLast(tmp.getElement());
            tmp = tmp.next;
        }
        return twin;
    }

    // copy a linked list using recursion
    public SinglyLinkedList<E> recursiveCopy() {
        SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
        recursiveCopyHelper(head, twin);
        return twin;
    }

    // helper for recursiveCopy
    private void recursiveCopyHelper(Node<E> current, SinglyLinkedList<E> newList) {
        // base case (reached end of original list)
        if (current == null) {
            return;
        }

        // add current element and move to the next
        newList.addLast(current.getElement());
        recursiveCopyHelper(current.getNext(), newList);
    }

    public SinglyLinkedList<E> sortedMerge(SinglyLinkedList<E> other) {

        SinglyLinkedList<E> result = new SinglyLinkedList<>();

        Node<E> l1 = this.head;
        Node<E> l2 = other.head;

        while (l1 != null && l2 != null) {
            // take smaller element and add it to result
            // then move to next element in list
            if (((Comparable<E>) l1.getElement()).compareTo(l2.getElement()) <= 0) {
                result.addLast(l1.getElement());
                l1 = l1.getNext();
            } else {
                result.addLast(l2.getElement());
                l2 = l2.getNext();
            }
        }

        // add leftover elements from list 1
        while (l1 != null) {
            result.addLast(l1.getElement());
            l1 = l1.getNext();
        }

        // add leftover elements from list 2
        while (l2 != null) {
            result.addLast(l2.getElement());
            l2 = l2.getNext();
        }

        return result;
    }


    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

        System.out.println("\noriginal list: " + ll);
        ll.reverse();
        System.out.println("reversed list: " + ll);

        SinglyLinkedList<Integer> ll2 = ll.copy();
        System.out.println("copied list: " + ll2);

        SinglyLinkedList<Integer> ll3 = ll.recursiveCopy();
        System.out.println("recursively copied list: " + ll3);

        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<Integer>();
        l1.addLast(2);
        l1.addLast(6);
        l1.addLast(20);
        l1.addLast(24);

        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<Integer>();
        l2.addLast(1);
        l2.addLast(3);
        l2.addLast(5);
        l2.addLast(8);
        l2.addLast(12);
        l2.addLast(19);
        l2.addLast(25);

        SinglyLinkedList<Integer> result = l1.sortedMerge(l2);

        System.out.println("\nlist 1: " + l1);
        System.out.printf("list 2: " + l2);
        System.out.printf("\nmerged list: " + result);

    }
}
