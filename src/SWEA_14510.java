package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_14510 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test = 1; test <= T; test++) {
            int N = Integer.parseInt(br.readLine());
            int answer = 0;
            int[] trees = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) trees[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(trees);
            int maxValue = trees[trees.length - 1];
            int oddCnt = 0;
            int evenCnt = 0;
            for (int i = 0; i < N; i++) {
                int target = trees[i];
                int diff = maxValue - target;
                if (diff % 3 == 0) answer += (diff / 3) * 2;
                else {
                    int mok = diff / 3;
                    diff -= mok * 3;
                    answer += (mok * 2);
                    if (diff == 1) oddCnt++;
                    else if (diff == 2) evenCnt++;
                }
            }
            if (evenCnt == oddCnt) answer += oddCnt * 2;
            else if (evenCnt > oddCnt) {
                if (oddCnt > 0) answer += oddCnt * 2;
                evenCnt -= oddCnt;
                answer += evenCnt * 2;
            } else {
                if (evenCnt > 0) answer += evenCnt * 2;
                oddCnt -= evenCnt;
                answer++;
                oddCnt--;
                answer += oddCnt * 2;
            }
            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
