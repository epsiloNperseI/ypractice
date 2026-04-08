package ypractice.patterns;

public class Palindrome {

    private static String currentString;

    public static String longestPalindrome(String s) {
        currentString = "";
        for (int i = 0; i < s.length(); i++) {
            findPalindrome(s, i, i);
            findPalindrome(s, i, i + 1);
        }

        return currentString;
    }

    private static void findPalindrome(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        if (currentString.length() < right - left - 1) {
            currentString = str.substring(left + 1, right);
        }
    }

}
