
package com.example.Builder;

import java.util.Arrays;

/**
 * A mutable implementation of a string — a custom version of
 * {@link StringBuilder}.
 *
 * <p>
 * This is an example of how the <strong>Builder Design Pattern</strong> works.
 * </p>
 *
 * <p>
 * <strong>Example Usage:</strong>
 * </p>
 * 
 * <pre>
 * {
 *     @code
 *     CustomStringBuilder s = new CustomStringBuilder();
 *     s.append('c');
 *     s.append("sequence");
 *     s.append(123);
 *     System.out.println(s.toString());
 *     // Output: "csequence123"
 * }
 * </pre>
 *
 * @author Kartik Sethi
 * @see java.lang.StringBuilder
 */
public final class CustomStringBuilder implements Cloneable {

    private transient static final int INITIAL_THRESHOLD = 16;
    private transient int size;
    private transient int capacity;
    private transient char[] value;

    public CustomStringBuilder() {
        this.value = new char[INITIAL_THRESHOLD];
        this.capacity = INITIAL_THRESHOLD;
    }

    public CustomStringBuilder(String s) {
        this.value = new char[INITIAL_THRESHOLD];
        this.capacity = INITIAL_THRESHOLD;
        append(s);
    }

    public CustomStringBuilder(int capacity) {
        this.capacity = INITIAL_THRESHOLD;
        ensureCapacity(capacity);
        this.value = new char[this.capacity];
    }

    public CustomStringBuilder append(char ch) {
        ensureCapacity(size + 1);
        value[size++] = ch;
        return this;
    }

    public CustomStringBuilder append(String s) {
        int len = s.length();
        ensureCapacity(size + len);
        for (int i = 0; i < len; i++) {
            value[size++] = s.charAt(i);
        }
        return this;
    }

    public CustomStringBuilder append(int i) {
        return append(Integer.toString(i));
    }

    /**
     * Ensures that the internal array has at least the specified minimum capacity.
     *
     * @param minimumCapacity the minimum required capacity
     */
    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > capacity) {
            newCapacity(minimumCapacity);
            value = Arrays.copyOf(value, capacity);
        }
    }

    /**
     * Calculates a new capacity that is at least as large as the specified minimum.
     * Doubles the capacity each time until the minimum is met or exceeded.
     *
     * @param minimumCapacity the minimum required capacity
     */
    private void newCapacity(int minimumCapacity) {
        while (capacity < minimumCapacity) {
            if (capacity == Integer.MAX_VALUE) {
                throw new OutOfMemoryError("Required length extends implementation limit");
            }
            if (capacity == Integer.MAX_VALUE / 2 + 1) {
                capacity = Integer.MAX_VALUE;
            } else {
                capacity <<= 1;
            }
        }
    }

    /**
     * Returns the size (character count).
     *
     * @return the length of the sequence of characters currently
     *         represented by this object
     */
    public int size() {
        return this.size;
    }

    /**
     * Used to get the value of StringBuilder.
     * 
     * @return the string representation of the current content
     */
    @Override
    public String toString() {
        return new String(value, 0, size);
    }

    /**
     * Creates and returns a deep clone of this CustomStringBuilder instance.
     *
     * @return a deep copy of this CustomStringBuilder
     * @throws CloneNotSupportedException if cloning is not supported
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        CustomStringBuilder stringBuilder = new CustomStringBuilder(capacity);
        stringBuilder.size = this.size;
        stringBuilder.value = Arrays.copyOf(value, capacity);
        return stringBuilder;
    }
}
