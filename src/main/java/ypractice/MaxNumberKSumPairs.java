package ypractice;

import java.util.HashMap;
import java.util.Map;

public class MaxNumberKSumPairs {
    public static int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int result = 0;

        for (int num : nums) {
            int complement = k - num;

            if (freq.getOrDefault(complement, 0) > 0) {
                result++;
                freq.put(complement, freq.get(complement) - 1);
            } else {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
        }

        return result;
    }

    static void main() {
        int[] nums = {4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4};
        int k = 2;

        System.out.println(maxOperations(nums, k));
    }

}
