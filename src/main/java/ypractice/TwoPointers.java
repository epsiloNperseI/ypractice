package ypractice;

public class TwoPointers {

    public static int maxConsecutiveElements(String str) {
        int result = 0;
        int curLetterIdx = 0;

        while (curLetterIdx < str.length()) {
            int nextLetterIdx = curLetterIdx;
            while (nextLetterIdx < str.length()
                && str.charAt(nextLetterIdx) == str.charAt(curLetterIdx)) {
                ++nextLetterIdx;
            }
            result = Math.max(result, nextLetterIdx - curLetterIdx);
            curLetterIdx = nextLetterIdx;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxConsecutiveElements("aaabbccccaaaaaaa")); // 4
        System.out.println(maxConsecutiveElements("abc"));       // 1
        System.out.println(maxConsecutiveElements("aabbaa"));    // 2

        int[] arr1 = {2, 1, 3, 4, 5};
        System.out.println(subarraySum(arr1, 8));

        System.out.println(subarraySum(arr1, 10));

        System.out.println(subarraySum(arr1, 0));

        System.out.println(subarraySum(arr1, 15));

        int[] arr2 = {1, 2, 3};
        System.out.println(subarraySum(arr2, 7));
    }

    /**
     * Проверяет, существует ли подмассив с суммой равной X
     * в массиве nonNegativeArr (все элементы >= 0)
     *
     * @param nonNegativeArr массив неотрицательных чисел
     * @param X искомая сумма
     * @return true если такой подмассив существует, иначе false
     */
    public static boolean subarraySum(int[] nonNegativeArr, int X) {
        int right = 0;
        int currentSum = 0;

        for (int left = 0; left < nonNegativeArr.length; left++) {

            if (left > 0) {
                currentSum -= nonNegativeArr[left - 1];
            }


            while (right < nonNegativeArr.length && currentSum < X) {
                currentSum += nonNegativeArr[right];
                right++;
            }

            if (currentSum == X) {
                return true;
            }

        }

        return false;
    }

}
