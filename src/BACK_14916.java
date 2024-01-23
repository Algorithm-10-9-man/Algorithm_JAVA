package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BACK_14916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        if (N >= 6) {
            dp[2] = 1;
            dp[4] = 2;
            dp[5] = 1;
            for (int i = 6; i <= N; i++) {
                int beforeSec = 100000;
                int beforeFiv = 100000;
                boolean isChk = false;
                if (dp[i - 2] > 0) {
                    isChk = true;
                    beforeSec = dp[i - 2];
                }
                if (dp[i - 5] > 0) {
                    isChk = true;
                    beforeFiv = dp[i - 5];
                }
                if (isChk) dp[i] = Math.min(beforeSec, beforeFiv) + 1;
            }
            if (dp[N] > 0) System.out.println(dp[N]);
            else System.out.println(-1);
        } else {
            if (N == 2 || N == 5) System.out.println(1);
            else if (N == 0) System.out.println(0);
            else if (N == 4) System.out.println(2);
            else System.out.println(-1);
        }
    }
}
