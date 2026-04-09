package ypractice.patterns.two_pointers;

import java.util.HashMap;
import java.util.Map;

//sliding window
public class LongestSubstringWithoutRepeating {
    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right;
        int maxSeqSize = 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            right = i;
            char currentChar = chars[i];
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }
            map.put(currentChar, i);

            if(right - left + 1 > maxSeqSize) {
                maxSeqSize =  right - left + 1;
            }
        }
        return maxSeqSize;
    }

}
