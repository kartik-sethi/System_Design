package com.iterator_pattern_arraylist.Collections;

import com.iterator_pattern_arraylist.Collections_impl.CustomArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CustomIterator is an extension of the standard {@link Iterator} interface
 * that provides additional functionality for traversing a collection in both
 * directions (forward and backward).
 * It supports operations such as checking if there is a previous element and
 * retrieving the index of the previous element.
 * This interface is designed to be used with custom collections like
 * {@link CustomArrayList}.
 * <p>
 * Example usage:
 * 
 * <pre>
 * CustomIterator&lt;String&gt; iterator = customList.iterator();
 * while (iterator.hasNext()) {
 *     System.out.println(iterator.next());
 * }
 * 
 * // Iterating in reverse
 * while (iterator.hasPrevious()) {
 *     System.out.println(iterator.previous());
 * }
 * </pre>
 * </p>
 * 
 * @param <E> Type of elements returned by this iterator.
 * @see Iterator
 * @see CustomArrayList
 * @author Kartik Sethi
 * @version 1.0
 * @since 2025-04-28
 */
public interface CustomIterator<E> extends Iterator<E> {

    /**
     * Checks if there are more elements when iterating backward.
     * 
     * @return true if there is a previous element, false otherwise.
     */
    boolean hasPrevious();

    /**
     * Returns the previous element in the iteration.
     * 
     * @return the previous element.
     * @throws NoSuchElementException if there is no previous element.
     */
    E previous();

    /**
     * Returns the index of the previous element in the iteration.
     * 
     * @return the index of the previous element, or -1 if there is no previous
     *         element.
     */
    int previousIndex();
}
