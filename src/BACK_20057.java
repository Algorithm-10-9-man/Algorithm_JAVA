package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_20057 {
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map = new int[N][N];
    static int answer;
    static int alpha;

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    public static void moveSand(int[] start, int dir) {
        int allSand = map[start[0]][start[1]];
        map[start[0]][start[1]] = 0;

        // alpha 이동
        alpha = allSand;

        int nx;
        int ny;

        // 1%
        nx = start[0] - dx[dir];
        ny = start[1] - dy[dir];
        for (int i = 0; i < 4; i++) {
            if (dir % 2 == 0) {     // 가로 이동
                if (i % 2 == 1) {
                    move(allSand, nx, ny, i,1, 0.01f);
                }
            } else {
                if (i % 2 == 0) {
                    move(allSand, nx, ny, i,1, 0.01f);
                }
            }
        }

        // 7%
        nx = start[0];
        ny = start[1];
        for (int i = 0; i < 4; i++) {
            if (dir % 2 == 0) {     // 가로 이동
                if (i % 2 == 1) {
                    move(allSand, nx, ny, i,1, 0.07f);
                }
            } else {
                if (i % 2 == 0) {
                    move(allSand, nx, ny, i,1, 0.07f);
                }
            }
        }

        // 2%
        nx = start[0];
        ny = start[1];
        for (int i = 0; i < 4; i++) {
            if (dir % 2 == 0) {     // 가로 이동
                if (i % 2 == 1) {
                    move(allSand, nx, ny, i,2, 0.02f);
                }
            } else {
                if (i % 2 == 0) {
                    move(allSand, nx, ny, i,2, 0.02f);
                }
            }
        }

        // 10%
        nx = start[0] + dx[dir];
        ny = start[1] + dy[dir];
        for (int i = 0; i < 4; i++) {
            if (dir % 2 == 0) {     // 가로 이동
                if (i % 2 == 1) {
                    move(allSand, nx, ny, i,1, 0.1f);
                }
            } else {
                if (i % 2 == 0) {
                    move(allSand, nx, ny, i,1, 0.1f);
                }
            }
        }

        // 5%
        nx = start[0] + (dx[dir] * 2);
        ny = start[1] + (dy[dir] * 2);
        int sand = (int)(allSand * 0.05f);
        alpha -= sand;
        if (isIn(nx, ny)) {
            map[nx][ny] += sand;
        } else answer += sand;

        // alpha
        nx = start[0] + dx[dir];
        ny = start[1] + dy[dir];
        if (isIn(nx, ny)) {
            map[nx][ny] += alpha;
        } else answer += alpha;
    }

    private static void move(int allSand, int nx, int ny, int i, int dist, float value) {
        int sand;
        int nnx = nx + (dx[i] * dist);
        int nny = ny + (dy[i] * dist);
        sand = (int)(allSand * value);
        alpha -= sand;
        if (isIn(nnx, nny)) {
            map[nnx][nny] += sand;
        } else answer += sand;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = 0;
        int[] start = {N/2, N/2};
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int size = 1;
        int idx = 0;
        // 달팽이 모양으로 돌아 나가기
        outLoop:
        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < size; j++) {
                    start[0] += dx[idx % 4];
                    start[1] += dy[idx % 4];
                    moveSand(start, idx % 4);
//                    for (int[] elem : map) System.out.println(Arrays.toString(elem));
//                    System.out.println(answer);
//                    System.out.println();
                    if (start[0] == 0 && start[1] == 0) break outLoop;
                }
                idx++;
            }
            size++;
        }
        System.out.println(answer);
    }
}