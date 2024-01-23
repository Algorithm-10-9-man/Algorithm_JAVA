package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_28216 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
        }
        int[] now = new int[]{1, 1};
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            now[0] += dx[dir]; // 시작지점 포함하지 않음.
            now[1] += dy[dir];
            int[] next = new int[]{now[0] + (dx[dir] * (dist - 1)), now[1] + (dy[dir] * (dist - 1))};
            if (dir == 0) { // 아래로 이동

            }
        }
    }
}
