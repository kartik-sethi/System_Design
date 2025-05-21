package com.singleton.SingletonComponents;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A thread-safe singleton class that provides methods for printing to the
 * console.
 * <p>
 * This class follows the Singleton design pattern, ensuring only one instance
 * is created and providing
 * thread-safe methods for printing various types of data to the console. It
 * also includes a method to print
 * timestamps.
 * </p>
 * <p>
 * The class ensures that all print operations are thread-safe using a
 * {@link ReentrantLock}, and the singleton
 * instance is lazily initialized to avoid unnecessary creation of the object.
 * </p>
 * 
 * <strong>Usage Example:</strong>
 * 
 * <pre>{@code
 * SingletonThreadSafePrinter printer = SingletonThreadSafePrinter.instance();
 * printer.print("Hello World");
 * printer.println(123);
 * printer.printTimeStamp();
 * }</pre>
 * 
 * @author Kartik Sethi
 */
public final class SingletonThreadSafePrinter {

    /**
     * The single instance of the {@code SingletonThreadSafePrinter} class.
     * This field is volatile to ensure thread-safe lazy initialization.
     */
    private static transient volatile SingletonThreadSafePrinter INSTANCE;

    /**
     * The lock object used to ensure thread safety when accessing the singleton
     * instance or performing print operations.
     */
    private static final transient ReentrantLock lock = new ReentrantLock();

    /**
     * Private constructor to prevent direct instantiation and enforce the Singleton
     * pattern.
     */
    private SingletonThreadSafePrinter() {
    }

    /**
     * Retrieves the single instance of the {@code SingletonThreadSafePrinter}
     * class.
     * <p>
     * If the instance does not exist, it will be created in a thread-safe manner.
     * </p>
     * 
     * @return the single instance of {@code SingletonThreadSafePrinter}.
     */
    public static SingletonThreadSafePrinter instance() {
        if (INSTANCE == null) {
            lock.lock();
            try {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonThreadSafePrinter();
                }
            } finally {
                lock.unlock();
            }
        }
        return INSTANCE;
    }

    /**
     * Prints an object to the console without adding a newline.
     * <p>
     * This method is thread-safe and ensures that only one thread can print at a
     * time.
     * </p>
     * 
     * @param s the object to print. Can be any type, including strings, numbers, or
     *          custom objects.
     */
    public void print(Object s) {
        lock.lock();
        try {
            CustomSystem.out.print(s);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Prints an object to the console followed by a newline.
     * <p>
     * This method is thread-safe and ensures that only one thread can print at a
     * time.
     * </p>
     * 
     * @param s the object to print. Can be any type, including strings, numbers, or
     *          custom objects.
     */
    public void println(Object s) {
        lock.lock();
        try {
            CustomSystem.out.println(s);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Prints the current timestamp to the console.
     * <p>
     * This method prints the current date and time in the format defined by
     * {@link LocalDateTime#now()}.
     * </p>
     * <p>
     * This method is thread-safe and ensures that the timestamp is printed without
     * interference from other threads.
     * </p>
     */
    public void printTimeStamp() {
        lock.lock();
        try {
            CustomSystem.printTimeStamp();
        } finally {
            lock.unlock();
        }
    }
}
