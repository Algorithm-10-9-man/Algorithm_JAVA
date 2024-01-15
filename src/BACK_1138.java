package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arrays = new int[N];
        int[] answer = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrays[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            int target = arrays[i];
            int cnt = 0;
            int idx = 0;
            while (true) {
                if (answer[idx] == 0 && cnt == target) {
                    answer[idx] = i + 1;
                    break;
                }
                if (answer[idx] == 0) {
                    cnt++;
                }
                idx++;
            }
        }
        for (int i = 0; i < N; i++) System.out.print(answer[i] + " ");
    }
}
