package org.dataStructer.linkedList;

import lombok.Getter;
import org.dataStructer.arraylist.Constatns;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;



    public void add(T data) {
        insertEnd(data);
    }

    public void insertEnd(T data) {
        Node<T> node = new Node<>(data);
        if (isEmpty())
            head = tail = node;
        else {
            tail.setNext(node);
            tail = node;
        }
        length++;
        dataIntegrity();
    }

    public void insertFront(T data) {
        Node<T> node = new Node<>(data);
        if (isEmpty())
            head = tail = node;
        else {
            node.setNext(head);
            head = node;
        }
        length++;
        dataIntegrity();
    }


    public Node<T> deleteFirst() {
        if (isEmpty())
            return null;
        Node<T> removed = head;
        if (head == tail)
            head = tail = null;
        else
            head = head.getNext();
        removed.setNext(null);
        length--;
        dataIntegrity();
        return removed;
    }

    public Optional<Node<T>> deleteFront() {
        return Optional.ofNullable(deleteFirst());
    }

    public Node<T> deleteLast() {
        if (isEmpty())
            return null;
        Node<T> removed = tail;
        if (head == tail)
            head = tail = null;
        else {
            Node<T> beforeTail = head;
            while (beforeTail.getNext() != tail)
                beforeTail = beforeTail.getNext();
            beforeTail.setNext(null);
            tail = beforeTail;
        }
        length--;
        dataIntegrity();
        return removed;
    }

    // ---------------------------- access ----------------------------

    /** index starts from 1 */
    public T getNode(int index) {
        if (index < 1 || index > length)
            throw new IllegalArgumentException(Constatns.INDEX_OUT_OF_BOUNDS);
        Node<T> current = head;
        for (int i = 1; i < index; i++)
            current = current.getNext();
        return current.getData();
    }

    public Node<T> search(T data) {
        for (Node<T> current = head; current != null; current = current.getNext())
            if (current.dataEquals(data))
                return current;
        return null;
    }


    public Node<T> getNthNodeBack(int position) {
        if (position < 1 || position > length)
            throw new RuntimeException(Constatns.INVALIDPOSITION);
        Node<T> current = head;
        for (int steps = length - position; steps > 0; steps--)
            current = current.getNext();
        return current;
    }

    /** position 1 is the tail, solved recursively without using length */
    public Node<T> getNthRecursive(int position) {
        if (position < 1)
            throw new RuntimeException(Constatns.INVALIDPOSITION);
        Object[] found = new Object[1];
        getNthRecursive(head, position, found);
        if (found[0] == null)
            throw new RuntimeException(Constatns.INVALIDPOSITION);
        @SuppressWarnings("unchecked")
        Node<T> result = (Node<T>) found[0];
        return result;
    }

    // returns the position of current counted from the end
    private int getNthRecursive(Node<T> current, int position, Object[] found) {
        if (current == null)
            return 0;
        int fromEnd = getNthRecursive(current.getNext(), position, found) + 1;
        if (fromEnd == position)
            found[0] = current;
        return fromEnd;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    // ---------------------------- print ----------------------------

    public void print() {
        for (Node<T> current = head; current != null; current = current.getNext())
            System.out.println(current.getData());
        System.out.println();
    }

    public void printRecursivlyReversed() {
        printRecursivlyReversed(head);
    }

    private void printRecursivlyReversed(Node<T> current) {
        if (current == null)
            return;
        printRecursivlyReversed(current.getNext());
        System.out.println(current.getData());
    }

    public void printRecursivelyIteratively() {
        printRecursivelyIteratively(head);
    }

    private void printRecursivelyIteratively(Node<T> current) {
        if (current == null) {
            System.out.println();
            return;
        }
        System.out.print(current.getData() + "  ");
        printRecursivelyIteratively(current.getNext());
    }

    // ---------------------------- integrity ----------------------------

    public void dataIntegrity() {
        if (isEmpty()) {
            if (head != null || tail != null)
                throw new IllegalStateException(Constatns.INConsistancyInLinkedList);
            return;
        }
        if (head == null || tail == null || tail.getNext() != null)
            throw new IllegalStateException(Constatns.INConsistancyInLinkedList);
        if (length == 1 && head != tail)
            throw new IllegalStateException(Constatns.INConsistancyInLinkedList);
        noCycle();
        verifyLength();
    }

    // Floyd's tortoise and hare: fast moves 2 steps, slow moves 1; they meet only if there is a cycle
    public void noCycle() {
        Node<T> slow = head, fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast)
                throw new IllegalStateException(Constatns.CYCLE_IN_LINKEDLIST);
        }
    }

    private void verifyLength() {
        int count = 0;
        Node<T> last = null;
        for (Node<T> current = head; current != null; current = current.getNext()) {
            count++;
            last = current;
        }
        if (count != length || last != tail)
            throw new IllegalStateException(Constatns.INConsistancyInLinkedList);
    }

    // ---------------------------- Object ----------------------------

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node<T> current = head; current != null; current = current.getNext())
            builder.append(current.getData()).append(" ");
        return builder.toString();
    }


    public Node deleteWithKey(T key){
                if(head==null)
                    return null;
            Node<T> current = head;
            if(head.getData().equals(key)){
                if(head.getNext() == null){
                    head=tail=null;
                    length--;
                    return current;
                }
                head=head.getNext();
                length--;
                return current;
            }
            Node<T>previous=null;
            while (current !=null){
                        if(current.getData().equals(key)){
                            if(current==tail){
                                tail=previous;
                            }
                            previous.setNext(current.getNext());
                            current.setNext(null);
                            length--;
                            break;
                        }
                        previous = current;
                        current = current.getNext();
            }

            return current;
    }

    public void swapPairs(){
         if(isEmpty())
             return;
        for(Node<T> current = head; current != null && current.getNext()!=null; current = current.getNext().getNext()){
              if(current.getNext()!=null){
                  swapPairs(current,current.getNext());
              }
        }
    }
    public void swapPairs(Node<T>current,Node<T>next){
          T temp=current.getData();
          current.setData(next.getData());
          next.setData(temp);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof LinkedList))
            return false;
        LinkedList<?> other = (LinkedList<?>) obj;
        if (length != other.length)
            return false;
        Node<?> b = other.head;
        for (Node<T> a = head; a != null; a = a.getNext(), b = b.getNext())
            if (!Objects.equals(a.getData(), b.getData()))
                return false;
        return true;
    }

    void reverse(){
        if(isEmpty()||length==1)
            return;
        tail=head;
        Node<T>prev=head;
        Node<T>refNext;
        head=head.getNext();
        while(head!=null){
            refNext=head.getNext();
             head.setNext(prev);
            prev=head;
            head=refNext;
        }
        tail.setNext(null);
        head=prev;


    }


    public void deleteEvenPosition(){
        if(isEmpty())
            return;
        int counter=1;
        for(Node<T> current = head,previous=null; current != null;counter++  ){
                    if(counter%2==0){
                        Node<T> next = current.getNext();
                        if(tail==current){
                            tail=previous;
                        }
                        previous.setNext(next);
                        current.setNext(null);
                        current=next;
                        length--;

                    }
                        else {
                        previous = current;
                        current = current.getNext();

                    }

        }
    }

    void insertToBeSorted(T value, Comparator<T>comparator){

               if(isEmpty()||comparator.compare(head.getData(),value)>=0){
                insertFront(value);
                 }
            else if(comparator.compare(tail.getData(),value)<=0){
                insertEnd(value);
            }
            else {
                for (Node<T> current = head, prev = null; current != null; prev = current, current = current.getNext()) {
                    if (comparator.compare(current.getData(), value) >= 0) {
                            insertBetween(prev,current,value);
                        break;
                    }
                }
            }
            dataIntegrity();
    }

    private void insertBetween(Node<T>previous,Node<T> current,T value){

        Node<T> newNode = new Node<>(value);
        previous.setNext(newNode);
        newNode.setNext(current);
        length++;
    }
    @Override
    public int hashCode() {
        int hash = 1;
        for (Node<T> current = head; current != null; current = current.getNext())
            hash = 31 * hash + Objects.hashCode(current.getData());
        return hash;
    }

    public void swapHeadAndTail(){
         if(isEmpty()||length==1)
             return;
         else if(length==2){
             tail.setNext(head);
             head.setNext(null);
             head=tail;
             tail=head.getNext();
         }
         else {
             Node<T> tailPrev = getTheBreviousNodeForTail();
             Node<T> headNext = head.getNext();
             swapTwoNode(tailPrev, headNext);
         }



    }
    private void swapTwoNode(Node<T>tailPrev,Node<T>headNext){
        tailPrev.setNext(head);
        head.setNext(null);
        tail.setNext(headNext);
        head=tail;
        tail=tailPrev.getNext();
    }
    private Node<T>getTheBreviousNodeForTail(){
        for(Node<T> current = head; current != null; current = current.getNext()){
            if(current.getNext()==tail){
                return current;
            }
        }
        return null;
    }

    public void leftRotate(int numberOfRotaion){
        if(numberOfRotaion<0)
            throw new IllegalArgumentException();

        if(isEmpty()||length==1)
            return;
        numberOfRotaion=numberOfRotaion%length;
        Node<T> headNext;
        for (int i = 0; i <numberOfRotaion ; i++) {
            headNext=head.getNext();
            tail.setNext(head);
            tail=head;
            tail.setNext(null);
            head=headNext;
        }
    }

    public void RemoveDuplicates(Node<T>head,Comparator<T> comparator){
            //time O(n2)
            //memeory O(n)
        if(isEmpty()||length<=1)
            return;

        for(Node<T> current = head; current != null; current = current.getNext()){

            for (Node<T>prev=current,next=current.getNext();next!=null;){
                if(comparator.compare(next.getData(),current.getData())==0){
                    if(next==tail){
                        tail=prev;
                    }
                    prev.setNext(next.getNext());
                    next.setNext(null);
                    next=prev.getNext();
                    length--;
                }
                else {
                    prev=next;
                    next=next.getNext();
                }
            }
        }

    }


    public void removeLastOcurrence(T key,Comparator<T> comparator){
        if( isEmpty())
            return  ;
        Node<T>lastOccurance=null;
        boolean exists=false;
        for(Node<T> current =  getHead(),prev=null; current != null;prev=current, current = current.getNext()){
                    if(comparator.compare(current.getData(),key)==0){
                        lastOccurance=prev;
                        exists=true;
                    }
        }
        if(lastOccurance==null&&exists){
            // this means the
            if( getLength()==1){
                  tail=null;
            }
             length--;
            head=head.getNext();
        }


              else if(exists){
                  if(lastOccurance.getNext()== tail)
                    tail=lastOccurance;
                    Node<T>deletedNode=lastOccurance.getNext();

                   lastOccurance.setNext(lastOccurance.getNext().getNext());
                   deletedNode.setNext(null);
                   length--;
               }
        }





   public void moveToBAck(T key,Comparator<T> comparator){
        if(isEmpty()||length<=1)
            return;
        Node<T>end=tail;
        for(Node<T> current = head,prev=null ; current != null&&current!=end;  ){
                    if(comparator.compare(current.getData(),key)==0){

                        current=moveToEnd(prev,current);
                    }
                    else{
                        prev=current;
                        current=current.getNext();

                    }

        }
   }

   private Node<T> moveToEnd(Node<T>prev,Node<T>current){
       Node<T> next=current.getNext();
       if(prev!=null)
           prev.setNext(next);
       if (current == head) {
           head=head.getNext();
         }
           tail.setNext(current);
           tail = current;

           current.setNext(null);
          return next;
   }

   public  T Max( Comparator<T> comparator){
        if(isEmpty() )
           return null;
        return recursiveMax(head,comparator);

   }

   public T recursiveMax(Node<T>current,Comparator<T> comparator){
           if(current==tail)
               return  tail.getData();
           T max=recursiveMax(current.getNext(),comparator);
           if(comparator.compare(current.getData(),max)>0){
                max=current.getData();
           }
           return max;
   }

   public void arrangeOddAndEven(){
        if(isEmpty()||length<=2)
            return;
        Node<T>headEven=null, tailEven=null;
        for(Node<T> current = head.getNext(),prev=head;current!=null;prev=current,current=current.getNext()){
                    Node<T> next=current.getNext();

                    if(tail==current){
                        tail=prev;
                    }
                    if(headEven==null){
                        headEven=tailEven=current;
                        current.setNext(null);
                    }
                    else{
                        tailEven.setNext(current);
                        tailEven=current;
                        tailEven.setNext(null);
                    }

                   prev.setNext(next);
                   current=next;
                   if(current==null)
                      break;

        }

        tail.setNext(headEven);
        tail=tailEven;
   }


}
