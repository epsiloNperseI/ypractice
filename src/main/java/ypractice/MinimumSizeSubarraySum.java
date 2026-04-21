package ypractice;

public class MinimumSizeSubarraySum {

    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int currentSum = 0;
        int result = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            while (currentSum >= target) {
                result = Math.min(result, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    static void main() {
        int[] myIntArray = {2,3,1,2,4,3};
        int target = 7;
        System.out.println(minSubArrayLen(target, myIntArray));
    }
}
