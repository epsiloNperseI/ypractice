package ypractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Базовые из условия
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

        System.out.println(fourSum(new int[]{2, 2, 2, 2, 2}, 8));
        // [[2,2,2,2]]

        // Дубликаты — должен вернуть только уникальные
        System.out.println(fourSum(new int[]{0, 0, 0, 0}, 0));
        // [[0,0,0,0]]

        // Нет решения
        System.out.println(fourSum(new int[]{1, 2, 3, 4}, 100));
        // []

        // Отрицательный target
        System.out.println(fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, -4));

        // Большие числа (overflow ловушка)
        System.out.println(fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296));
        // []
    }
}
