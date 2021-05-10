package com.revature.p0.utils.datastructures;

public interface List<T> {
    void add(T t);//insert to end of list
    void add(int index, T t);//insert at index
    T get(int index);//get T at index
    void clear(); //clears all elements
    int contains(T t);//returns index of object if contained, if not found return -1
    void remove(int index);//removes element at index
    int size();

}
