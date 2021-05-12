package com.revature.p0.utils;


import com.revature.p0.utils.datastructures.LinkedList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class LinkedListTest {

    private LinkedList<String> sut;

    @Before
    public void testSetUp() {
        sut = new LinkedList<String>();
    }

    @After
    public void testTearDown() {
        sut = null;
    }

    @Test
    public void test_emptyLinkedListSize() {
        int expectedSize = 0;

        Assert.assertEquals(expectedSize, sut.size());
    }

    @Test
    public void test_nonEmptyLinkedListSize() {
        int expectedSize = 1;

        sut.add("test");

        Assert.assertEquals(expectedSize, sut.size());
    }

    @Test
    public void test_linkedListGetIndex() {
        int expectedSize = 5;

        for (int i = 0; i < expectedSize; i++) {
            sut.add("index " + i);
        }

        Assert.assertEquals(expectedSize, sut.size());
        Assert.assertEquals("index 0", sut.get(0));
        Assert.assertEquals("index 2", sut.get(2));
        Assert.assertEquals("index 4", sut.get(4));
    }

    @Test
    public void test_linkedListClear() {
        int expectedSize = 0;

        sut.add("test");
        sut.add("test");
        sut.clear();

        Assert.assertEquals(expectedSize, sut.size());
    }

    @Test
    public void test_removeElementFromLinkedList() {
        int initialSize = 5;

        for (int i = 0; i < initialSize; i++) {
            sut.add("index " + i);
        }
        sut.remove(3);
        sut.remove(0);

        Assert.assertEquals("index 1", sut.get(0));
        Assert.assertEquals("index 4", sut.get(2));
    }

    @Test
    public void test_linkedListSearch() {
        int initialSize = 5;
        int index = 3;

        for (int i = 0; i < initialSize; i++) {
            sut.add("index " + i);
        }

        Assert.assertEquals(index, sut.contains("index 3"));
        Assert.assertEquals(-1, sut.contains("String not present"));
    }


    @Test
    public void test_addElementToLinkedListAtIndex() {
        int initialSize = 5;

        for (int i = 0; i < initialSize; i++) {
            sut.add("index " + i);
        }
        sut.add(3, "New index 3");

        Assert.assertEquals(6, sut.size());
        Assert.assertEquals("index 2", sut.get(2));
        Assert.assertEquals("New index 3", sut.get(3));
        Assert.assertEquals("index 3", sut.get(4));
    }

    @Test
    public void test_linkedListIteratorTraversal() {
        int initialSize = 5;

        for (int i = 0; i < initialSize; i++) {
            sut.add("index " + i);
        }
        Iterator<String> iter = sut.iterator();

        Assert.assertTrue(iter.hasNext());
        for (int i = 0; i < initialSize; i++) {
            Assert.assertEquals("index " + i, iter.next());
        }
        Assert.assertFalse(iter.hasNext());

    }

}
