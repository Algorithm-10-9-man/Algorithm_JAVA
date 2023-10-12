package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_13977 {
    static long NUM = 1_000_000_007;
    public static long fastPow(long a, long b) {
        long ret = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                ret = (ret * a) % NUM;
            }
            a = (a * a) % NUM;
            b >>= 1;
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(st.nextToken());
        long[] dp = new long[4000001];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) dp[i] = (dp[i - 1] * i) % NUM;
        for (int test = 0; test < M; test++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            sb.append((fastPow((dp[N - R] * dp[R]) % NUM, NUM - 2) * dp[N]) % NUM).append("\n");
        }
        System.out.print(sb);
    }
}