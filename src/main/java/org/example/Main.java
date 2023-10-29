package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("a", "d", "b", "p", "e", "i", "c", "z", "j", "t");
        MyArrayList<String> myList = new MyArrayList<>(list);
//        String[] l = new String[10];
//        list.toArray(l);
        MyQuickSort.sort(myList);
        Iterator<String> i = myList.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }
}