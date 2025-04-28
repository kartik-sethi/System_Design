package com.iterator_design_pattern.Collections_impl;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SequencedCollection;

import com.iterator_design_pattern.Collections.CustomIterator;
import com.iterator_design_pattern.Collections.CustomList;

/**
 * Example usage of CustomArrayList:
 * <p>
 * This example demonstrates how to create a CustomArrayList, add elements to
 * it,
 * and iterate through it using the CustomListIterator.
 * </p>
 * 
 * <pre>
 * CustomArrayList&lt;String&gt; list = new CustomArrayList&lt;&gt;();
 * list.add("Apple");
 * list.add("Banana");
 * list.add("Cherry");
 * 
 * // Iterate through the list
 * for (String item : list) {
 *     System.out.println(item);
 * }
 * </pre>
 * 
 * CustomArrayList is a custom implementation of a list that adheres to the
 * Iterator Design Pattern. It provides various methods for list manipulation,
 * including adding elements, getting the index of an element, iterating over
 * the list, and more.
 * <p>
 * The list also supports custom iteration via the {@link CustomListIterator},
 * which implements the {@link CustomIterator} interface, allowing efficient
 * traversal of the list and removal of elements during iteration.
 * </p>
 * 
 * @param <E> Type of elements in the list.
 * @see CustomList
 * @see java.util.ArrayList
 * @see CustomIterator
 * @see CustomListIterator
 * @author Kartik Sethi
 * @version 1.0
 * @since 2025-04-28
 */
public class CustomArrayList<E> extends AbstractCollection<E> implements CustomList<E>, Cloneable {

    private static final transient int INITIAL_THRESHOLD = 16;
    private transient final ListData data = new ListData();
    private transient int capacity;
    private transient Object[] value;

    /**
     * Constructs an empty CustomArrayList with an initial capacity of 16.
     */
    public CustomArrayList() {
        this.value = new Object[INITIAL_THRESHOLD];
        this.capacity = INITIAL_THRESHOLD;
    }

    /**
     * Constructs a CustomArrayList containing the elements of the specified
     * collection.
     * 
     * @param collection The collection whose elements are to be added to this list.
     */
    public CustomArrayList(Collection<? extends E> collection) {
        this.value = new Object[INITIAL_THRESHOLD];
        this.capacity = INITIAL_THRESHOLD;
        this.addAll(collection);
    }

    /**
     * Constructs a CustomArrayList with the specified initial capacity.
     * 
     * @param capacity The initial capacity of the list.
     */
    public CustomArrayList(int capacity) {
        this.capacity = INITIAL_THRESHOLD;
        this.ensureCapacity(capacity);
        this.value = new Object[this.capacity];
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return the size of the list.
     */
    @Override
    public int size() {
        return data.size;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return data.size == 0;
    }

    /**
     * Retrieves the element at the specified index in this list.
     * 
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @implSec
     *          This implementation throws an {@code IndexOutOfBoundsException} if
     *          the
     *          index is out of range
     * 
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E get(int index) {
        // Check if the index is within the valid range
        if (index < 0 || index >= data.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + data.size);
        }

        // Return the element at the specified index
        return (E) value[index];
    }

    /**
     * Returns the index of the first occurrence of the specified element, or -1 if
     * not found.
     * 
     * @param o The element to search for.
     * @return the index of the element or -1 if not found.
     */
    @Override
    public int indexOf(Object o) {
        CustomIterator<E> it = new CustomListIterator<>(value, data);
        if (o == null) {
            while (it.hasNext())
                if (it.next() == null)
                    return it.previousIndex();
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return it.previousIndex();
        }
        return -1;
    }

    /**
     * Adds an element to the list, increasing the size and modifying the internal
     * state.
     * 
     * @param element The element to be added to the list.
     * @return true if the element was added successfully.
     */
    @Override
    public boolean add(E element) {
        data.modificationCount++;
        ensureCapacity(data.size + 1);
        this.value[data.size++] = element;
        return true;
    }

    /**
     * Adds all elements from the specified collection to the list.
     * 
     * @param collection The collection whose elements are to be added.
     * @return true if any elements were added.
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        data.modificationCount++;
        Object[] arr = collection.toArray();
        int len = arr.length;
        if (len == 0)
            return false;
        this.ensureCapacity(data.size + len);
        System.arraycopy(arr, 0, value, data.size, len);
        data.size += len;
        return true;
    }

    /**
     * Ensures that the list has enough capacity to store at least the specified
     * number of elements.
     * 
     * @param minimumCapacity The minimum capacity the list should have.
     */
    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > this.capacity) {
            this.newCapacity(minimumCapacity);
            this.value = Arrays.copyOf(this.value, this.capacity);
        }
    }

    /**
     * Expands the internal array's capacity to meet the minimum requirement.
     * 
     * @param minimumCapacity The minimum capacity the array should have.
     */
    private void newCapacity(int minimumCapacity) {
        while (this.capacity < minimumCapacity) {
            if (this.capacity == Integer.MAX_VALUE) {
                throw new OutOfMemoryError("Required length extends implementation limit");
            }

            if (this.capacity == 1073741824) {
                this.capacity = Integer.MAX_VALUE;
            } else {
                this.capacity <<= 1;
            }
        }
    }

    /**
     * Returns an iterator over the elements in the list.
     * 
     * @return a CustomListIterator for the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new CustomListIterator<>(value, data);
    }

    /**
     * Returns an array containing all the elements in the list in proper sequence.
     * 
     * @return an array containing all the elements in the list.
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(value, data.size);
    }

    /**
     * Returns an array containing all the elements in the list in proper sequence.
     * If the provided array is large enough, it will be used, otherwise a new array
     * will be created.
     * 
     * @param a the array into which the elements of the list should be stored.
     * @return an array containing all the elements in the list.
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < data.size) {
            return (T[]) Arrays.copyOf(value, data.size, a.getClass());
        }
        System.arraycopy(value, 0, a, 0, data.size);
        if (a.length > data.size)
            a[data.size] = null;
        return a;
    }

    /**
     * Clears all elements from the list, resetting its size to 0.
     */
    @Override
    public void clear() {
        for (int i = 0; i < data.size; i++) {
            value[i] = null;
        }
        data.size = 0;
    }

    /**
     * Reverses the list. This method is not yet supported.
     * 
     * @implspec
     *           This implementation always throws an
     *           {@Code UnsupportedOperationException}.
     * 
     * @throws UnsupportedOperationException {@inheritDoc}
     */
    @Override
    public SequencedCollection<E> reversed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public class ListData {
        int size;
        int modificationCount;
    }
}
