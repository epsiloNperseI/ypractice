package ypractice;

public class ParenthesesGenerator {

    public static void generate(int n) {
        backtrack("", 0, 0, n);
    }

    private static void backtrack(String current, int open, int close, int max) {
        System.out.println("current = \"" + current + "\", open=" + open + ", close=" + close);

        // Когда строка достигла длины 2*n — это готовый вариант
        if (current.length() == max * 2) {
            System.out.println("Готовая последовательность: " + current);
            return;
        }

        // Если можем добавить "(" — добавляем
        if (open < max) {
            backtrack(current + "(", open + 1, close, max);
        }

        // Если можем добавить ")" — добавляем
        if (close < open) {
            backtrack(current + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        generate(3);
    }

}
