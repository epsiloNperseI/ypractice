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
        // Пример использования
        System.out.println(maxConsecutiveElements("aaabbccccaaaaaaa")); // 4
        System.out.println(maxConsecutiveElements("abc"));       // 1
        System.out.println(maxConsecutiveElements("aabbaa"));    // 2

        // Пример 1: Есть подмассив с суммой 8
        int[] arr1 = {2, 1, 3, 4, 5};
        System.out.println(subarraySum(arr1, 8)); // true ([1, 3, 4] = 8)

        // Пример 2: Нет подмассива с суммой 10
        System.out.println(subarraySum(arr1, 10)); // true ([1, 4, 5] = 10)

        // Пример 3: Крайний случай - пустой подмассив
        System.out.println(subarraySum(arr1, 0)); // true (пустой подмассив)

        // Пример 4: Весь массив
        System.out.println(subarraySum(arr1, 15)); // true (весь массив: 2+1+3+4+5=15)

        // Пример 5: Не существует
        int[] arr2 = {1, 2, 3};
        System.out.println(subarraySum(arr2, 7)); // false
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

        // Левый указатель left движется в цикле for
        for (int left = 0; left < nonNegativeArr.length; left++) {

            // Шаг 1: Пересчитываем сумму после сдвига left
            // Если left > 0, значит мы убрали элемент nonNegativeArr[left-1]
            if (left > 0) {
                currentSum -= nonNegativeArr[left - 1];
            }

            // Шаг 2: Двигаем правый указатель, пока сумма < X
            // и не вышли за границы массива
            while (right < nonNegativeArr.length && currentSum < X) {
                currentSum += nonNegativeArr[right];
                right++;
            }

            // Шаг 3: Проверяем, нашли ли нужную сумму
            if (currentSum == X) {
                return true;
            }

            // Важно: если currentSum > X, мы не двигаем right
            // а на следующей итерации сдвинем left и уменьшим сумму
        }

        return false;
    }

}
