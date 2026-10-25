package org.dataStructer.linkedList;

import org.dataStructer.arraylist.Constatns;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void test_Add_whenNoElement(){
        LinkedList<Integer> list=new LinkedList<Integer>();
        list.add(1);
        assertAll(()->{
            assertEquals(1,list.getHead().getData());
            assertEquals(1,list.getTail().getData());
        });
        list.add(2);
        assertAll(()->{
            assertEquals(1,list.getHead().getData());
            assertEquals(2,list.getTail().getData());
        });
        list.add(3);
        list.add(4);
        list.add(5);
        assertAll(()->{
            assertEquals(1,list.getHead().getData());
            assertEquals(5,list.getTail().getData());
        });

    }

    @Test
    void test_Add(){
        LinkedList<Integer> list=new LinkedList<>();
        list.add(1);
        list.add(9);
        list.add(3);
        list.add(411);
        assertAll(()->{
            assertEquals(1,list.getNode(1));
            assertEquals(9,list.getNode(2));
            assertEquals(411,list.getNode(4));
            assertEquals(3,list.getNode(3));
        });
    }

    @Test
    void test_add_when_throwsException(){
        LinkedList<Integer> list=new LinkedList<>();
        list.add(1);
        assertThrows(IllegalArgumentException.class,()->{list.getNode(0);}, Constatns.INDEX_OUT_OF_BOUNDS);
        assertThrows(IllegalArgumentException.class,()->{list.getNode(-1);},Constatns.INDEX_OUT_OF_BOUNDS);
    }

    @Test
    void test_search(){
        LinkedList<Integer> list=new LinkedList<>();
        list.add(1);
        list.add(9);
        assertAll(()->{
            assertEquals(1,list.search(1).getData());
            assertEquals(9,list.search(9).getData());
            assertNull(list.search(0));
            assertNull(list.search(-1) );
        });
    }
}