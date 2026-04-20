package ypractice.patterns.two_pointers;

public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i +1]) {
            i--;

        }
    }

}
