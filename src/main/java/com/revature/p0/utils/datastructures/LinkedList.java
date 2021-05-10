package com.revature.p0.utils.datastructures;

import java.util.Iterator;


public class LinkedList<T> implements List<T>, Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;



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

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

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

    @Override
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(index + " is out of bounds.");
        }
        Node<T> cursor = head;
        int i = 0;
        while(i < index) {
            cursor = cursor.next;
            i++;
        }
        cursor.next.prev = cursor.prev;
        cursor.prev.next = cursor.next;
        cursor.prev = null;
        cursor.next = null;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> cursor = head;

            @Override
            public boolean hasNext() {
                if (cursor == null){
                    return false;
                }
                return true;
            }

            @Override
            public T next() {
                T t = cursor.obj;
                cursor = cursor.next;
                return t;
            }
        };
    }


    private class Node<T> {
        Node<T> next;
        Node<T> prev;
        T obj;

        Node() {

        }

        Node(T t) {
            obj = t;
        }

        Node(T t, Node<T> next) {
            this(t);
            this.next = next;
        }

        Node(T t, Node<T> next, Node<T> prev) {
            this(t, next);
            this.prev = prev;
        }
    }


}
