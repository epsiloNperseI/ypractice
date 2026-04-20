package ypractice;

public class StringToInteger {

    //решение по времени слабое, посмотрел решение быстрое на литкоде и разобрал
    public static int myAtoi(String s) {
        char[] chars = s.toCharArray();
        StringBuilder previewString = new StringBuilder();
        boolean isNegative = false;
        boolean signSeen = false;

        for (char current : chars) {
            if (current == ' ') {
                if (!previewString.isEmpty() || signSeen) break;
                continue;
            }
            if (current == '-' || current == '+') {
                if (signSeen || !previewString.isEmpty()) break;
                signSeen = true;
                isNegative = (current == '-');
                continue;
            }
            if (Character.isDigit(current)) {
                previewString.append(current);
            } else {
                break;
            }
        }

        if (previewString.isEmpty()) return 0;
        while (previewString.length() > 1 && previewString.charAt(0) == '0') {
            previewString.deleteCharAt(0);
        }
        if (previewString.length() > 10) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        long result = Long.parseLong(previewString.toString());
        if (isNegative) result = -result;
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return (int) result;
    }

    static void main() {
        System.out.println(myAtoi("42"));           // 42
        System.out.println(myAtoi(" -042"));        // -42
        System.out.println(myAtoi("1337c0d3"));     // 1337
        System.out.println(myAtoi("0-1"));          // 0
        System.out.println(myAtoi("words and 987"));// 0
        System.out.println(myAtoi("2147483648"));   // 2147483647
        System.out.println(myAtoi("-2147483649"));  // -2147483648
        System.out.println(myAtoi("+1"));  // 1
        System.out.println(myAtoi("+-12"));  // 0
    }

}
