package ypractice.patterns.two_pointers;

public class ReverseWordsInString {

    // плохое решение по времени
    public static String reverseWords(String s) {
        char[] arr = s.toCharArray();
        reverse(arr, 0, arr.length - 1);

        int wordStart = 0;
        for (int i = 0; i <= arr.length; i++) {
            boolean endOfWord = (i == arr.length || arr[i] == ' ');
            if (endOfWord && i > wordStart) {
                reverse(arr, wordStart, i - 1);
                wordStart = i + 1;
            } else if (i < arr.length && arr[i] == ' ') {
                wordStart = i + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != ' ') {
                if (!sb.isEmpty()) sb.append(' ');
                while (i < arr.length && arr[i] != ' ') {
                    sb.append(arr[i]);
                    i++;
                }
            }
        }

        return sb.toString();
    }

    private static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    static void main() {
        String str = "  the sky  is blue  ";

        System.out.println(reverseWords(str));
    }
}
