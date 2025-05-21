package com.iterator_pattern_arraylist.Collections;

import java.util.SequencedCollection;

/**
 * CustomList Interface that extends SequencedCollection.
 * <p>
 * This interface represents a custom list structure, providing methods for
 * iterating, adding, and finding elements.
 * The CustomList adheres to the Iterator Design Pattern by defining methods for
 * custom iteration.
 * </p>
 * 
 * @param <E> Type of elements in the list.
 */
public interface CustomList<E> extends SequencedCollection<E> {

    /**
     * Returns the index of the specified element in the list.
     * 
     * @param e the element whose index is to be found
     * @return the index of the element if found, -1 otherwise.
     */
    int indexOf(E e);

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
    E get(int index);

}
