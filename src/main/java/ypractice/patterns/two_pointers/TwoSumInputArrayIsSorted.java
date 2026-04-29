package ypractice.patterns.two_pointers;

import java.util.Arrays;

public class TwoSumInputArrayIsSorted {

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{};
    }

    static void main() {
        int[] arr = {-10,-8,-2,1,2,5,6};

        System.out.println(Arrays.toString(twoSum(arr, 0)));
    }
}
