package com.iterator_design_pattern.Collections_impl;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.iterator_design_pattern.Collections.CustomIterator;
import com.iterator_design_pattern.Collections_impl.CustomArrayList.ListData;

/**
 * CustomListIterator is an implementation of the {@link CustomIterator}
 * interface, which provides an iterator
 * for traversing a collection in both forward and backward directions. It
 * supports the ability to remove elements
 * while iterating, and ensures that modifications to the collection during
 * iteration are properly handled.
 * <p>
 * Example usage:
 * 
 * <pre>
 * CustomListIterator&lt;String&gt; iterator = new CustomListIterator&lt;&gt;(value, modificationCount, size);
 * 
 * // Forward iteration
 * while (iterator.hasNext()) {
 *     System.out.println(iterator.next());
 * }
 * 
 * // Backward iteration
 * while (iterator.hasPrevious()) {
 *     System.out.println(iterator.previous());
 * }
 * </pre>
 * </p>
 * 
 * @param <E> The type of elements returned by this iterator.
 * @see CustomIterator
 * @see java.util.Iterator
 * @author Kartik Sethi
 * @version 1.0
 * @since 2025-04-28
 */
public class CustomListIterator<E> implements CustomIterator<E> {
    private int cursor = -1; // Position of the next element to be returned
    private int lastReturned = -1; // Index of the last element returned
    private final ListData data; // stores the data of list
    private int requiredCount; // The initial value of modificationCount to detect concurrent modifications
    private final Object[] value; // The array backing the collection

    /**
     * Constructs a CustomListIterator with the provided values and modification
     * count.
     *
     * @param value             The array backing the collection.
     * @param modificationCount The current modification count of the collection.
     * @param size              The size of the collection.
     */
    CustomListIterator(Object[] value, ListData data) {
        this.value = value;
        this.requiredCount = data.modificationCount;
        this.data = data;
    }

    /**
     * Returns true if there are more elements when traversing the collection in the
     * forward direction.
     * 
     * @return true if there is at least one more element in the forward direction;
     *         false otherwise.
     */
    @Override
    public boolean hasNext() {
        return cursor < data.size - 1;
    }

    /**
     * Returns true if there are more elements when traversing the collection in the
     * reverse direction.
     * 
     * @return true if there is at least one more element in the reverse direction;
     *         false otherwise.
     */
    @Override
    public boolean hasPrevious() {
        return cursor > 0;
    }

    /**
     * Returns the next element in the iteration.
     * 
     * @return the next element.
     * @throws NoSuchElementException if there are no more elements to iterate over.
     */
    @SuppressWarnings("unchecked")
    @Override
    public E next() {
        checkForConcurrentModification();
        if (!hasNext())
            throw new NoSuchElementException("No more elements in the collection.");
        cursor++;
        return (E) value[lastReturned = cursor];
    }

    /**
     * Returns the previous element in the iteration.
     * 
     * @return the previous element.
     * 
     * @implSpec
     *           This implementation throws an
     *           {@code NoSuchElementException} if there are no more elements to
     *           iterate over
     *           in the reverse direction.
     * 
     * @throws NoSuchElementException {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E previous() {
        checkForConcurrentModification();
        if (!hasPrevious())
            throw new NoSuchElementException("No more elements in the collection.");
        cursor--;
        return (E) value[lastReturned = cursor];
    }

    /**
     * Returns the index of the previous element in the iteration.
     * 
     * @return the index of the previous element.
     */
    @Override
    public int previousIndex() {
        return cursor - 1;
    }

    /**
     * Removes the last element returned by the iterator.
     * 
     * @implSpec
     *           This implementation throws an
     *           {@code IllegalStateException} if neither next nor previous has been
     *           called,
     *           or if remove is called
     *           after an element has already been removed.
     * 
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public void remove() {
        if (lastReturned == -1) {
            throw new IllegalStateException("Next or previous must be called before remove.");
        }
        System.arraycopy(value, lastReturned + 1, value, lastReturned, data.size - lastReturned - 1);
        value[data.size -= 1] = null;
        data.modificationCount += 1;
        cursor--;
        lastReturned = -1;
    }

    /**
     * Checks if the collection has been modified during iteration.
     * 
     * @implSpec
     *           This implementation throws an
     *           {@code NoSuchElementException} if the collection was modified
     *           during
     *           iteration.
     * 
     * @throws NoSuchElementException {@inheritDoc}
     */
    private void checkForConcurrentModification() {
        if (data.modificationCount != requiredCount) {
            throw new ConcurrentModificationException("Collection was modified during iteration.");
        }
    }
}
