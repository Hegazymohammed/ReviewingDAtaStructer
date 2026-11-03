package org.dataStructer.linkedList;

import org.dataStructer.arraylist.Constatns;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

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

    @Test
    @DisplayName("test insert end")
    void test_insertEnd(){
        LinkedList<Integer> list=new LinkedList<>();
        list.insertEnd(1);
        assertAll(()->{
            assertEquals(1,list.getHead().getData());
            assertEquals(1,list.getTail().getData());
            assertEquals(1,list.getLength());
        });
        list.insertEnd(2);
        assertAll(()->{
            assertEquals(1,list.getHead().getData());
            assertEquals(2,list.getTail().getData());
            assertEquals(2,list.getLength());
        });
        list.insertEnd(3);
        assertAll(()->{
            assertEquals(1,list.getHead().getData());
            assertEquals(3,list.getTail().getData());
            assertEquals(3,list.getLength());
        });

    }

    @Test
    @DisplayName("insert node at the front")
    void testINsertFront(){
        LinkedList<Integer> list=new LinkedList<>();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertFront(0);
        assertAll(()->{
            assertEquals(0,list.getHead().getData());
            assertEquals(3,list.getTail().getData());
        });
        String expected="0 1 2 3 ";
        assertEquals(expected, list.toString());
    }
    @Test
    void testDeleteFront(){
        LinkedList<Integer> list=new LinkedList<>();
        list.insertEnd(6);
        list.insertEnd(10);
        list.insertEnd(8);
        list.insertEnd(15);
        list.deleteFront();
        String expected="10 8 15 ";
        assertEquals(expected, list.toString());
        list.deleteFront();
        expected="8 15 ";
        assertEquals(expected, list.toString());
        list.deleteFront();
         expected="15 ";
         assertEquals(expected, list.toString());
    }

    @Test
    void testGetNthBack(){

        LinkedList<Integer> list=new LinkedList<>();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
        Node<Integer>node=list.getNthNodeBack(1);
        assertAll(()->{
            assertEquals(5,node.getData());
            assertEquals(list.getTail(),node);
            assertEquals(4,list.getNthNodeBack(2).getData());
            assertThrows(RuntimeException.class,()->{list.getNthNodeBack(6);});
        });

    }
    @Test
    void testGetNthBackRecursivly(){

        LinkedList<Integer> list=new LinkedList<>();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
        Node<Integer>node=list.getNthRecursive(1);
        assertAll(()->{
            assertEquals(5,node.getData());
            assertEquals(list.getTail(),node);
            assertEquals(4,list.getNthNodeBack(2).getData());
            assertThrows(RuntimeException.class,()->{list.getNthNodeBack(6);});
        });

    }

    @Test
    void testEquals(){
        LinkedList<Integer> list=new LinkedList<>();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
        LinkedList<Integer> list1 =new LinkedList<>();
        list1.insertEnd(1);
        list1.insertEnd(2);
        list1.insertEnd(3);
        list1.insertEnd(41);
        list1.insertEnd(5);
         assertFalse(list.equals(list1));
         list=new LinkedList<>();
        list.insertEnd(1);
        list.insertEnd(2);
        list.insertEnd(3);
        list.insertEnd(4);
        list.insertEnd(5);
         list1 =new LinkedList<>();
        list1.insertEnd(1);
        list1.insertEnd(2);
        list1.insertEnd(3);
        list1.insertEnd(4);
        list1.insertEnd(5);
        assertTrue(list.equals(list1));


    }

    private LinkedList<Integer> listOf(Integer... values){
        LinkedList<Integer> list=new LinkedList<>();
        for(Integer value:values)
            list.insertEnd(value);
        return list;
    }

    // ---------------------------- deleteFirst ----------------------------

    @Test
    @DisplayName("deleteFirst on empty list returns null and keeps length 0")
    void testDeleteFirst_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertAll(()->{
            assertNull(list.deleteFirst());
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteFirst on single element makes head and tail null")
    void testDeleteFirst_whenOneElement(){
        LinkedList<Integer> list=listOf(7);
        Node<Integer> removed=list.deleteFirst();
        assertAll(()->{
            assertEquals(7,removed.getData());
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
            assertTrue(list.isEmpty());
        });
    }

    @Test
    @DisplayName("deleteFirst moves head forward and keeps tail")
    void testDeleteFirst_whenManyElements(){
        LinkedList<Integer> list=listOf(1,2,3);
        Node<Integer> oldTail=list.getTail();
        Node<Integer> removed=list.deleteFirst();
        assertAll(()->{
            assertEquals(1,removed.getData());
            assertEquals(2,list.getHead().getData());
            assertSame(oldTail,list.getTail());
            assertEquals(2,list.getLength());
            assertEquals("2 3 ",list.toString());
        });
    }

    @Test
    @DisplayName("removed node must be detached from the list")
    void testDeleteFirst_removedNodeIsDetached(){
        LinkedList<Integer> list=listOf(1,2,3);
        Node<Integer> removed=list.deleteFirst();
        assertNull(removed.getNext());
    }

    @Test
    @DisplayName("deleteFirst until empty, extra calls are safe")
    void testDeleteFirst_untilEmpty(){
        LinkedList<Integer> list=listOf(1,2,3);
        assertEquals(1,list.deleteFirst().getData());
        assertEquals(2,list.deleteFirst().getData());
        assertEquals(3,list.deleteFirst().getData());
        assertAll(()->{
            assertNull(list.deleteFirst());
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("list is usable again after deleteFirst empties it")
    void testDeleteFirst_thenInsertAgain(){
        LinkedList<Integer> list=listOf(1);
        list.deleteFirst();
        list.insertEnd(5);
        list.insertEnd(6);
        assertAll(()->{
            assertEquals(5,list.getHead().getData());
            assertEquals(6,list.getTail().getData());
            assertEquals(2,list.getLength());
            assertEquals("5 6 ",list.toString());
        });
    }

    @Test
    @DisplayName("deleteFirst after insertFront on empty list")
    void testDeleteFirst_afterInsertFrontOnEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        list.insertFront(4);
        assertEquals(1,list.getLength());
        assertEquals(4,list.deleteFirst().getData());
        assertTrue(list.isEmpty());
    }

    // ---------------------------- deleteLast ----------------------------

    @Test
    @DisplayName("deleteLast on empty list returns null and keeps length 0")
    void testDeleteLast_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertAll(()->{
            assertNull(list.deleteLast());
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteLast on single element makes head and tail null")
    void testDeleteLast_whenOneElement(){
        LinkedList<Integer> list=listOf(7);
        Node<Integer> removed=list.deleteLast();
        assertAll(()->{
            assertEquals(7,removed.getData());
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteLast on two elements leaves head == tail")
    void testDeleteLast_whenTwoElements(){
        LinkedList<Integer> list=listOf(1,2);
        Node<Integer> removed=list.deleteLast();
        assertAll(()->{
            assertEquals(2,removed.getData());
            assertSame(list.getHead(),list.getTail());
            assertEquals(1,list.getTail().getData());
            assertNull(list.getTail().getNext());
            assertEquals(1,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteLast moves tail back and cuts the link to removed node")
    void testDeleteLast_whenManyElements(){
        LinkedList<Integer> list=listOf(1,2,3,4);
        Node<Integer> oldHead=list.getHead();
        Node<Integer> removed=list.deleteLast();
        assertAll(()->{
            assertEquals(4,removed.getData());
            assertEquals(3,list.getTail().getData());
            assertNull(list.getTail().getNext()); // new tail must not still point to 4
            assertSame(oldHead,list.getHead());
            assertEquals(3,list.getLength());
            assertEquals("1 2 3 ",list.toString());
        });
    }

    @Test
    @DisplayName("deleteLast until empty, extra calls are safe")
    void testDeleteLast_untilEmpty(){
        LinkedList<Integer> list=listOf(1,2,3);
        assertEquals(3,list.deleteLast().getData());
        assertEquals(2,list.deleteLast().getData());
        assertEquals(1,list.deleteLast().getData());
        assertAll(()->{
            assertNull(list.deleteLast());
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("insertEnd works correctly after deleteLast")
    void testDeleteLast_thenInsertEnd(){
        LinkedList<Integer> list=listOf(1,2,3);
        list.deleteLast();
        list.insertEnd(9);
        assertAll(()->{
            assertEquals(9,list.getTail().getData());
            assertEquals("1 2 9 ",list.toString());
            assertEquals(3,list.getLength());
        });
    }

    @Test
    @DisplayName("mixing deleteFirst and deleteLast")
    void testDeleteFirstAndLast_mixed(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        list.deleteFirst();
        list.deleteLast();
        assertEquals("2 3 4 ",list.toString());
        list.deleteLast();
        list.deleteFirst();
        assertAll(()->{
            assertSame(list.getHead(),list.getTail());
            assertEquals(3,list.getHead().getData());
            assertEquals(1,list.getLength());
        });
        list.deleteLast();
        assertTrue(list.isEmpty());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    // ---------------------------- deleteWithKey ----------------------------

    @Test
    @DisplayName("deleteWithKey on empty list returns null")
    void testDeleteWithKey_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertAll(()->{
            assertNull(list.deleteWithKey(1));
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteWithKey with key not found leaves list unchanged")
    void testDeleteWithKey_whenKeyNotFound(){
        LinkedList<Integer> list=listOf(1,2,3);
        assertAll(()->{
            assertNull(list.deleteWithKey(99));
            assertEquals("1 2 3 ",list.toString());
            assertEquals(3,list.getLength());
            assertEquals(1,list.getHead().getData());
            assertEquals(3,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("deleteWithKey on the only element empties the list")
    void testDeleteWithKey_whenOneElement(){
        LinkedList<Integer> list=listOf(5);
        Node<Integer> removed=list.deleteWithKey(5);
        assertAll(()->{
            assertEquals(5,removed.getData());
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteWithKey on the only element with wrong key does nothing")
    void testDeleteWithKey_whenOneElementNotMatching(){
        LinkedList<Integer> list=listOf(5);
        assertNull(list.deleteWithKey(6));
        assertSame(list.getHead(),list.getTail());
        assertEquals(1,list.getLength());
    }

    @Test
    @DisplayName("deleteWithKey on head moves head forward")
    void testDeleteWithKey_whenKeyIsHead(){
        LinkedList<Integer> list=listOf(1,2,3);
        Node<Integer> removed=list.deleteWithKey(1);
        assertAll(()->{
            assertEquals(1,removed.getData());
            assertEquals(2,list.getHead().getData());
            assertEquals(3,list.getTail().getData());
            assertEquals("2 3 ",list.toString());
            assertEquals(2,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteWithKey in the middle links neighbours together")
    void testDeleteWithKey_whenKeyInMiddle(){
        LinkedList<Integer> list=listOf(1,2,3,4);
        Node<Integer> removed=list.deleteWithKey(3);
        assertAll(()->{
            assertEquals(3,removed.getData());
            assertEquals("1 2 4 ",list.toString());
            assertEquals(3,list.getLength());
            assertEquals(1,list.getHead().getData());
            assertEquals(4,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("deleteWithKey on tail moves tail back and cuts its next")
    void testDeleteWithKey_whenKeyIsTail(){
        LinkedList<Integer> list=listOf(1,2,3);
        Node<Integer> removed=list.deleteWithKey(3);
        assertAll(()->{
            assertEquals(3,removed.getData());
            assertEquals(2,list.getTail().getData());
            assertNull(list.getTail().getNext());
            assertEquals("1 2 ",list.toString());
            assertEquals(2,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteWithKey on two elements leaves head == tail")
    void testDeleteWithKey_whenTwoElements(){
        LinkedList<Integer> first=listOf(1,2);
        first.deleteWithKey(1);
        LinkedList<Integer> second=listOf(1,2);
        second.deleteWithKey(2);
        assertAll(()->{
            assertSame(first.getHead(),first.getTail());
            assertEquals(2,first.getHead().getData());
            assertSame(second.getHead(),second.getTail());
            assertEquals(1,second.getHead().getData());
            assertNull(second.getTail().getNext());
        });
    }

    @Test
    @DisplayName("deleteWithKey with duplicates removes only the first match")
    void testDeleteWithKey_whenDuplicates(){
        LinkedList<Integer> list=listOf(1,2,2,3,2);
        list.deleteWithKey(2);
        assertAll(()->{
            assertEquals("1 2 3 2 ",list.toString());
            assertEquals(4,list.getLength());
            assertEquals(2,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("removed node must be detached from the list")
    void testDeleteWithKey_removedNodeIsDetached(){
        LinkedList<Integer> list=listOf(1,2,3);
        Node<Integer> removed=list.deleteWithKey(2);
        assertNull(removed.getNext());
    }



    @Test
    @DisplayName("deleteWithKey uses equals, not ==")
    void testDeleteWithKey_usesEqualsNotReference(){
        LinkedList<Integer> list=listOf(1000,2000);
        // 1000 is outside the Integer cache, so new boxed value is a different object
        Node<Integer> removed=list.deleteWithKey(Integer.valueOf(1000));
        assertNotNull(removed);
        assertEquals("2000 ",list.toString());
    }

    @Test
    @DisplayName("delete every key one by one, then insert again")
    void testDeleteWithKey_untilEmptyThenInsert(){
        LinkedList<Integer> list=listOf(1,2,3);
        list.deleteWithKey(2);
        list.deleteWithKey(3);
        list.deleteWithKey(1);
        assertTrue(list.isEmpty());
        assertNull(list.getHead());
        assertNull(list.getTail());
        list.insertEnd(7);
        assertAll(()->{
            assertEquals(7,list.getHead().getData());
            assertSame(list.getHead(),list.getTail());
            assertEquals(1,list.getLength());
        });
    }

    // ---------------------------- swapPairs ----------------------------

    @Test
    @DisplayName("swapPairs on empty list does nothing")
    void testSwapPairs_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertDoesNotThrow(()->list.swapPairs());
        assertAll(()->{
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("swapPairs on one element does nothing")
    void testSwapPairs_whenOneElement(){
        LinkedList<Integer> list=listOf(1);
        assertDoesNotThrow(()->list.swapPairs());
        assertAll(()->{
            assertEquals("1 ",list.toString());
            assertSame(list.getHead(),list.getTail());
            assertEquals(1,list.getLength());
        });
    }

    @Test
    @DisplayName("swapPairs {1,2} => {2,1}")
    void testSwapPairs_whenTwoElements(){
        LinkedList<Integer> list=listOf(1,2);
        list.swapPairs();
        assertAll(()->{
            assertEquals("2 1 ",list.toString());
            assertEquals(2,list.getHead().getData());
            assertEquals(1,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("swapPairs {1,2,3} => {2,1,3}")
    void testSwapPairs_whenThreeElements(){
        LinkedList<Integer> list=listOf(1,2,3);
        assertDoesNotThrow(()->list.swapPairs());
        assertAll(()->{
            assertEquals("2 1 3 ",list.toString());
            assertEquals(3,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("swapPairs {1,2,3,4} => {2,1,4,3}")
    void testSwapPairs_whenEvenLength(){
        LinkedList<Integer> list=listOf(1,2,3,4);
        list.swapPairs();
        assertAll(()->{
            assertEquals("2 1 4 3 ",list.toString());
            assertEquals(2,list.getHead().getData());
            assertEquals(3,list.getTail().getData());
            assertEquals(4,list.getLength());
        });
    }

    @Test
    @DisplayName("swapPairs {1,2,3,4,5} => {2,1,4,3,5}")
    void testSwapPairs_whenOddLength(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        assertDoesNotThrow(()->list.swapPairs());
        assertAll(()->{
            assertEquals("2 1 4 3 5 ",list.toString());
            assertEquals(2,list.getHead().getData());
            assertEquals(5,list.getTail().getData());
            assertEquals(5,list.getLength());
        });
    }

    @Test
    @DisplayName("swapPairs keeps the list structure valid")
    void testSwapPairs_keepsStructure(){
        LinkedList<Integer> list=listOf(1,2,3,4,5,6);
        list.swapPairs();
        assertAll(()->{
            assertNull(list.getTail().getNext());
            assertDoesNotThrow(()->list.dataIntegrity());
            assertEquals(6,list.getLength());
        });
    }

    @Test
    @DisplayName("swapPairs twice gives back the original list")
    void testSwapPairs_twiceRestoresOriginal(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        list.swapPairs();
        list.swapPairs();
        assertEquals("1 2 3 4 5 ",list.toString());
    }

    @Test
    @DisplayName("swapPairs with equal values")
    void testSwapPairs_whenEqualValues(){
        LinkedList<Integer> list=listOf(7,7,7,7);
        list.swapPairs();
        assertEquals("7 7 7 7 ",list.toString());
    }

    @Test
    @DisplayName("swapPairs with null values does not crash")
    void testSwapPairs_whenNullValues(){
        LinkedList<Integer> list=listOf(null,1,2,null);
        assertDoesNotThrow(()->list.swapPairs());
        assertEquals("1 null null 2 ",list.toString());
    }

    @Test
    @DisplayName("swapPairs works with Strings")
    void testSwapPairs_withStrings(){
        LinkedList<String> list=new LinkedList<>();
        list.insertEnd("a");
        list.insertEnd("b");
        list.insertEnd("c");
        list.insertEnd("d");
        list.swapPairs();
        assertEquals("b a d c ",list.toString());
    }

    @Test
    @DisplayName("list still works after swapPairs")
    void testSwapPairs_thenInsertAndDelete(){
        LinkedList<Integer> list=listOf(1,2,3);
        list.swapPairs();
        list.insertEnd(4);
        assertEquals("2 1 3 4 ",list.toString());
        assertEquals(4,list.deleteLast().getData());
        assertEquals(2,list.deleteFirst().getData());
        assertEquals("1 3 ",list.toString());
    }

    // ---------------------------- reverse ----------------------------

    private java.util.List<Node<Integer>> nodesOf(LinkedList<Integer> list){
        java.util.List<Node<Integer>> nodes=new java.util.ArrayList<>();
        for(Node<Integer> current=list.getHead();current!=null&&nodes.size()<=list.getLength();current=current.getNext())
            nodes.add(current);
        return nodes;
    }

    @Test
    @DisplayName("reverse on empty list does nothing")
    void testReverse_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertDoesNotThrow(()->list.reverse());
        assertAll(()->{
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("reverse on one element keeps the same node")
    void testReverse_whenOneElement(){
        LinkedList<Integer> list=listOf(1);
        Node<Integer> only=list.getHead();
        list.reverse();
        assertAll(()->{
            assertSame(only,list.getHead());
            assertSame(only,list.getTail());
            assertNull(only.getNext());
            assertEquals(1,list.getLength());
        });
    }

    @Test
    @DisplayName("reverse {1,2} => {2,1}")
    void testReverse_whenTwoElements(){
        LinkedList<Integer> list=listOf(1,2);
        Node<Integer> first=list.getHead();
        Node<Integer> second=list.getTail();
        list.reverse();
        assertAll(()->{
            assertEquals("2 1 ",list.toString());
            assertSame(second,list.getHead());
            assertSame(first,list.getTail());
            assertSame(first,second.getNext());
            assertNull(first.getNext());
        });
    }

    @Test
    @DisplayName("reverse {1,2,3} => {3,2,1}")
    void testReverse_whenThreeElements(){
        LinkedList<Integer> list=listOf(1,2,3);
        list.reverse();
        assertAll(()->{
            assertEquals("3 2 1 ",list.toString());
            assertEquals(3,list.getHead().getData());
            assertEquals(1,list.getTail().getData());
            assertEquals(3,list.getLength());
        });
    }

    @Test
    @DisplayName("reverse {1,2,3,4,5} => {5,4,3,2,1}")
    void testReverse_whenOddLength(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        list.reverse();
        System.out.println(list.toString());
        assertAll(()->{
            assertEquals("5 4 3 2 1 ",list.toString());
            assertEquals(5,list.getHead().getData());
            assertEquals(1,list.getTail().getData());
            assertEquals(5,list.getLength());
        });
    }

    @Test
    @DisplayName("reverse {1,2,3,4} => {4,3,2,1}")
    void testReverse_whenEvenLength(){
        LinkedList<Integer> list=listOf(1,2,3,4);
        list.reverse();
        assertEquals("4 3 2 1 ",list.toString());
    }

    @Test
    @DisplayName("reverse moves the nodes themselves, not only the data")
    void testReverse_reversesAddressesNotData(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        java.util.List<Node<Integer>> before=nodesOf(list);
        list.reverse();
        java.util.List<Node<Integer>> after=nodesOf(list);
        assertEquals(before.size(),after.size());
        for(int i=0;i<before.size();i++){
            Node<Integer> original=before.get(i);
            Node<Integer> reversed=after.get(after.size()-1-i);
            assertSame(original,reversed,"node at position "+i+" was not moved");
            assertEquals(i+1,original.getData(),"data inside node was changed");
        }
    }

    @Test
    @DisplayName("after reverse: tail.next is null and there is no cycle")
    void testReverse_keepsStructure(){
        LinkedList<Integer> list=listOf(1,2,3,4,5,6);
        list.reverse();
        assertAll(()->{
            assertNull(list.getTail().getNext());
            assertDoesNotThrow(()->list.noCycle());
            assertDoesNotThrow(()->list.dataIntegrity());
            assertEquals(6,nodesOf(list).size());
        });
    }

    @Test
    @DisplayName("reverse twice gives back the original list")
    void testReverse_twiceRestoresOriginal(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        Node<Integer> oldHead=list.getHead();
        Node<Integer> oldTail=list.getTail();
        list.reverse();
        list.reverse();
        assertAll(()->{
            assertEquals("1 2 3 4 5 ",list.toString());
            assertSame(oldHead,list.getHead());
            assertSame(oldTail,list.getTail());
        });
    }

    @Test
    @DisplayName("reverse with duplicates and null values")
    void testReverse_withDuplicatesAndNulls(){
        LinkedList<Integer> list=listOf(1,null,1,2);
        assertDoesNotThrow(()->list.reverse());
        assertEquals("2 1 null 1 ",list.toString());
    }

    @Test
    @DisplayName("list still works after reverse")
    void testReverse_thenInsertAndDelete(){
        LinkedList<Integer> list=listOf(1,2,3);
        list.reverse();
        list.insertEnd(0);
        list.insertFront(4);
        assertEquals("4 3 2 1 0 ",list.toString());
        assertEquals(0,list.deleteLast().getData());
        assertEquals(4,list.deleteFirst().getData());
        assertEquals("3 2 1 ",list.toString());
        assertEquals(3,list.getLength());
    }

    // ---------------------------- deleteEvenPosition ----------------------------

    @Test
    @DisplayName("deleteEvenPosition on empty list does nothing")
    void testDeleteEvenPosition_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertDoesNotThrow(()->list.deleteEvenPosition());
        assertAll(()->{
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteEvenPosition on one element keeps it")
    void testDeleteEvenPosition_whenOneElement(){
        LinkedList<Integer> list=listOf(1);
        list.deleteEvenPosition();
        assertAll(()->{
            assertEquals("1 ",list.toString());
            assertSame(list.getHead(),list.getTail());
            assertEquals(1,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteEvenPosition {1,2} => {1}, tail moves back")
    void testDeleteEvenPosition_whenTwoElements(){
        LinkedList<Integer> list=listOf(1,2);
        list.deleteEvenPosition();
        assertAll(()->{
            assertEquals("1 ",list.toString());
            assertSame(list.getHead(),list.getTail());
            assertNull(list.getTail().getNext());
            assertEquals(1,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteEvenPosition {1,2,3} => {1,3}")
    void testDeleteEvenPosition_whenThreeElements(){
        LinkedList<Integer> list=listOf(1,2,3);
        list.deleteEvenPosition();
        assertAll(()->{
            assertEquals("1 3 ",list.toString());
            assertEquals(3,list.getTail().getData());
            assertEquals(2,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteEvenPosition {1,2,3,4,10} => {1,3,10}")
    void testDeleteEvenPosition_whenOddLength(){
        LinkedList<Integer> list=listOf(1,2,3,4,10);
        list.deleteEvenPosition();
        assertAll(()->{
            assertEquals("1 3 10 ",list.toString());
            assertEquals(1,list.getHead().getData());
            assertEquals(10,list.getTail().getData());
            assertEquals(3,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteEvenPosition {1,2,3,4,5,6} => {1,3,5}, tail must be 5")
    void testDeleteEvenPosition_whenEvenLength(){
        LinkedList<Integer> list=listOf(1,2,3,4,5,6);
        list.deleteEvenPosition();
        assertAll(()->{
            assertEquals("1 3 5 ",list.toString());
            assertEquals(5,list.getTail().getData());
            assertNull(list.getTail().getNext());
            assertEquals(3,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteEvenPosition uses positions, not values")
    void testDeleteEvenPosition_usesPositionsNotValues(){
        LinkedList<Integer> list=listOf(2,4,6,8,10);
        list.deleteEvenPosition();
        assertEquals("2 6 10 ",list.toString());
        LinkedList<Integer> odds=listOf(7,9,11,13);
        odds.deleteEvenPosition();
        assertEquals("7 11 ",odds.toString());
    }

    @Test
    @DisplayName("deleteEvenPosition keeps the original nodes (addresses)")
    void testDeleteEvenPosition_keepsOddNodes(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        java.util.List<Node<Integer>> before=nodesOf(list);
        list.deleteEvenPosition();
        java.util.List<Node<Integer>> after=nodesOf(list);
        assertAll(()->{
            assertEquals(3,after.size());
            assertSame(before.get(0),after.get(0));
            assertSame(before.get(2),after.get(1));
            assertSame(before.get(4),after.get(2));
        });
    }

    @Test
    @DisplayName("deleteEvenPosition keeps the list structure valid")
    void testDeleteEvenPosition_keepsStructure(){
        LinkedList<Integer> list=listOf(1,2,3,4,5,6,7,8);
        list.deleteEvenPosition();
        assertAll(()->{
            assertEquals(4,nodesOf(list).size());
            assertEquals(4,list.getLength());
            assertDoesNotThrow(()->list.dataIntegrity());
        });
    }

    @Test
    @DisplayName("deleteEvenPosition twice: {1..8} => {1,3,5,7} => {1,5}")
    void testDeleteEvenPosition_twice(){
        LinkedList<Integer> list=listOf(1,2,3,4,5,6,7,8);
        list.deleteEvenPosition();
        list.deleteEvenPosition();
        assertAll(()->{
            assertEquals("1 5 ",list.toString());
            assertEquals(5,list.getTail().getData());
            assertEquals(2,list.getLength());
        });
    }

    @Test
    @DisplayName("deleteEvenPosition with duplicates and null values")
    void testDeleteEvenPosition_withDuplicatesAndNulls(){
        LinkedList<Integer> list=listOf(1,1,null,null,1);
        assertDoesNotThrow(()->list.deleteEvenPosition());
        assertEquals("1 null 1 ",list.toString());
    }

    @Test
    @DisplayName("list still works after deleteEvenPosition")
    void testDeleteEvenPosition_thenInsertAndDelete(){
        LinkedList<Integer> list=listOf(1,2,3,4);
        list.deleteEvenPosition();
        list.insertEnd(9);
        assertEquals("1 3 9 ",list.toString());
        assertEquals(9,list.deleteLast().getData());
        assertEquals(3,list.getTail().getData());
        assertEquals(2,list.getLength());
    }

    // ---------------------------- insertToBeSorted ----------------------------

    private static final java.util.Comparator<Integer> ASC=java.util.Comparator.naturalOrder();

    private LinkedList<Integer> sortedOf(Integer... values){
        LinkedList<Integer> list=new LinkedList<>();
        for(Integer value:values)
            list.insertToBeSorted(value,ASC);
        return list;
    }

    @Test
    @DisplayName("insertToBeSorted into empty list")
    void testinsertToBeSorted_whenEmpty(){
        LinkedList<Integer> list=sortedOf(5);
        assertAll(()->{
            assertEquals("5 ",list.toString());
            assertSame(list.getHead(),list.getTail());
            assertEquals(1,list.getLength());
        });
    }

    @Test
    @DisplayName("insertToBeSorted smaller than head goes to the front")
    void testinsertToBeSorted_atFront(){
        LinkedList<Integer> list=sortedOf(5,3);
        assertAll(()->{
            assertEquals("3 5 ",list.toString());
            assertEquals(3,list.getHead().getData());
            assertEquals(5,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("insertToBeSorted bigger than tail goes to the end and updates tail")
    void testinsertToBeSorted_atEnd(){
        LinkedList<Integer> list=sortedOf(1,3,9);
        assertAll(()->{
            assertEquals("1 3 9 ",list.toString());
            assertEquals(9,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("insertToBeSorted in the middle")
    void testinsertToBeSorted_inMiddle(){
        LinkedList<Integer> list=sortedOf(1,10,5);
        assertAll(()->{
            assertEquals("1 5 10 ",list.toString());
            assertEquals(1,list.getHead().getData());
            assertEquals(10,list.getTail().getData());
            assertEquals(3,list.getLength());
        });
    }

    @Test
    @DisplayName("insertToBeSorted ascending input")
    void testinsertToBeSorted_ascendingInput(){
        assertEquals("1 2 3 4 5 ",sortedOf(1,2,3,4,5).toString());
    }

    @Test
    @DisplayName("insertToBeSorted descending input")
    void testinsertToBeSorted_descendingInput(){
        assertEquals("1 2 3 4 5 ",sortedOf(5,4,3,2,1).toString());
    }

    @Test
    @DisplayName("insertToBeSorted random input")
    void testinsertToBeSorted_randomInput(){
        LinkedList<Integer> list=sortedOf(10,2,30,2,5);
        assertAll(()->{
            assertEquals("2 2 5 10 30 ",list.toString());
            assertEquals(5,list.getLength());
        });
    }

    @Test
    @DisplayName("insertToBeSorted duplicates of head, middle and tail")
    void testinsertToBeSorted_duplicates(){
        LinkedList<Integer> list=sortedOf(3,1,5,1,3,5);
        assertAll(()->{
            assertEquals("1 1 3 3 5 5 ",list.toString());
            assertEquals(6,list.getLength());
            assertEquals(5,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("insertToBeSorted all equal values")
    void testinsertToBeSorted_allEqual(){
        LinkedList<Integer> list=sortedOf(7,7,7);
        assertEquals("7 7 7 ",list.toString());
        assertEquals(3,list.getLength());
    }

    @Test
    @DisplayName("insertToBeSorted with negative numbers and zero")
    void testinsertToBeSorted_negativesAndZero(){
        assertEquals("-5 -1 0 3 ",sortedOf(0,-1,3,-5).toString());
    }

    @Test
    @DisplayName("insertToBeSorted compares by value, not by reference")
    void testinsertToBeSorted_bigIntegers(){
        // outside the Integer cache: == would give wrong answers
        assertEquals("1000 1500 2000 ",sortedOf(2000,1000,1500).toString());
    }

    @Test
    @DisplayName("insertToBeSorted inserts the value only once")
    void testinsertToBeSorted_noDoubleInsert(){
        LinkedList<Integer> list=sortedOf(4);
        list.insertToBeSorted(2,ASC);
        assertEquals(2,list.getLength());
        assertEquals(2,nodesOf(list).size());
    }

    @Test
    @DisplayName("insertToBeSorted keeps the list structure valid")
    void testinsertToBeSorted_keepsStructure(){
        LinkedList<Integer> list=sortedOf(8,3,6,1,9,2);
        assertAll(()->{
            assertEquals(6,nodesOf(list).size());
            assertEquals(6,list.getLength());
            assertNull(list.getTail().getNext());
            assertDoesNotThrow(()->list.dataIntegrity());
        });
    }

    @Test
    @DisplayName("insertToBeSorted with Strings")
    void testinsertToBeSorted_withStrings(){
        LinkedList<String> list=new LinkedList<>();
        list.insertToBeSorted("c",java.util.Comparator.naturalOrder());
        list.insertToBeSorted("a",java.util.Comparator.naturalOrder());
        list.insertToBeSorted("b",java.util.Comparator.naturalOrder());
        assertEquals("a b c ",list.toString());
    }

    @Test
    @DisplayName("insertToBeSorted with descending comparator")
    void testinsertToBeSorted_descendingComparator(){
        LinkedList<Integer> list=new LinkedList<>();
        java.util.Comparator<Integer> desc=java.util.Comparator.reverseOrder();
        list.insertToBeSorted(1,desc);
        list.insertToBeSorted(3,desc);
        list.insertToBeSorted(2,desc);
        assertEquals("3 2 1 ",list.toString());
    }

    @Test
    @DisplayName("list still works after insertToBeSorted")
    void testinsertToBeSorted_thenDelete(){
        LinkedList<Integer> list=sortedOf(3,1,2);
        assertEquals(3,list.deleteLast().getData());
        assertEquals(1,list.deleteFirst().getData());
        list.insertToBeSorted(5,ASC);
        assertEquals("2 5 ",list.toString());
        assertEquals(5,list.getTail().getData());
    }

    // ---------------------------- swapHeadAndTail ----------------------------

    // prints values and node addresses, e.g. "1@1b6d3586 -> 2@4554617c -> null"
    private void printValuesAndAddresses(String title,LinkedList<Integer> list){
        StringBuilder builder=new StringBuilder(title).append(": ");
        int count=0;
        for(Node<Integer> current=list.getHead();current!=null&&count<=list.getLength();current=current.getNext(),count++)
            builder.append(current.getData()).append("@")
                   .append(Integer.toHexString(System.identityHashCode(current))).append(" -> ");
        builder.append("null");
        System.out.println(builder);
    }

    private LinkedList<Integer> swapAndPrint(LinkedList<Integer> list){
        printValuesAndAddresses("before",list);
        list.swapHeadAndTail();
        printValuesAndAddresses("after ",list);
        return list;
    }

    @Test
    @DisplayName("swapHeadAndTail on empty list does nothing")
    void testSwapHeadAndTail_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertDoesNotThrow(()->list.swapHeadAndTail());
        assertAll(()->{
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("swapHeadAndTail on one element keeps the same node")
    void testSwapHeadAndTail_whenOneElement(){
        LinkedList<Integer> list=listOf(1);
        Node<Integer> only=list.getHead();
        swapAndPrint(list);
        assertAll(()->{
            assertSame(only,list.getHead());
            assertSame(only,list.getTail());
            assertNull(only.getNext());
        });
    }

    @Test
    @DisplayName("swapHeadAndTail {1,2} => {2,1}")
    void testSwapHeadAndTail_whenTwoElements(){
        LinkedList<Integer> list=listOf(1,2);
        Node<Integer> oldHead=list.getHead();
        Node<Integer> oldTail=list.getTail();
        assertDoesNotThrow(()->swapAndPrint(list));
        assertAll(()->{
            assertEquals("2 1 ",list.toString());
            assertSame(oldTail,list.getHead());
            assertSame(oldHead,list.getTail());
            assertSame(oldHead,oldTail.getNext());
            assertNull(oldHead.getNext());
            assertEquals(2,list.getLength());
        });
    }

    @Test
    @DisplayName("swapHeadAndTail {1,2,3} => {3,2,1}")
    void testSwapHeadAndTail_whenThreeElements(){
        LinkedList<Integer> list=listOf(1,2,3);
        java.util.List<Node<Integer>> before=nodesOf(list);
        swapAndPrint(list);
        java.util.List<Node<Integer>> after=nodesOf(list);
        assertAll(()->{
            assertEquals("3 2 1 ",list.toString());
            assertSame(before.get(2),after.get(0));
            assertSame(before.get(1),after.get(1));
            assertSame(before.get(0),after.get(2));
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("swapHeadAndTail {1,2,3,4,5} => {5,2,3,4,1}")
    void testSwapHeadAndTail_whenManyElements(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        swapAndPrint(list);
        assertAll(()->{
            assertEquals("5 2 3 4 1 ",list.toString());
            assertEquals(5,list.getHead().getData());
            assertEquals(1,list.getTail().getData());
            assertEquals(5,list.getLength());
        });
    }

    @Test
    @DisplayName("swapHeadAndTail swaps NODES (addresses), not values")
    void testSwapHeadAndTail_swapsAddressesNotValues(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        java.util.List<Node<Integer>> before=nodesOf(list);
        swapAndPrint(list);
        java.util.List<Node<Integer>> after=nodesOf(list);
        assertAll(()->{
            assertSame(before.get(4),list.getHead(),"old tail node must become head");
            assertSame(before.get(0),list.getTail(),"old head node must become tail");
            assertEquals(1,before.get(0).getData(),"value inside old head node was changed");
            assertEquals(5,before.get(4).getData(),"value inside old tail node was changed");
            for(int i=1;i<4;i++)
                assertSame(before.get(i),after.get(i),"middle node "+i+" moved");
        });
    }

    @Test
    @DisplayName("swapHeadAndTail keeps the list structure valid")
    void testSwapHeadAndTail_keepsStructure(){
        LinkedList<Integer> list=listOf(1,2,3,4,5,6);
        swapAndPrint(list);
        assertAll(()->{
            assertNull(list.getTail().getNext());
            assertEquals(6,nodesOf(list).size());
            assertDoesNotThrow(()->list.noCycle());
            assertDoesNotThrow(()->list.dataIntegrity());
        });
    }

    @Test
    @DisplayName("swapHeadAndTail twice gives back the original list")
    void testSwapHeadAndTail_twiceRestoresOriginal(){
        LinkedList<Integer> list=listOf(1,2,3,4);
        Node<Integer> oldHead=list.getHead();
        Node<Integer> oldTail=list.getTail();
        swapAndPrint(list);
        swapAndPrint(list);
        assertAll(()->{
            assertEquals("1 2 3 4 ",list.toString());
            assertSame(oldHead,list.getHead());
            assertSame(oldTail,list.getTail());
        });
    }

    @Test
    @DisplayName("swapHeadAndTail with equal and null values")
    void testSwapHeadAndTail_withEqualAndNullValues(){
        LinkedList<Integer> list=listOf(7,null,7);
        Node<Integer> oldHead=list.getHead();
        assertDoesNotThrow(()->swapAndPrint(list));
        assertEquals("7 null 7 ",list.toString());
        assertSame(oldHead,list.getTail());
    }

    @Test
    @DisplayName("list still works after swapHeadAndTail")
    void testSwapHeadAndTail_thenInsertAndDelete(){
        LinkedList<Integer> list=listOf(1,2,3);
        swapAndPrint(list);
        list.insertEnd(4);
        list.insertFront(0);
        assertEquals("0 3 2 1 4 ",list.toString());
        assertEquals(4,list.deleteLast().getData());
        assertEquals(1,list.getTail().getData());
        assertEquals(0,list.deleteFirst().getData());
        assertEquals(3,list.getHead().getData());
    }

    // ---------------------------- leftRotate ----------------------------

    @Test
    @DisplayName("leftRotate on empty list does nothing")
    void testLeftRotate_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertDoesNotThrow(()->list.leftRotate(3));
        assertAll(()->{
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("leftRotate on one element keeps it")
    void testLeftRotate_whenOneElement(){
        LinkedList<Integer> list=listOf(6);
        Node<Integer> only=list.getHead();
        list.leftRotate(5);
        assertAll(()->{
            assertSame(only,list.getHead());
            assertSame(only,list.getTail());
            assertNull(only.getNext());
        });
    }

    @Test
    @DisplayName("leftRotate k = 0 does nothing")
    void testLeftRotate_whenZero(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        list.leftRotate(0);
        assertEquals("6 10 8 15 ",list.toString());
    }

    @Test
    @DisplayName("leftRotate k = 1 => {10,8,15,6}")
    void testLeftRotate_whenOne(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        list.leftRotate(1);
        assertAll(()->{
            assertEquals("10 8 15 6 ",list.toString());
            assertEquals(10,list.getHead().getData());
            assertEquals(6,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("leftRotate k = 2 => {8,15,6,10}")
    void testLeftRotate_whenTwo(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        list.leftRotate(2);
        assertEquals("8 15 6 10 ",list.toString());
        assertEquals(10,list.getTail().getData());
    }

    @Test
    @DisplayName("leftRotate k = 3 => {15,6,10,8}")
    void testLeftRotate_whenThree(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        list.leftRotate(3);
        assertEquals("15 6 10 8 ",list.toString());
        assertEquals(8,list.getTail().getData());
    }

    @Test
    @DisplayName("leftRotate k = length gives the same list")
    void testLeftRotate_whenEqualsLength(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        Node<Integer> oldHead=list.getHead();
        Node<Integer> oldTail=list.getTail();
        list.leftRotate(4);
        assertAll(()->{
            assertEquals("6 10 8 15 ",list.toString());
            assertSame(oldHead,list.getHead());
            assertSame(oldTail,list.getTail());
        });
    }

    @Test
    @DisplayName("leftRotate k > length uses k % length")
    void testLeftRotate_whenBiggerThanLength(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        list.leftRotate(5); // same as 1
        assertEquals("10 8 15 6 ",list.toString());
        LinkedList<Integer> other=listOf(6,10,8,15);
        other.leftRotate(10); // same as 2
        assertEquals("8 15 6 10 ",other.toString());
    }

    @Test
    @DisplayName("leftRotate on two elements")
    void testLeftRotate_whenTwoElements(){
        LinkedList<Integer> list=listOf(1,2);
        list.leftRotate(1);
        assertAll(()->{
            assertEquals("2 1 ",list.toString());
            assertEquals(2,list.getHead().getData());
            assertEquals(1,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @Timeout(1)
    @DisplayName("leftRotate with huge k (200000000) is fast")
    void testLeftRotate_whenHugeK(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        list.leftRotate(200_000_000); // 200000000 % 4 == 0
        assertEquals("6 10 8 15 ",list.toString());
        LinkedList<Integer> other=listOf(6,10,8,15);
        other.leftRotate(200_000_001); // % 4 == 1
        assertEquals("10 8 15 6 ",other.toString());
    }

    @Test
    @Timeout(2)
    @DisplayName("leftRotate on a long list with huge k is O(n)")
    void testLeftRotate_longListHugeK(){
        LinkedList<Integer> list=new LinkedList<>();
        for(int i=0;i<1000;i++)
            list.add(i);
        list.leftRotate(Integer.MAX_VALUE); // MAX_VALUE % 1000 == 647
        assertAll(()->{
            assertEquals(647,list.getHead().getData());
            assertEquals(646,list.getTail().getData());
            assertEquals(1000,list.getLength());
        });
    }

    @Test
    @DisplayName("leftRotate moves the nodes (addresses), not values")
    void testLeftRotate_movesNodes(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        java.util.List<Node<Integer>> before=nodesOf(list);
        list.leftRotate(1);
        java.util.List<Node<Integer>> after=nodesOf(list);
        assertAll(()->{
            assertSame(before.get(1),after.get(0));
            assertSame(before.get(2),after.get(1));
            assertSame(before.get(3),after.get(2));
            assertSame(before.get(0),after.get(3));
        });
    }

    @Test
    @DisplayName("leftRotate keeps the list structure valid")
    void testLeftRotate_keepsStructure(){
        LinkedList<Integer> list=listOf(1,2,3,4,5,6);
        list.leftRotate(4);
        assertAll(()->{
            assertEquals("5 6 1 2 3 4 ",list.toString());
            assertEquals(6,nodesOf(list).size());
            assertEquals(6,list.getLength());
            assertNull(list.getTail().getNext());
            assertDoesNotThrow(()->list.dataIntegrity());
        });
    }

    @Test
    @DisplayName("leftRotate k then (length - k) gives back the original")
    void testLeftRotate_restoresOriginal(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        list.leftRotate(2);
        list.leftRotate(3);
        assertEquals("1 2 3 4 5 ",list.toString());
    }

    @Test
    @DisplayName("leftRotate with negative k throws IllegalArgumentException")
    void testLeftRotate_whenNegative(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        assertAll(()->{
            assertThrows(IllegalArgumentException.class,()->list.leftRotate(-1));
            assertThrows(IllegalArgumentException.class,()->list.leftRotate(-4));
            assertThrows(IllegalArgumentException.class,()->list.leftRotate(-200_000_000));
            assertThrows(IllegalArgumentException.class,()->list.leftRotate(Integer.MIN_VALUE));
        });
    }

    @Test
    @DisplayName("leftRotate with negative k leaves the list unchanged")
    void testLeftRotate_whenNegative_listUnchanged(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        Node<Integer> oldHead=list.getHead();
        Node<Integer> oldTail=list.getTail();
        assertThrows(IllegalArgumentException.class,()->list.leftRotate(-2));
        assertAll(()->{
            assertEquals("6 10 8 15 ",list.toString());
            assertSame(oldHead,list.getHead());
            assertSame(oldTail,list.getTail());
            assertEquals(4,list.getLength());
        });
    }

    @Test
    @DisplayName("leftRotate with negative k throws even on empty or one-element list")
    void testLeftRotate_whenNegative_smallLists(){
        LinkedList<Integer> empty=new LinkedList<>();
        LinkedList<Integer> one=listOf(6);
        assertAll(()->{
            assertThrows(IllegalArgumentException.class,()->empty.leftRotate(-1));
            assertThrows(IllegalArgumentException.class,()->one.leftRotate(-1));
        });
    }

    @Test
    @DisplayName("list still works after leftRotate")
    void testLeftRotate_thenInsertAndDelete(){
        LinkedList<Integer> list=listOf(6,10,8,15);
        list.leftRotate(1);
        list.insertEnd(99);
        assertEquals("10 8 15 6 99 ",list.toString());
        assertEquals(99,list.deleteLast().getData());
        assertEquals(6,list.getTail().getData());
        assertEquals(10,list.deleteFirst().getData());
        assertEquals("8 15 6 ",list.toString());
    }

    // ---------------------------- RemoveDuplicates ----------------------------

    // the only place that calls RemoveDuplicates: change here if the signature changes
    private LinkedList<Integer> removeDuplicatesOf(Integer... values){
        LinkedList<Integer> list=listOf(values);
        list.RemoveDuplicates(list.getHead(),java.util.Comparator.naturalOrder());
        return list;
    }

    @Test
    @DisplayName("RemoveDuplicates {1,2,1,3,2,4,3,5,2} => {1,2,3,4,5}")
    void testRemoveDuplicates_example(){
        LinkedList<Integer> list=removeDuplicatesOf(1,2,1,3,2,4,3,5,2);
        assertAll(()->{
            assertEquals("1 2 3 4 5 ",list.toString());
            assertEquals(5,list.getLength());
            assertEquals(1,list.getHead().getData());
            assertEquals(5,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("RemoveDuplicates with no duplicates keeps everything")
    void testRemoveDuplicates_noDuplicates(){
        LinkedList<Integer> list=removeDuplicatesOf(1,2,3,4,5);
        assertEquals("1 2 3 4 5 ",list.toString());
        assertEquals(5,list.getLength());
    }

    @Test
    @DisplayName("RemoveDuplicates {1,1,1} => {1}")
    void testRemoveDuplicates_allSame(){
        LinkedList<Integer> list=removeDuplicatesOf(1,1,1);
        assertAll(()->{
            assertEquals("1 ",list.toString());
            assertSame(list.getHead(),list.getTail());
            assertNull(list.getTail().getNext());
            assertEquals(1,list.getLength());
        });
    }

    @Test
    @DisplayName("RemoveDuplicates on empty list")
    void testRemoveDuplicates_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertDoesNotThrow(()->list.RemoveDuplicates(list.getHead(),java.util.Comparator.naturalOrder()));
        assertAll(()->{
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("RemoveDuplicates on one element")
    void testRemoveDuplicates_oneElement(){
        LinkedList<Integer> list=removeDuplicatesOf(7);
        assertEquals("7 ",list.toString());
        assertSame(list.getHead(),list.getTail());
    }

    @Test
    @DisplayName("RemoveDuplicates {4,4} => {4}, tail moves back to head")
    void testRemoveDuplicates_twoEqual(){
        LinkedList<Integer> list=removeDuplicatesOf(4,4);
        assertAll(()->{
            assertEquals("4 ",list.toString());
            assertSame(list.getHead(),list.getTail());
            assertEquals(1,list.getLength());
        });
    }

    @Test
    @DisplayName("RemoveDuplicates keeps the FIRST occurrence order (not sorted)")
    void testRemoveDuplicates_keepsFirstOccurrenceOrder(){
        assertEquals("3 1 2 ",removeDuplicatesOf(3,1,3,2,1).toString());
        assertEquals("5 4 3 2 1 ",removeDuplicatesOf(5,4,3,2,1,5).toString());
    }

    @Test
    @DisplayName("RemoveDuplicates when the tail is a duplicate, tail moves back")
    void testRemoveDuplicates_tailIsDuplicate(){
        LinkedList<Integer> list=removeDuplicatesOf(1,2,3,1);
        assertAll(()->{
            assertEquals("1 2 3 ",list.toString());
            assertEquals(3,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("RemoveDuplicates consecutive duplicates at the end {1,2,2,2} => {1,2}")
    void testRemoveDuplicates_consecutiveAtEnd(){
        LinkedList<Integer> list=removeDuplicatesOf(1,2,2,2);
        assertAll(()->{
            assertEquals("1 2 ",list.toString());
            assertEquals(2,list.getTail().getData());
            assertNull(list.getTail().getNext());
            assertEquals(2,list.getLength());
        });
    }

    @Test
    @DisplayName("RemoveDuplicates keeps the first nodes (addresses), not copies")
    void testRemoveDuplicates_keepsFirstNodes(){
        LinkedList<Integer> list=listOf(1,2,1,3,2);
        java.util.List<Node<Integer>> before=nodesOf(list);
        list.RemoveDuplicates(list.getHead(),java.util.Comparator.naturalOrder());
        java.util.List<Node<Integer>> after=nodesOf(list);
        assertAll(()->{
            assertEquals(3,after.size());
            assertSame(before.get(0),after.get(0));
            assertSame(before.get(1),after.get(1));
            assertSame(before.get(3),after.get(2));
        });
    }

    @Test
    @DisplayName("RemoveDuplicates removed nodes are detached")
    void testRemoveDuplicates_removedNodesDetached(){
        LinkedList<Integer> list=listOf(1,2,1,3);
        Node<Integer> duplicate=nodesOf(list).get(2);
        list.RemoveDuplicates(list.getHead(),java.util.Comparator.naturalOrder());
        assertNull(duplicate.getNext());
    }

    @Test
    @DisplayName("RemoveDuplicates with negative numbers and zero")
    void testRemoveDuplicates_negativesAndZero(){
        assertEquals("0 -1 2 ",removeDuplicatesOf(0,-1,0,-1,2).toString());
    }

    @Test
    @DisplayName("RemoveDuplicates compares by value, not reference (1000 > Integer cache)")
    void testRemoveDuplicates_bigNumbers(){
        assertEquals("1000 2000 ",removeDuplicatesOf(1000,2000,1000).toString());
    }

    @Test
    @DisplayName("RemoveDuplicates with Strings")
    void testRemoveDuplicates_withStrings(){
        LinkedList<String> list=new LinkedList<>();
        for(String s:new String[]{"b","a","b","c","a"})
            list.insertEnd(s);
        list.RemoveDuplicates(list.getHead(),java.util.Comparator.naturalOrder());
        assertEquals("b a c ",list.toString());
    }

    @Test
    @DisplayName("RemoveDuplicates twice gives the same result")
    void testRemoveDuplicates_twice(){
        LinkedList<Integer> list=removeDuplicatesOf(1,2,1,3);
        list.RemoveDuplicates(list.getHead(),java.util.Comparator.naturalOrder());
        assertEquals("1 2 3 ",list.toString());
        assertEquals(3,list.getLength());
    }

    @Test
    @DisplayName("RemoveDuplicates keeps the list structure valid")
    void testRemoveDuplicates_keepsStructure(){
        LinkedList<Integer> list=removeDuplicatesOf(4,2,4,8,2,6,8);
        assertAll(()->{
            assertEquals("4 2 8 6 ",list.toString());
            assertEquals(nodesOf(list).size(),list.getLength());
            assertNull(list.getTail().getNext());
            assertDoesNotThrow(()->list.dataIntegrity());
        });
    }

    @Test
    @DisplayName("list still works after RemoveDuplicates")
    void testRemoveDuplicates_thenInsertAndDelete(){
        LinkedList<Integer> list=removeDuplicatesOf(1,2,1,3,3);
        list.insertEnd(9);
        assertEquals("1 2 3 9 ",list.toString());
        assertEquals(9,list.deleteLast().getData());
        assertEquals(3,list.getTail().getData());
        assertEquals(1,list.deleteFirst().getData());
        assertEquals(2,list.getLength());
    }

    // ---------------------------- removeLastOcurrence ----------------------------

    // the only place that calls removeLastOcurrence: change here if the signature changes
    private void removeLast(LinkedList<Integer> list,Integer key){
        list.removeLastOcurrence(key,java.util.Comparator.naturalOrder());
    }

    @Test
    @DisplayName("removeLastOcurrence on empty list does nothing")
    void testRemoveLastOcurrence_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertDoesNotThrow(()->removeLast(list,1));
        assertAll(()->{
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("removeLastOcurrence key not found leaves list unchanged")
    void testRemoveLastOcurrence_whenNotFound(){
        LinkedList<Integer> list=listOf(1,2,3);
        assertDoesNotThrow(()->removeLast(list,9));
        assertAll(()->{
            assertEquals("1 2 3 ",list.toString());
            assertEquals(3,list.getLength());
            assertEquals(3,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("removeLastOcurrence on the only element empties the list")
    void testRemoveLastOcurrence_whenOneElement(){
        LinkedList<Integer> list=listOf(5);
        removeLast(list,5);
        assertAll(()->{
            assertNull(list.getHead());
            assertNull(list.getTail());
            assertEquals(0,list.getLength());
        });
    }

    @Test
    @DisplayName("removeLastOcurrence on the only element with wrong key does nothing")
    void testRemoveLastOcurrence_whenOneElementNotMatching(){
        LinkedList<Integer> list=listOf(5);
        removeLast(list,6);
        assertEquals("5 ",list.toString());
        assertSame(list.getHead(),list.getTail());
    }

    @Test
    @DisplayName("removeLastOcurrence single occurrence at head")
    void testRemoveLastOcurrence_onlyAtHead(){
        LinkedList<Integer> list=listOf(1,2,3);
        removeLast(list,1);
        assertAll(()->{
            assertEquals("2 3 ",list.toString());
            assertEquals(2,list.getHead().getData());
            assertEquals(2,list.getLength());
        });
    }

    @Test
    @DisplayName("removeLastOcurrence single occurrence in the middle")
    void testRemoveLastOcurrence_onlyInMiddle(){
        LinkedList<Integer> list=listOf(1,2,3);
        removeLast(list,2);
        assertEquals("1 3 ",list.toString());
        assertEquals(2,list.getLength());
    }

    @Test
    @DisplayName("removeLastOcurrence single occurrence at tail, tail moves back")
    void testRemoveLastOcurrence_onlyAtTail(){
        LinkedList<Integer> list=listOf(1,2,3);
        removeLast(list,3);
        assertAll(()->{
            assertEquals("1 2 ",list.toString());
            assertEquals(2,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("removeLastOcurrence {1,2,3,1,4,1,5} key 1 => {1,2,3,1,4,5}")
    void testRemoveLastOcurrence_manyOccurrences(){
        LinkedList<Integer> list=listOf(1,2,3,1,4,1,5);
        removeLast(list,1);
        assertAll(()->{
            assertEquals("1 2 3 1 4 5 ",list.toString());
            assertEquals(6,list.getLength());
            assertEquals(5,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("removeLastOcurrence last occurrence is the tail")
    void testRemoveLastOcurrence_lastIsTail(){
        LinkedList<Integer> list=listOf(7,2,7,3,7);
        removeLast(list,7);
        assertAll(()->{
            assertEquals("7 2 7 3 ",list.toString());
            assertEquals(3,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("removeLastOcurrence first occurrence is head, last is middle")
    void testRemoveLastOcurrence_headStays(){
        LinkedList<Integer> list=listOf(4,1,4,2);
        Node<Integer> oldHead=list.getHead();
        removeLast(list,4);
        assertAll(()->{
            assertEquals("4 1 2 ",list.toString());
            assertSame(oldHead,list.getHead());
        });
    }

    @Test
    @DisplayName("removeLastOcurrence {2,2} => {2}")
    void testRemoveLastOcurrence_twoEqual(){
        LinkedList<Integer> list=listOf(2,2);
        Node<Integer> first=list.getHead();
        removeLast(list,2);
        assertAll(()->{
            assertEquals("2 ",list.toString());
            assertSame(first,list.getHead());
            assertSame(first,list.getTail());
            assertNull(first.getNext());
            assertEquals(1,list.getLength());
        });
    }

    @Test
    @DisplayName("removeLastOcurrence all equal {3,3,3} => {3,3}")
    void testRemoveLastOcurrence_allEqual(){
        LinkedList<Integer> list=listOf(3,3,3);
        removeLast(list,3);
        assertEquals("3 3 ",list.toString());
        assertEquals(2,list.getLength());
    }

    @Test
    @DisplayName("removeLastOcurrence removes the LAST node object, not another one")
    void testRemoveLastOcurrence_removesRightNode(){
        LinkedList<Integer> list=listOf(1,9,1,9);
        java.util.List<Node<Integer>> before=nodesOf(list);
        removeLast(list,1);
        java.util.List<Node<Integer>> after=nodesOf(list);
        assertAll(()->{
            assertEquals(3,after.size());
            assertSame(before.get(0),after.get(0));
            assertSame(before.get(1),after.get(1));
            assertSame(before.get(3),after.get(2));
            assertNull(before.get(2).getNext(),"removed node must be detached");
        });
    }

    @Test
    @DisplayName("removeLastOcurrence called repeatedly removes from the back")
    void testRemoveLastOcurrence_repeated(){
        LinkedList<Integer> list=listOf(1,2,1,3,1);
        removeLast(list,1);
        assertEquals("1 2 1 3 ",list.toString());
        removeLast(list,1);
        assertEquals("1 2 3 ",list.toString());
        removeLast(list,1);
        assertEquals("2 3 ",list.toString());
        removeLast(list,1);
        assertEquals("2 3 ",list.toString());
    }

    @Test
    @DisplayName("removeLastOcurrence compares by value, not reference (1000 > Integer cache)")
    void testRemoveLastOcurrence_bigNumbers(){
        LinkedList<Integer> list=listOf(1000,5,1000);
        removeLast(list,Integer.valueOf(1000));
        assertEquals("1000 5 ",list.toString());
    }

    @Test
    @DisplayName("removeLastOcurrence keeps the list structure valid")
    void testRemoveLastOcurrence_keepsStructure(){
        LinkedList<Integer> list=listOf(5,1,5,2,5,3);
        removeLast(list,5);
        assertAll(()->{
            assertEquals("5 1 5 2 3 ",list.toString());
            assertEquals(nodesOf(list).size(),list.getLength());
            assertNull(list.getTail().getNext());
            assertDoesNotThrow(()->list.dataIntegrity());
        });
    }

    @Test
    @DisplayName("list still works after removeLastOcurrence")
    void testRemoveLastOcurrence_thenInsertAndDelete(){
        LinkedList<Integer> list=listOf(1,2,1);
        removeLast(list,1);
        list.insertEnd(9);
        assertEquals("1 2 9 ",list.toString());
        assertEquals(9,list.deleteLast().getData());
        assertEquals(2,list.getTail().getData());
    }

    // ---------------------------- moveToBAck ----------------------------

    // the only place that calls moveToBAck: change here if the signature changes
    private LinkedList<Integer> moveToBack(LinkedList<Integer> list,Integer key){
        list.moveToBAck(key,java.util.Comparator.naturalOrder());
        return list;
    }

    @Test
    @DisplayName("moveToBAck {1,2,3,2,4,1} key 1 => {2,3,2,4,1,1}")
    void testMoveToBack_example1(){
        LinkedList<Integer> list=moveToBack(listOf(1,2,3,2,4,1),1);
        assertAll(()->{
            assertEquals("2 3 2 4 1 1 ",list.toString());
            assertEquals(2,list.getHead().getData());
            assertEquals(1,list.getTail().getData());
            assertEquals(6,list.getLength());
        });
    }

    @Test
    @DisplayName("moveToBAck {1,2,3,1,2,4,1,7,1,8,1,1} key 1 => {2,3,2,4,7,8,1,1,1,1,1,1}")
    void testMoveToBack_example2(){
        LinkedList<Integer> list=moveToBack(listOf(1,2,3,1,2,4,1,7,1,8,1,1),1);
        assertAll(()->{
            assertEquals("2 3 2 4 7 8 1 1 1 1 1 1 ",list.toString());
            assertEquals(12,list.getLength());
        });
    }

    @Test
    @DisplayName("moveToBAck on empty list does nothing")
    void testMoveToBack_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertDoesNotThrow(()->moveToBack(list,1));
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test
    @DisplayName("moveToBAck on one element does nothing")
    void testMoveToBack_whenOneElement(){
        LinkedList<Integer> list=moveToBack(listOf(1),1);
        assertEquals("1 ",list.toString());
        assertSame(list.getHead(),list.getTail());
    }

    @Test
    @DisplayName("moveToBAck key not found leaves list unchanged")
    void testMoveToBack_whenNotFound(){
        LinkedList<Integer> list=moveToBack(listOf(1,2,3),9);
        assertEquals("1 2 3 ",list.toString());
        assertEquals(3,list.getTail().getData());
    }

    @Test
    @DisplayName("moveToBAck {1,2} key 1 => {2,1}")
    void testMoveToBack_twoElementsKeyAtHead(){
        LinkedList<Integer> list=moveToBack(listOf(1,2),1);
        assertAll(()->{
            assertEquals("2 1 ",list.toString());
            assertEquals(2,list.getHead().getData());
            assertEquals(1,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("moveToBAck {2,1} key 1 => {2,1} (already at the back)")
    void testMoveToBack_twoElementsKeyAtTail(){
        LinkedList<Integer> list=moveToBack(listOf(2,1),1);
        assertEquals("2 1 ",list.toString());
        assertEquals(1,list.getTail().getData());
    }

    @Test
    @DisplayName("moveToBAck key only in the middle: {2,1,3} => {2,3,1}")
    void testMoveToBack_keyInMiddle(){
        LinkedList<Integer> list=moveToBack(listOf(2,1,3),1);
        assertAll(()->{
            assertEquals("2 3 1 ",list.toString());
            assertEquals(3,list.getLength());
            assertEquals(3,nodesOf(list).size());
            assertEquals(1,list.getTail().getData());
        });
    }

    @Test
    @DisplayName("moveToBAck keys already at the back: unchanged")
    void testMoveToBack_alreadyAtBack(){
        LinkedList<Integer> list=moveToBack(listOf(2,3,1,1),1);
        assertEquals("2 3 1 1 ",list.toString());
    }

    @Test
    @DisplayName("moveToBAck all elements equal key: unchanged")
    void testMoveToBack_allKeys(){
        LinkedList<Integer> list=moveToBack(listOf(1,1,1),1);
        assertAll(()->{
            assertEquals("1 1 1 ",list.toString());
            assertEquals(3,list.getLength());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("moveToBAck consecutive keys at the front: {1,1,1,2} => {2,1,1,1}")
    void testMoveToBack_consecutiveAtFront(){
        LinkedList<Integer> list=moveToBack(listOf(1,1,1,2),1);
        assertAll(()->{
            assertEquals("2 1 1 1 ",list.toString());
            assertEquals(2,list.getHead().getData());
        });
    }

    @Test
    @DisplayName("moveToBAck consecutive keys in the middle: {2,1,1,3} => {2,3,1,1}")
    void testMoveToBack_consecutiveInMiddle(){
        LinkedList<Integer> list=moveToBack(listOf(2,1,1,3),1);
        assertEquals("2 3 1 1 ",list.toString());
        assertEquals(4,nodesOf(list).size());
    }

    @Test
    @DisplayName("moveToBAck keeps the order of the other elements")
    void testMoveToBack_keepsOrderOfOthers(){
        LinkedList<Integer> list=moveToBack(listOf(5,9,4,9,3,9,2),9);
        assertEquals("5 4 3 2 9 9 9 ",list.toString());
    }

    @Test
    @DisplayName("moveToBAck moves the nodes (addresses), not values")
    void testMoveToBack_movesNodes(){
        LinkedList<Integer> list=listOf(1,2,1,3);
        java.util.List<Node<Integer>> before=nodesOf(list);
        moveToBack(list,1);
        java.util.List<Node<Integer>> after=nodesOf(list);
        assertAll(()->{
            assertEquals(4,after.size());
            assertSame(before.get(1),after.get(0));
            assertSame(before.get(3),after.get(1));
            assertSame(before.get(0),after.get(2));
            assertSame(before.get(2),after.get(3));
        });
    }

    @Test
    @DisplayName("moveToBAck compares by value, not reference (1000 > Integer cache)")
    void testMoveToBack_bigNumbers(){
        LinkedList<Integer> list=moveToBack(listOf(1000,5,1000,6),Integer.valueOf(1000));
        assertEquals("5 6 1000 1000 ",list.toString());
    }

    @Test
    @DisplayName("moveToBAck keeps the list structure valid")
    void testMoveToBack_keepsStructure(){
        LinkedList<Integer> list=moveToBack(listOf(1,2,1,3,1,4),1);
        assertAll(()->{
            assertEquals(nodesOf(list).size(),list.getLength());
            assertNull(list.getTail().getNext());
            assertDoesNotThrow(()->list.dataIntegrity());
        });
    }

    @Test
    @DisplayName("list still works after moveToBAck")
    void testMoveToBack_thenInsertAndDelete(){
        LinkedList<Integer> list=moveToBack(listOf(1,2,3),1);
        list.insertEnd(9);
        assertEquals("2 3 1 9 ",list.toString());
        assertEquals(9,list.deleteLast().getData());
        assertEquals(1,list.getTail().getData());
        assertEquals(2,list.deleteFirst().getData());
    }

    // ---------------------------- Max ----------------------------

    // the only place that calls Max with natural order: change here if the signature changes
    private Integer maxOf(Integer... values){
        return listOf(values).Max(java.util.Comparator.naturalOrder());
    }

    @Test
    @DisplayName("Max on empty list returns null")
    void testMax_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertNull(list.Max(java.util.Comparator.naturalOrder()));
    }

    @Test
    @DisplayName("Max on one element returns it")
    void testMax_whenOneElement(){
        assertEquals(7,maxOf(7));
    }

    @Test
    @DisplayName("Max on two elements")
    void testMax_whenTwoElements(){
        assertAll(()->{
            assertEquals(5,maxOf(5,2));
            assertEquals(5,maxOf(2,5));
        });
    }

    @Test
    @DisplayName("Max at head, middle and tail")
    void testMax_position(){
        assertAll(()->{
            assertEquals(9,maxOf(9,1,2,3),"max at head");
            assertEquals(9,maxOf(1,2,9,3),"max in middle");
            assertEquals(9,maxOf(1,2,3,9),"max at tail");
        });
    }

    @Test
    @DisplayName("Max with sorted and reverse sorted input")
    void testMax_sortedInput(){
        assertEquals(5,maxOf(1,2,3,4,5));
        assertEquals(5,maxOf(5,4,3,2,1));
    }

    @Test
    @DisplayName("Max with duplicates of the max")
    void testMax_duplicateMax(){
        assertEquals(8,maxOf(8,3,8,1,8));
    }

    @Test
    @DisplayName("Max when all elements are equal")
    void testMax_allEqual(){
        assertEquals(4,maxOf(4,4,4));
    }

    @Test
    @DisplayName("Max with all negative numbers")
    void testMax_allNegative(){
        assertEquals(-1,maxOf(-5,-1,-3));
    }

    @Test
    @DisplayName("Max with negatives and zero")
    void testMax_negativesAndZero(){
        assertEquals(0,maxOf(-5,0,-3));
    }

    @Test
    @DisplayName("Max with Integer.MIN_VALUE and Integer.MAX_VALUE")
    void testMax_extremeValues(){
        assertEquals(Integer.MAX_VALUE,maxOf(Integer.MIN_VALUE,0,Integer.MAX_VALUE,-1));
        assertEquals(Integer.MIN_VALUE,maxOf(Integer.MIN_VALUE,Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("Max compares by value, not reference (1000 > Integer cache)")
    void testMax_bigNumbers(){
        assertEquals(2000,maxOf(1000,2000,1500));
    }

    @Test
    @DisplayName("Max with reverse comparator returns the minimum")
    void testMax_reverseComparator(){
        LinkedList<Integer> list=listOf(4,1,9,3);
        assertEquals(1,list.Max(java.util.Comparator.reverseOrder()));
    }

    @Test
    @DisplayName("Max with Strings")
    void testMax_withStrings(){
        LinkedList<String> list=new LinkedList<>();
        for(String s:new String[]{"pear","apple","zebra","mango"})
            list.insertEnd(s);
        assertEquals("zebra",list.Max(java.util.Comparator.naturalOrder()));
        assertEquals("apple",list.Max(java.util.Comparator.reverseOrder()));
    }

    @Test
    @DisplayName("Max does not change the list")
    void testMax_doesNotChangeList(){
        LinkedList<Integer> list=listOf(3,9,1);
        Node<Integer> oldHead=list.getHead();
        Node<Integer> oldTail=list.getTail();
        list.Max(java.util.Comparator.naturalOrder());
        assertAll(()->{
            assertEquals("3 9 1 ",list.toString());
            assertSame(oldHead,list.getHead());
            assertSame(oldTail,list.getTail());
            assertEquals(3,list.getLength());
        });
    }

    @Test
    @DisplayName("Max after the list changes")
    void testMax_afterChanges(){
        LinkedList<Integer> list=listOf(3,9,1);
        list.deleteWithKey(9);
        assertEquals(3,list.Max(java.util.Comparator.naturalOrder()));
        list.insertEnd(20);
        assertEquals(20,list.Max(java.util.Comparator.naturalOrder()));
        list.deleteLast();
        assertEquals(3,list.Max(java.util.Comparator.naturalOrder()));
    }

    @Test
    @DisplayName("Max on a long list (recursion depth)")
    void testMax_longList(){
        LinkedList<Integer> list=new LinkedList<>();
        for(int i=0;i<5000;i++)
            list.insertEnd(i%2==0?i:-i);
        assertEquals(4998,list.Max(java.util.Comparator.naturalOrder()));
    }

    // ---------------------------- arrangeOddAndEven ----------------------------

    private LinkedList<Integer> arrange(Integer... values){
        LinkedList<Integer> list=listOf(values);
        list.arrangeOddAndEven();
        return list;
    }

    @Test
    @DisplayName("arrangeOddAndEven on empty list does nothing")
    void testArrangeOddAndEven_whenEmpty(){
        LinkedList<Integer> list=new LinkedList<>();
        assertDoesNotThrow(()->list.arrangeOddAndEven());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test
    @DisplayName("arrangeOddAndEven on one element")
    void testArrangeOddAndEven_whenOneElement(){
        LinkedList<Integer> list=arrange(1);
        assertEquals("1 ",list.toString());
        assertSame(list.getHead(),list.getTail());
    }

    @Test
    @DisplayName("arrangeOddAndEven on two elements: unchanged")
    void testArrangeOddAndEven_whenTwoElements(){
        LinkedList<Integer> list=arrange(1,2);
        assertEquals("1 2 ",list.toString());
        assertEquals(2,list.getTail().getData());
    }

    @Test
    @DisplayName("arrangeOddAndEven {1,2,3} => {1,3,2}")
    void testArrangeOddAndEven_whenThree(){
        LinkedList<Integer> list=arrange(1,2,3);
        assertAll(()->{
            assertEquals("1 3 2 ",list.toString());
            assertEquals(1,list.getHead().getData());
            assertEquals(2,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("arrangeOddAndEven {1,2,3,4} => {1,3,2,4}")
    void testArrangeOddAndEven_whenFour(){
        LinkedList<Integer> list=arrange(1,2,3,4);
        assertAll(()->{
            assertEquals("1 3 2 4 ",list.toString());
            assertEquals(4,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("arrangeOddAndEven {1..7} => {1,3,5,7,2,4,6}")
    void testArrangeOddAndEven_whenSeven(){
        LinkedList<Integer> list=arrange(1,2,3,4,5,6,7);
        assertAll(()->{
            assertEquals("1 3 5 7 2 4 6 ",list.toString());
            assertEquals(6,list.getTail().getData());
            assertEquals(7,list.getLength());
        });
    }

    @Test
    @DisplayName("arrangeOddAndEven {1..6} even length => {1,3,5,2,4,6}")
    void testArrangeOddAndEven_whenSix(){
        LinkedList<Integer> list=arrange(1,2,3,4,5,6);
        assertAll(()->{
            assertEquals("1 3 5 2 4 6 ",list.toString());
            assertEquals(6,list.getTail().getData());
            assertNull(list.getTail().getNext());
        });
    }

    @Test
    @DisplayName("arrangeOddAndEven {11,33,55,4,50,17,8} => {11,55,50,8,33,4,17}")
    void testArrangeOddAndEven_byPositionNotValue(){
        LinkedList<Integer> list=arrange(11,33,55,4,50,17,8);
        assertEquals("11 55 50 8 33 4 17 ",list.toString());
    }

    @Test
    @DisplayName("arrangeOddAndEven {10,20,3,7,15} => {10,3,15,20,7}")
    void testArrangeOddAndEven_example10(){
        LinkedList<Integer> list=arrange(10,20,3,7,15);
        assertEquals("10 3 15 20 7 ",list.toString());
        assertEquals(7,list.getTail().getData());
    }

    @Test
    @DisplayName("arrangeOddAndEven with equal values")
    void testArrangeOddAndEven_equalValues(){
        LinkedList<Integer> list=listOf(5,5,5,5);
        java.util.List<Node<Integer>> before=nodesOf(list);
        list.arrangeOddAndEven();
        java.util.List<Node<Integer>> after=nodesOf(list);
        assertAll(()->{
            assertEquals("5 5 5 5 ",list.toString());
            assertSame(before.get(0),after.get(0));
            assertSame(before.get(2),after.get(1));
            assertSame(before.get(1),after.get(2));
            assertSame(before.get(3),after.get(3));
        });
    }

    @Test
    @DisplayName("arrangeOddAndEven moves the nodes (addresses), not values")
    void testArrangeOddAndEven_movesNodes(){
        LinkedList<Integer> list=listOf(1,2,3,4,5);
        java.util.List<Node<Integer>> before=nodesOf(list);
        list.arrangeOddAndEven();
        java.util.List<Node<Integer>> after=nodesOf(list);
        int[] order={0,2,4,1,3};
        assertEquals(5,after.size());
        for(int i=0;i<order.length;i++)
            assertSame(before.get(order[i]),after.get(i),"wrong node at position "+i);
    }

    @Test
    @DisplayName("arrangeOddAndEven keeps the list structure valid")
    void testArrangeOddAndEven_keepsStructure(){
        for(int n=0;n<=9;n++){
            LinkedList<Integer> list=new LinkedList<>();
            for(int i=1;i<=n;i++)
                list.insertEnd(i);
            int size=n;
            assertDoesNotThrow(()->list.arrangeOddAndEven(),"length "+size);
            assertEquals(n,nodesOf(list).size(),"lost nodes, length "+n);
            assertEquals(n,list.getLength());
            assertDoesNotThrow(()->list.dataIntegrity(),"length "+size);
        }
    }

    @Test
    @DisplayName("arrangeOddAndEven twice")
    void testArrangeOddAndEven_twice(){
        LinkedList<Integer> list=arrange(1,2,3,4,5,6);
        list.arrangeOddAndEven(); // 1 3 5 2 4 6 -> 1 5 4 3 2 6
        assertEquals("1 5 4 3 2 6 ",list.toString());
    }

    @Test
    @DisplayName("list still works after arrangeOddAndEven")
    void testArrangeOddAndEven_thenInsertAndDelete(){
        LinkedList<Integer> list=arrange(1,2,3,4,5);
        list.insertEnd(9);
        assertEquals("1 3 5 2 4 9 ",list.toString());
        assertEquals(9,list.deleteLast().getData());
        assertEquals(4,list.getTail().getData());
        assertEquals(1,list.deleteFirst().getData());
        assertEquals("3 5 2 4 ",list.toString());
    }
}