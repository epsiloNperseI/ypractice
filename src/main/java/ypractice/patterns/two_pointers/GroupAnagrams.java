package ypractice.patterns.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String item: strs) {
            char[] chars = item.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(item);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println("Test Case 1:");
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result1 = groupAnagrams(strs1);
        System.out.println("Input: " + Arrays.toString(strs1));
        System.out.println("Output: " + result1);
        System.out.println();

        System.out.println("Test Case 2:");
        String[] strs2 = {""};
        List<List<String>> result2 = groupAnagrams(strs2);
        System.out.println("Input: " + Arrays.toString(strs2));
        System.out.println("Output: " + result2);
        System.out.println();

        System.out.println("Test Case 3:");
        String[] strs3 = {"a"};
        List<List<String>> result3 = groupAnagrams(strs3);
        System.out.println("Input: " + Arrays.toString(strs3));
        System.out.println("Output: " + result3);
        System.out.println();

        System.out.println("Additional Test Case - Empty array:");
        String[] strs4 = {};
        List<List<String>> result4 = groupAnagrams(strs4);
        System.out.println("Input: " + Arrays.toString(strs4));
        System.out.println("Output: " + result4);
        System.out.println();

        System.out.println("Additional Test Case - All same anagrams:");
        String[] strs5 = {"abc", "cba", "bca", "acb"};
        List<List<String>> result5 = groupAnagrams(strs5);
        System.out.println("Input: " + Arrays.toString(strs5));
        System.out.println("Output: " + result5);
        System.out.println();

        System.out.println("Additional Test Case - No anagrams:");
        String[] strs6 = {"hello", "world", "java", "code"};
        List<List<String>> result6 = groupAnagrams(strs6);
        System.out.println("Input: " + Arrays.toString(strs6));
        System.out.println("Output: " + result6);
    }
}
