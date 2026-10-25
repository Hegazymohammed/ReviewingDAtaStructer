package org.dataStructer.arraylist;


import java.util.Objects;
import java.util.Optional;

public class ArrayList<T> {
    private T[] array;
    private int size;
    private int numberOfElements;




    public ArrayList(int size) {
        if (size < 0)
            size = 10;
        this.size = size;
        array = (T[]) new Object[size];
    }

    public void set(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Constatns.INVALID_INDEX);
        }
        array[index] = value;
        numberOfElements++;
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Constatns.INVALID_INDEX);
        }
        return array[index];

    }

    public void print() {

        for (int i = 1; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }



    public Optional<T> find(T value) {
        for (int i = 0; i < numberOfElements; i++) {
             if (Objects.equals(array[i], value)) {
                return Optional.of(array[i]);
             }

        }
        return Optional.empty();
    }

    public void add(T value) {
        if (this.arrayIsFull()) {
              expandCapacity();
        }
        array[numberOfElements++] = value;
    }

    public T getFront() {
        if (this.ArrayIsEmpty()) {
            throw new ArrayIndexOutOfBoundsException(Constatns.ARRAY_IS_EMPTY);
        }
        return array[0];
    }

    public T getBack() {
        if (numberOfElements == 0) {
            throw new ArrayIndexOutOfBoundsException(Constatns.ARRAY_IS_EMPTY);
        }
        return array[numberOfElements - 1];
    }

    private boolean ArrayIsEmpty() {
        return numberOfElements == 0;
    }

    private boolean arrayIsFull() {
        return this.size() >= size;
    }

    public int size(){
        return numberOfElements;
    }

    private void expandCapacity(){
          this.size=size*2;
          T[]newArray=(T[])new Object[size];
          for(int i=0;i<numberOfElements;i++){
              newArray[i]=array[i];
          }
          array=newArray;
    }

    public void insert(int index,T value){
           if(index<0)
               throw new IndexOutOfBoundsException(Constatns.INVALID_INDEX);
           if(index>size)
               expandCapacity();
           shiftRight(index);
           this.set(index,value);

    }

    private void shiftRight(int index){
        for(int i=numberOfElements;i>index;i--){
                    array[i]=array[i-1];
        }
    }

    public void rotateRight() {
        T temp=array[numberOfElements-1];
        for(int i=numberOfElements-1;i>0;i--)
                array[i]=array[i-1];
        array[0]=temp;
    }

    public void rotateLeft() {
        T temp=array[0];
        for (int i = 0; i <numberOfElements-1 ; i++) {
                array[i]=array[i+1];
        }
        array[numberOfElements-1]=temp;
    }

    public void rotateRightWithNSteps(int numberOfSteps) {
                numberOfSteps=numberOfSteps%size;
                for (int i = 0; i < numberOfSteps; i++) {
                    rotateRight();
                }
    }

    public T deleteIndex(int index) {
           if(index>numberOfElements)
               throw new IndexOutOfBoundsException(Constatns.INVALID_INDEX);
           T temp=array[index];
           shiftLeft(index);
           numberOfElements--;
           return temp;

    }

    public void shiftLeft(int startIndex){
        for (int i = startIndex-1; i <numberOfElements; i++) {
                   array[i]=array[i+1];
        }
    }

    public Optional<T> improvedSearch(T value){
          for(int i=0;i<numberOfElements;i++){
                if(array[i].equals(value)){
                     T temp=array[i];
                     array[i]=array[i-1];
                     array[i-1]=temp;
                     return Optional.of(array[i-1]);
                }
          }
          return Optional.empty();
    }


}
