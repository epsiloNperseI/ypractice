package ypractice;

public class FindJudge {

    public static void main(String[] args) {

    }

    public int findJudgeLeetCode(int N, int[][] trust) {
        int[] Trusted = new int[N+1];
        for(int[] person : trust){
            Trusted[person[0]]--;
            Trusted[person[1]]++;
        }
        for(int i = 1;i < Trusted.length;i++){
            if(Trusted[i] == N-1) return i;
        }
        return -1;
    }

    public static int findJudgeGpt(int n, int[][] trust) {

        int[] in = new int[n + 1];
        int[] out = new int[n + 1];

        for (int[] t : trust) {
            int from = t[0];
            int to = t[1];

            out[from]++;
            in[to]++;
        }

        for (int i = 1; i <= n; i++) {
            if (out[i] == 0 && in[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }

}
