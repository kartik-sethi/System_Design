package com.singleton.SingletonComponents;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

/**
 * This class demonstrates the Singleton Design Pattern by providing a custom
 * implementation that mimics the behavior of the {@link System} class for
 * printing
 * output to the console with a timestamp.
 * <p>
 * The class follows the Singleton pattern, ensuring only a single instance of
 * {@code CustomSystem} is created throughout the application. It provides a
 * static
 * method to print the current timestamp to the console, similar to how
 * {@code System.out}
 * is used in Java.
 * </p>
 * <p>
 * The {@code out} field is a custom {@code PrintStream} that writes to the
 * standard output stream.
 * </p>
 * <p>
 * <strong>Example Usage:</strong>
 * 
 * <pre>{@code
 * // Prints the current timestamp to the console
 * CustomSystem.printTimeStamp();
 * }</pre>
 * </p>
 * 
 * @author Kartik Sethi
 * @see java.lang.System
 */
public final class CustomSystem {

    /**
     * A custom {@code PrintStream} that writes to the console (stdout).
     * This is initialized to output to {@code FileDescriptor.out}.
     */
    public static final transient PrintStream out = new PrintStream(new FileOutputStream(FileDescriptor.out));

    /**
     * Private constructor to prevent instantiation of the {@code CustomSystem}
     * class.
     * This class follows the Singleton Design Pattern, and is intended to be used
     * statically.
     */
    private CustomSystem() {
    }

    /**
     * Prints the current timestamp to the console in the format
     * {@code yyyy-MM-ddTHH:mm:ss}.
     * <p>
     * This method mimics the behavior of printing to the console, similar to
     * {@code System.out.println()}.
     * </p>
     */
    public static void printTimeStamp() {
        LocalDateTime dateTime = LocalDateTime.now();
        out.println(dateTime);
    }
}
