package com.zyx;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("zzz", "apple", "banana", "orange");
        //foreach
        list.forEach(System.out::println);
        // sort
        System.out.println("----------------------------------------------------");
        Collections.sort(list, String::compareTo);
        list.forEach(System.out::println);
        //filter
        List<String> list1 = list.stream().filter(s->s.startsWith("a")).collect(Collectors.toList());
        System.out.println("----------------------------------------------------");
        list1.forEach(System.out::println);
        //map
        List<Integer> list2 = list.stream().map(s->s.length()).collect(Collectors.toList());
        System.out.println("----------------------------------------------------");
        list2.forEach(System.out::println);
        //reduce
        System.out.println("----------------------------------------------------");
        int sum = list2.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        //group
        System.out.println("----------------------------------------------------");
        Map<Integer, List<String>> group = list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(group);
        
    }
}
