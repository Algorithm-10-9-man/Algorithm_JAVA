package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_17069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N ; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }
        long[][][] dp = new long[3][N][N]; // 0 가로 1 세로 2 대각
        dp[0][0][1] = 1;
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 3; k++) {
                    if (dp[k][i][j] > 0) {
                        if (k == 0) { // 가로
                            if (j + 1 < N && map[i][j + 1] != '1') {
                                dp[0][i][j + 1] += dp[k][i][j];
                            }
                            if (i + 1 < N && j + 1 < N && map[i][j + 1] != '1'
                            && map[i + 1][j] != '1' && map[i + 1][j + 1] != '1') {
                                dp[2][i + 1][j + 1] += dp[k][i][j];
                            }
                        } else if (k == 1) { // 세로
                            if (i + 1 < N && map[i + 1][j] != '1') {
                                dp[1][i + 1][j] += dp[k][i][j];
                            }
                            if (i + 1 < N && j + 1 < N && map[i][j + 1] != '1'
                                    && map[i + 1][j] != '1' && map[i + 1][j + 1] != '1') {
                                dp[2][i + 1][j + 1] += dp[k][i][j];
                            }
                        } else {
                            if (j + 1 < N && map[i][j + 1] != '1') {
                                dp[0][i][j + 1] += dp[k][i][j];
                            }
                            if (i + 1 < N && map[i + 1][j] != '1') {
                                dp[1][i + 1][j] += dp[k][i][j];
                            }
                            if (i + 1 < N && j + 1 < N && map[i][j + 1] != '1'
                                    && map[i + 1][j] != '1' && map[i + 1][j + 1] != '1') {
                                dp[2][i + 1][j + 1] += dp[k][i][j];
                            }
                        }
                    }
                }
            }
        }
        System.out.println(dp[0][N - 1][N - 1] + dp[1][N - 1][N - 1] + dp[2][N - 1][N - 1]);
    }
}
