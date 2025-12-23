package ypractice;

public class FindCenter {

    public static int findCenter(int[][] edges) {
        int[] arr = new int[edges.length * 2];
        for (int[] edge : edges) {
            arr[edge[0]]++;
            arr[edge[1]]++;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == edges.length) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] edges1 = {
            {1, 2},
            {2, 3},
            {4, 2}
        };

        int[][] edges2 = {
            {1, 2},
            {5, 1},
            {1, 3},
            {1, 4}
        };

        int[][] edges3 = {
            {3, 1},
            {3, 2},
            {3, 4},
            {3, 5}
        };

        System.out.println(findCenter(edges1));
        System.out.println(findCenter(edges2));
        System.out.println(findCenter(edges3));
    }

}
