package org.dataStructer.linkedList;

import java.util.Objects;

class Node<T> {
    private T data;
    private Node next;


    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public boolean dataEquals(T data) {
        return Objects.equals(this.data, data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data);
    }
}
