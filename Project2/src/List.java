package src;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class represents a dynamic list of objects, allowing for adding, removing, and searching for objects.
 * It dynamically resizes the list when full.
 *
 * @author Sydney Pacheco
 */


public class List<E> implements Iterable<E> {
    private static final int INITIAL_CAPACITY = 4;
    private static final int NOT_FOUND = -1;

    private E[] objects;
    private int size;

    public List() {
        this.objects = (E[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        E[] newobjects = (E[]) new Object[objects.length + 4];
        System.arraycopy(objects, 0, newobjects, 0, objects.length);
        objects = newobjects;
    }

    public boolean contains(E e) {
        return find(e) != NOT_FOUND;
    }


    private void swap(int i, int j) {
        E temp = objects[i];
        objects[i] = objects[j];
        objects[j] = temp;
    }

    public void add(E e) {
        if (size == objects.length) {
            grow();
        }
        objects[size] = e;
        size++;
    }

    public void remove(E e) {
        int index = find(e);
        if (index != NOT_FOUND) {
            for (int i = index; i < size - 1; i++) {
                objects[i] = objects[i + 1];
            }
            objects[size - 1] = null;
            size--;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return objects[index++];
            }
        };
    }

    public int getSize(){
        return size;
    }

    public E get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return objects[index];
    }

    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        objects[index] = e;
    }
}
