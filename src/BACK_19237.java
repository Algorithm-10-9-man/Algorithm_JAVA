package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_19237 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Shark {
        int x;
        int y;
        int num;
        int dir;
        boolean died;
        Shark(int x, int y, int num, boolean died) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.died = died;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", num=" + num +
                    ", dir=" + dir +
                    ", died=" + died +
                    '}';
        }
    }
    static class Smell {
        int x;
        int y;
        int num;
        int time;
        int dir;
        Smell(int x, int y, int num, int time) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.time = time;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Smell{" +
                    "x=" + x +
                    ", y=" + y +
                    ", num=" + num +
                    ", time=" + time +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Shark[] sharks = new Shark[M];
        Smell[][] smell = new Smell[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int elem = Integer.parseInt(st.nextToken());
                if (elem > 0) {
                    sharks[elem - 1] = new Shark(i, j, elem - 1, false);
                    smell[i][j] = new Smell(i, j, elem - 1, 0);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            for (Shark shark : sharks) {
                if (shark.num == i) {
                    shark.setDir(dir);
                    smell[shark.x][shark.y].setDir(dir);
                    break;
                }
            }
        }
        int[][][] dirs = new int[M][4][4];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    dirs[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        int cnt = 0;
        int answer = 0;
        Smell[] next = new Smell[sharks.length];
        for (int time = 1; time <= 1001; time++) {
            if (cnt == M - 1) {
                answer = time;
                break;
            }
            for (int i = 0; i < sharks.length; i++) {
                Shark elem = sharks[i];
                if (!elem.died) {
                    Smell target = null;
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = elem.x + dx[dirs[elem.num][elem.dir][dir]];
                        int ny = elem.y + dy[dirs[elem.num][elem.dir][dir]];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        if (smell[nx][ny] == null || time - smell[nx][ny].time > K) {
                            target = new Smell(nx, ny, elem.num, time);
                            target.setDir(dirs[elem.num][elem.dir][dir]);
                            break;
                        } else if (smell[nx][ny].num == elem.num) {
                            if (target == null) {
                                target = new Smell(nx, ny, elem.num, time);
                                target.setDir(dirs[elem.num][elem.dir][dir]);
                            }
                        }
                    }
                    next[elem.num] = target;
                }
            }
            for (int i = 0; i < sharks.length; i++) {
                Smell elem = next[i]; // 해당 번의 상어가 이동 할 후보 위치
                Shark shark = sharks[i];
                if (!shark.died) {
                    if (smell[elem.x][elem.y] == null || time - smell[elem.x][elem.y].time >= K || smell[elem.x][elem.y].num == elem.num) {
                        shark.x = elem.x;
                        shark.y = elem.y;
                        shark.dir = elem.dir;
                        smell[elem.x][elem.y] = elem;
                    } else { // 이미 있는 곳.
                        if (smell[elem.x][elem.y].num < elem.num) {
                            cnt++;
                            shark.died = true;
                        }
                    }
                }
            }
        }
        if (cnt != M - 1) System.out.println(-1);
        else System.out.println(answer - 1);
    }
}
