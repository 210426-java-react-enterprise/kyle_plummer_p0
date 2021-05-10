package com.revature.p0.utils.datastructures;

import com.revature.p0.utils.datastructures.List;

public class ArrayList<T> implements List {
    //private T[] array;
    private Object[] array;
    private int size;
    private int maxSize;

    public ArrayList() {
        maxSize = 2;
        size = 0;
        array = new Object[maxSize];
    }

    public ArrayList(int size) {
        maxSize = size;
        size = 0;
        array = new Object[size];
    }

    public ArrayList(T ...t) {
        maxSize = size = t.length;
        array = new Object[size];

        for (int i = 0; i < size; ++i) {
            array[i] = t[i];
        }
    }


    @Override
    public void add(Object o) {
        //if size == maxSize we need to grow array
        if (size == maxSize) {
            this.growArray();
        }

        array[size] = o;
        size++;
    }

    @Override
    public void add(int index, Object o) throws IndexOutOfBoundsException {
        if(index >= maxSize || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds. Max size is currently: " + maxSize);
        }
        else if(index >= size) {
            size = index + 1;
        }
        array[index] = o;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException{
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds. Size  is currently: " + size);
        }
        return array[index];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
    }

    @Override
    public int contains(Object o) {
        for (int i = 0; i < size; i++) {
            if(array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds. Size  is currently: " + size);
        }
        array[index] = null;

    }

    @Override
    public int size() {
        return size;
    }


    private void growArray(){
        //System.out.println("Growing Array from " + maxSize + " to " + maxSize * 2);
        //set up new array
        maxSize = maxSize * 2;
        Object[] tempArray = array;
        array = new Object[maxSize];

        //copy to new array
        for (int i = 0; i < size; i++) {
            array[i] = tempArray[i];
        }
    }
}
