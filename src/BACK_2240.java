package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_2240 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][][] dp = new int[W + 1][T + 1][3];
        int ja = Integer.parseInt(br.readLine()); // 제일 처음 떨어졌을 때
        if (ja == 1) {
            dp[0][1][1] = 1;
        } else dp[1][1][2] = 1;
        for (int i = 2; i <= T; i++) {
            ja = Integer.parseInt(br.readLine());
            for (int j = 0; j < dp.length; j++) { // 이동횟수가 0일때, 2번에 서 있을 수는 없다.
                if (ja == 1) { // 1번에 떨어짐
                    if (j == 0) { // 횟수가 0일 때는 1번 판에서만 이득 볼 수 있음.
                        dp[j][i][1] = dp[j][i - 1][1] + 1;
                    } else {
                        dp[j][i][1] = Math.max(dp[j - 1][i - 1][2], dp[j][i - 1][1]) + 1;
                        dp[j][i][2] = Math.max(dp[j - 1][i - 1][1], dp[j][i - 1][2]);
                    }
                } else {
                    // 횟수가 0일 때는 1번 판에서만 이득 볼 수 있음.
                    if (j != 0) {
                        dp[j][i][2] = Math.max(dp[j][i - 1][2], dp[j - 1][i - 1][1]) + 1;
                        dp[j][i][1] = Math.max(dp[j - 1][i - 1][2], dp[j][i - 1][1]);
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            int tmp = Math.max(dp[i][T][1], dp[i][T][2]);
            if (tmp > max) max = tmp;
        }
        System.out.println(max);
    }
}
