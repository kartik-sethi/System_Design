package com.singleton;

import com.singleton.SingletonComponents.SingletonThreadSafePrinter;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            SingletonThreadSafePrinter printer = SingletonThreadSafePrinter.instance();
            printer.print("Completed Execution of Thread: ");
            printer.println("1");
            printer.printTimeStamp();
        });

        Thread t2 = new Thread(() -> {
            SingletonThreadSafePrinter printer = SingletonThreadSafePrinter.instance();
            printer.print("Completed Execution of Thread: ");
            printer.println("2");
            printer.printTimeStamp();
        });
        t1.start();
        t2.start();
    }
}