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
        // Карта: префиксная сумма -> индекс, где она встретилась
        Map<Integer, Integer> prefixSumIndex = new HashMap<>();
        prefixSumIndex.put(0, -1);  // сумма пустого подмассива = 0 на позиции -1

        int curSum = 0;

        for (int i = 0; i < arr.length; i++) {
            curSum += arr[i];

            // Если curSum - target уже встречалась, значит нашли подмассив
            if (prefixSumIndex.containsKey(curSum - target)) {
                int startIndex = prefixSumIndex.get(curSum - target) + 1;
                System.out.println("Найден подмассив от " + startIndex + " до " + i);
                return true;
            }

            // Сохраняем текущую префиксную сумму
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
        // 1. Создаем множество для хранения встреченных префиксных сумм
        // HashSet позволяет быстро (O(1)) проверять наличие элемента
        Set<Integer> prefixSums = new HashSet<>();

        // 2. Добавляем начальное значение 0
        // Почему 0? Потому что сумма пустого подмассива = 0
        // Это нужно для случая, когда подмассив начинается с первого элемента
        prefixSums.add(0);

        // 3. Текущая накопленная сумма (префиксная сумма)
        // Начинаем с 0 (еще не добавили ни одного элемента)
        int curSum = 0;

        // 4. Проходим по всем элементам массива
        for (int i = 0; i < arr.length; i++) {

            // 5. Обновляем текущую сумму: добавляем текущий элемент
            // После этой строки curSum = сумма всех элементов от 0 до i
            curSum += arr[i];

            // 6. Вычисляем, какое число нам нужно найти в множестве
            // Из формулы: curSum - X = target, где X - какая-то предыдущая префиксная сумма
            // Значит X = curSum - target
            // Если X есть в множестве, значит нашли подмассив
            int requiredNumber = curSum - target;

            // 7. Проверяем, встречалась ли такая префиксная сумма раньше
            // contains() работает за O(1) в HashSet
            if (prefixSums.contains(requiredNumber)) {
                // 8. Если нашли - возвращаем true
                // Подмассив найден от того индекса, где была префиксная сумма requiredNumber,
                // до текущего индекса i
                return true;
            }

            // 9. Если не нашли - добавляем текущую префиксную сумму в множество
            // Она может понадобиться для будущих проверок
            prefixSums.add(curSum);
        }

        // 10. Если прошли весь массив и не нашли - возвращаем false
        return false;
    }
}
