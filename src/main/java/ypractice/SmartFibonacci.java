package ypractice;

import java.util.HashMap;
import java.util.Map;

public class SmartFibonacci {

    static Map<Integer, Integer> memory = new HashMap<>();

    public static int fib(int n) {
        System.out.println("Вычисляю F(" + n + ")");

        // Если уже вычисляли - берем из памяти
        if (memory.containsKey(n)) {
            System.out.println("Беру F(" + n + ") из памяти: " + memory.get(n));
            return memory.get(n);
        }

        // Базовые случаи
        if (n == 0) return 0;
        if (n == 1) return 1;

        // Вычисляем и ЗАПОМИНАЕМ
        int result = fib(n - 1) + fib(n - 2);
        System.out.println("result: " + result);
        memory.put(n, result);
        System.out.println("Запомнил F(" + n + ") = " + result);
        return result;
    }

    public static void main(String[] args) {
        int result = fib(5);
        System.out.println(result);
        System.out.println(memory.values());
    }

}
