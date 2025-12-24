package ypractice;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nonNegativeArr = {1, 2, 3, 8, 10, 33, 333, 345, 346, 500};
        System.out.println(binarySearch(nonNegativeArr, 333));
    }

    public static boolean binarySearch(int[] arr, int X) {
        int left = 0;
        int right = arr.length; // правая граница НЕ включена

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] == X) {
                return true;
            } else if (arr[mid] < X) {
                left = mid + 1;  // ищем справа
            } else {
                right = mid;     // ищем слева
            }
        }

        return false;
    }

}
