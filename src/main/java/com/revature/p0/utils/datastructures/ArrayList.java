package com.revature.p0.utils.datastructures;

/**
 * A fairly simple arraylist implementation extending custom list interface.
 * Default size is 2, grows by size * 2 when needed.
 * When an element is added or removed at an index other elements are not re-arranged.
 *
 * @param <T>
 */
public class ArrayList<T> implements List<T> {
    private Object[] array;
    private int size;
    private int maxSize;

    /**
     * Default constructor, creates an empty underlying array with maxSize 2
     */
    public ArrayList() {
        maxSize = 2;
        size = 0;
        array = new Object[maxSize];
    }

    /**
     * Sized constructor, creates an empty object with maxSize size
     * @param size the initial size of the underlying array
     */
    public ArrayList(int size) {
        maxSize = size;
        size = 0;
        array = new Object[size];
    }

    /**
     * Element list constructor, takes in variable number of objects and creates an underlying
     * array large enough to fit them.
     * @param t
     */
    public ArrayList(T ...t) {
        maxSize = size = t.length;
        array = new Object[size];

        for (int i = 0; i < size; ++i) {
            array[i] = t[i];
        }
    }


    /**
     * Adds an object to the underlying array after all previously added objects.
     * If array needs to grow, it invokes grow method.
     * @param o object to be added
     */
    @Override
    public void add(Object o) {
        //if size == maxSize we need to grow array
        if (size == maxSize) {
            this.growArray();
        }

        array[size] = o;
        size++;
    }

    /**
     * Adds object at specified index, advancing the size of the underlying array. This will
     * overwrite an existing element, rather than shift them
     * @param index index location where object will be inserted
     * @param o object to be inserted
     * @throws IndexOutOfBoundsException
     */
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

    /**
     * gets the object located at supplied index
     * @param index index of object to get
     * @return object located at index
     * @throws IndexOutOfBoundsException
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException{
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds. Size  is currently: " + size);
        }
        return (T)array[index];
    }

    /**
     * Emptys the underlying array by setting it's private reference to null and allowing
     * the old array to be garbage collected.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
    }

    /**
     * Check if object o is found within underlying array, using Object.equals() method
     * @param o object to search for
     * @return index location of first instance of matching object. -1 if not found.
     */
    @Override
    public int contains(Object o) {
        for (int i = 0; i < size; i++) {
            if(array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes object at specified index from underlying array, setting to null.
     * @param index index of object to remove from array
     */
    @Override
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds. Size  is currently: " + size);
        }
        array[index] = null;

    }

    /**
     * returns size of array. This is the one greater than the index of the most advanced stored object,
     * not the maxSize which controls growth of the underlying array.
     * @return one greater than index of most advanced stored object
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Doubles the size of the underlying array by creating a new array and copying the
     * contents of the previous array into it.
     */
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
