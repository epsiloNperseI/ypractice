package ypractice.recursion;

public class ParenthesesGenerator {

    public static void generate(int n) {
        backtrack("", 0, 0, n);
    }

    private static void backtrack(String current, int open, int close, int max) {
        System.out.println("current = \"" + current + "\", open=" + open + ", close=" + close);

        if (current.length() == max * 2) {
            System.out.println("Готовая последовательность: " + current);
            return;
        }

        if (open < max) {
            backtrack(current + "(", open + 1, close, max);
        }

        if (close < open) {
            backtrack(current + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        generate(3);
    }

}
