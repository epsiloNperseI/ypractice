package ypractice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubarraySum {

    public static void main(String[] args) {

        int[] arr1 = {2, 1, 3, 4, 5};
        System.out.println(subarraySumAlternative(arr1, 15)); // true ([1, 3, 4] = 8)
    }

    public static boolean subarraySumAlternative(int[] arr, int target) {
        Map<Integer, Integer> prefixSumIndex = new HashMap<>();
        prefixSumIndex.put(0, -1);

        int curSum = 0;

        for (int i = 0; i < arr.length; i++) {
            curSum += arr[i];

            if (prefixSumIndex.containsKey(curSum - target)) {
                int startIndex = prefixSumIndex.get(curSum - target) + 1;
                System.out.println("Найден подмассив от " + startIndex + " до " + i);
                return true;
            }

            prefixSumIndex.put(curSum, i);
        }

        return false;
    }

    /**
     * Проверяет, существует ли в массиве подмассив с суммой равной target
     *
     * @param arr исходный массив (может содержать любые числа)
     * @param target искомая сумма
     * @return true если такой подмассив существует, иначе false
     */
    public static boolean subarraySum(int[] arr, int target) {

        Set<Integer> prefixSums = new HashSet<>();

        prefixSums.add(0);

        int curSum = 0;

        for (int i = 0; i < arr.length; i++) {

            curSum += arr[i];

            int requiredNumber = curSum - target;

            if (prefixSums.contains(requiredNumber)) {

                return true;
            }

            prefixSums.add(curSum);
        }

        return false;
    }
}
