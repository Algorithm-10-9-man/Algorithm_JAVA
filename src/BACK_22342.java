package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_22342 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] d = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                d[i][j] = line.charAt(j) - '0';
            }
        }
        int maxSave = 0;
        int[][] output = new int[N][M];
        for (int col = 0; col < M; col++) {
            for (int row = 0; row < N; row++) {
                int subMax = 0;
                if (col == 0) output[row][col] = d[row][col];
                else {
                    if (row - 1 >= 0) subMax = Math.max(subMax, output[row - 1][col - 1]);
                    subMax = Math.max(output[row][col - 1], subMax);
                    if (row + 1 < N) subMax = Math.max(subMax, output[row + 1][col - 1]);
                    maxSave = Math.max(subMax, maxSave);
                    output[row][col] = subMax + d[row][col];
                }
            }
        }
        System.out.println(maxSave);
    }
}
