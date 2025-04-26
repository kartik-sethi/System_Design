
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
        this.value = s.toCharArray();
        this.capacity = value.length;
    }

    public CustomStringBuilder(int capacity) {
        this.capacity = capacity;
        this.value = new char[capacity];
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

    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > capacity) {
            newCapacity(minimumCapacity);
            value = Arrays.copyOf(value, capacity);
        }
    }

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
        return new String(value);
    }

    /**
     * User to get deep clone of CustomStringBuilder
     * 
     * @return Returns a deep clone copy of current instance of CustomStringBuilder
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        CustomStringBuilder stringBuilder = new CustomStringBuilder(capacity);
        stringBuilder.size = this.size;
        stringBuilder.value = Arrays.copyOf(value, capacity);
        return stringBuilder;
    }
}
