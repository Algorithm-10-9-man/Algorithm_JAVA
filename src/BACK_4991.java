package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_4991 {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        map = new char[20][20];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        while (true) {
            int answer = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            if (M == 0 && N == 0) break;
            int[] start = null;
            int dirCnt = 0;
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'o') start = new int[]{i, j, 0, 0};
                    if (map[i][j] == '*') {
                        map[i][j] = (char)('0' + dirCnt++);
                    }
                }
            }
            int ansBit = (1 << dirCnt) - 1;
            boolean[][][] visited = new boolean[1 << dirCnt][N][M];
            visited[0][start[0]][start[1]] = true;
            Queue<int[]> que = new ArrayDeque<>();
            que.offer(start);
            while (!que.isEmpty()) {
                int[] target = que.poll();
                if (target[2] == ansBit) {
                    answer = target[3];
                    break;
                }
                for (int i = 0; i < dx.length; i++) {
                    int nx = target[0] + dx[i];
                    int ny = target[1] + dy[i];
                    int bit = target[2];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (!visited[bit][nx][ny] && map[nx][ny] != 'x') {
                        visited[bit][nx][ny] = true;
                        if (map[nx][ny] >= '0' && map[nx][ny] <= '9') {
                            bit |= (1 << (map[nx][ny] - '0'));
                            visited[bit][nx][ny] = true;
                        }
                        que.offer(new int[]{nx, ny, bit, target[3] + 1});
                    }
                }
            }
            if (answer == Integer.MAX_VALUE) sb.append(-1).append("\n");
            else sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
