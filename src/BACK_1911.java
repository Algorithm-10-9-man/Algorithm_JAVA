package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_1911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] holes = new int[N][];
        int answer = 0;
        int cur = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            holes[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(holes, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        for (int i = 0; i < N; i++) {
            if (cur >= holes[i][1]) continue;
            else if (cur >= holes[i][0]) holes[i][0] = cur + 1;
            int size = holes[i][1] - holes[i][0];
            if (size % L == 0) answer += size / L;
            else {
                int wood = (size / L) + 1;
                cur = holes[i][0] + (wood * L) - 1;
                answer += wood;
            }
        }
        System.out.println(answer);
    }
}
