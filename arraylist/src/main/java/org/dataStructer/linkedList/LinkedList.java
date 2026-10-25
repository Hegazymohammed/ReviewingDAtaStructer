package org.dataStructer.linkedList;

import lombok.Getter;
import lombok.Setter;
import org.dataStructer.arraylist.Constatns;

import java.util.Objects;

class Node <T>{
   private  T data;
   private Node next;


   public Node(T data){
       this.data=data;
   }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNext() {
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

@Setter
@Getter
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;
    public void add(T data){
        if(head==null){
            head=new Node(data);
            tail=head;
            incrementLength();
            return;
        }
        Node current=head;
        while (current.getNext()!=null)
            current=current.getNext();
        Node<T> newNode=new Node<T>(data);
        current.setNext(newNode);
        tail=newNode;
        incrementLength();

    }

    public T getNode(int index){
        if(index<1||index>length)
            throw new IllegalArgumentException(Constatns.INDEX_OUT_OF_BOUNDS);
        for(Node current=head;current!=null;current=current.getNext()){
            if(index==1)
                return (T) current.getData();
            index-=1;
        }
        return null;
    }

    public void print(){

        for(Node<T> current=head;current!=null;current=current.getNext()){
            System.out.println(current.getData());

        }
        System.out.println();
    }

    private void printRecursivlyReversed(Node<T> current){
        if(current==null)
            return;
        printRecursivlyReversed(current.getNext());
        System.out.println(current.getData());

    }

    public void printRecursivlyReversed( ){
        Node<T> current=head;
        printRecursivlyReversed(current);
    }

    public void printRecursivelyIteratively(){
        Node<T> current=head;
        printRecursivelyIteratively(current);
    }

    public void printRecursivelyIteratively(Node<T> current){
          if(current==null) {
              System.out.println();
              return;
          }
        System.out.print(current.getData()+"  ");
        printRecursivelyIteratively(current.getNext());
    }

    public Node search(T data){
        for(Node<T> current=head;current!=null;current=current.getNext()){
                    if(current.dataEquals(data))
                        return current;
        }
        return null;
    }

    private void incrementLength(){
        length++;
    }
}
