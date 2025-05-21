package com.iterator_pattern_arraylist;

import com.iterator_pattern_arraylist.Collections.CustomList;
import com.iterator_pattern_arraylist.Collections_impl.CustomArrayList;

public class App {
    public static void main(String[] args) {

        CustomList<Integer> list = new CustomArrayList<>();
        list.add(3);
        System.out.println("Added 3: " + list);
        list.add(2);
        System.out.println("Added 2: " + list);
        list.add(1);
        System.out.println("Added 1: " + list);
        System.out.println("Get 1st element: " + list.get(0));
        list.remove(3);

        System.out.println("Print list size: " + list.size());
        System.out.println("Removed the 1st element: " + list);
        System.out.println("PrintList Elements in next line");
        for (int e : list) {
            // list.add(4); //throws concurrent modification
            System.out.println(e);
        }
    }
}
