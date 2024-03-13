package src;

import java.io.*;
import java.util.Arrays;

public class BACK_15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[][] dp = new int[10001][4]; // 첫 번째 인덱스, 해당 숫자. 두 번째 인덱스, 해당 숫자를 만들기 위해 쓴 식중 인덱스로 시작한 값
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[2][1] = 1;
        dp[3][3] = 1;
        dp[3][2] = 1;
        dp[3][1] = 1;
        for (int i = 4; i < 10001; i++) {
            dp[i][1] = 1;
            int sec = dp[i - 2][2] - dp[i - 1][1];
            dp[i][2] = (sec > 0) ? sec : 0;
            int thr = dp[i - 3][3] - dp[i - 2][2] - dp[i - 1][1];
            dp[i][3] = (thr > 0) ? thr : 0;
        }
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            bw.write(Integer.toString(Arrays.stream(dp[N]).sum()));
            bw.newLine();
        }
        bw.flush();
    }
}
