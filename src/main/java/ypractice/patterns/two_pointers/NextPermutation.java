package ypractice.patterns.two_pointers;

import java.util.Arrays;

public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        int left = i + 1;
        int right = nums.length - 1;
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

    static void main() {
        int[] myIntArray = {1, 2, 4, 3, 5};
        nextPermutation(myIntArray);
        System.out.println(Arrays.toString(myIntArray));
    }

}
