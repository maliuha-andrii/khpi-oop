package ua.khpi.oop.maliuha9_12;

import java.io.Serializable;

class Node<E> implements Serializable {
    private static final long serialVersionUID = 1L;
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
