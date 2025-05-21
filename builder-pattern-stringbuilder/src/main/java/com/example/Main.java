package com.example;

import com.example.Builder.CustomStringBuilder;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder s = new CustomStringBuilder();
        s.append("This is a custom implementation of StringBuilder");
        System.out.println(s);
        s.append("Append character ch - ");
        System.out.println(s.toString());
        s.append('j');
        System.out.println(s);
    }
}