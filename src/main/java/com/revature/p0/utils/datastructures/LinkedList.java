package com.revature.p0.utils.datastructures;

import java.util.Iterator;

/**
 * Simple doubly linkedlist implementation, extending custom list interface.
 * Also implements Iterable interface.
 * @param <T>
 */
public class LinkedList<T> implements List<T>, Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;


    /**
     * Adds an object to the end of the linked list
     * @param t object to be added to the list
     */
    @Override
    public void add(T t) {
        Node<T> newNode = new Node<T>(t);
        if (head == null) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    /**
     * Adds an object to the linked list at the specified index, splicing into place
     * and effectively shifting all further objects
     * @param index position to add object
     * @param t object to be added
     */
    @Override
    public void add(int index, T t) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(index + " is out of bounds.");
        }
        Node<T> cursor = head;
        Node<T> newNode = new Node<T>(t);
        int i = 0;
        while(i < index) {
            cursor = cursor.next;
            i++;
        }
        cursor.prev.next = newNode;
        newNode.prev = cursor.prev;
        newNode.next = cursor;
        cursor.prev = newNode;
        size++;
    }

    /**
     * gets the object found at provided index position
     * @param index location of the object to get
     * @return object found at index position
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(index + " is out of bounds.");
        }
        Node<T> cursor = head;
        int i = 0;
        while(i < index) {
            cursor = cursor.next;
            i++;
        }
        return cursor.obj;
    }

    /**
     * Clears the linked list by setting head and tail to null.
     *
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Searches linked list for an object using Object.equals() to compare
     * returns the index of the first matching object found. -1 if not found.
     * @param t the object to match
     * @return index of the first matching object found. -1 if not found
     */
    @Override
    public int contains(T t) {
        int index = 0;
        Node<T> cursor = head;
        while(cursor != null) {
            if(cursor.obj.equals(t)){
                return index;
            }
            cursor = cursor.next;
            index++;
        }
        return -1;
    }

    /**
     * removes an object from linked list and splices the two resulting separate lists
     * together.
     * @param index the location of the object to be removed.
     */
    @Override
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(index + " is out of bounds.");
        }

        if(index == 0) {
            head = head.next;
            size--;
        } else {

            Node<T> cursor = head;
            int i = 0;
            while (i < index) {
                cursor = cursor.next;
                i++;
            }
            cursor.next.prev = cursor.prev;
            cursor.prev.next = cursor.next;
            cursor.prev = null;
            cursor.next = null;
            size--;
        }
    }


    /**
     * returns the size of the linked list
     * @return size of linked list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * iterator implementation
     * @return returns an iterator object to traverse the linked list
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> cursor = head;

            /**
             * checks if the linked list has another node, testing if the cursor points to a node
             * or if it is null
             * @return true if the cursor points to a node, false if the cursor node reference is null
             */
            @Override
            public boolean hasNext() {
                if (cursor == null){
                    return false;
                }
                return true;
            }

            /**
             * returns the node the cursor points to, then advances the cursor to the next node
             * @return the object at the location of the cursor
             */
            @Override
            public T next() {
                T t = cursor.obj;
                cursor = cursor.next;
                return t;
            }
        };
    }


    /**
     * Private node class contains a reference to object of list type, a reference to the next node, and
     * to the previous node.
     * @param <T>
     */
    private class Node<T> {
        Node<T> next;
        Node<T> prev;
        T obj;

        /**
         * empty constructor creates an empty node
         */
        Node() {

        }

        /**
         * creates a node and stores an object by reference
         * @param t the stored object
         */
        Node(T t) {
            obj = t;
        }

        /**
         * creates a node which stores an object by reference and has a reference to another node
         * @param t object to be stored
         * @param next next node in list
         */
        Node(T t, Node<T> next) {
            this(t);
            this.next = next;
        }

        /**
         * creates a node which stores an object by reference and has refrences to two nodes,
         * previous and next in the list
         * @param t the object to be stored
         * @param next reference to next node in list
         * @param prev reference to previous node in list
         */
        Node(T t, Node<T> next, Node<T> prev) {
            this(t, next);
            this.prev = prev;
        }
    }


}
