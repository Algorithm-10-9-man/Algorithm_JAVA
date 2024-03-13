package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BACK_18430 {
    static class Cord {
        int x;
        int y;
        int value;
        Cord(int x, int y) {
            this.x = x;
            this.y = y;
        }
        void setValue(int value) {
            this.value = value;
        }
    }
    static int[][][] dirs = {{{1, 0},{0, -1}}, // dx, dy
            {{0, -1},{-1, 0}},
            {{-1, 0},{0, 1}},
            {{1, 0},{0, 1}}};
    static int answer;
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static boolean isIn(Cord cord) {
        return (cord.x >= 0 && cord.x < N && cord.y >= 0 && cord.y < M);
    }
    static int isSuccess(ArrayList<Cord> poss) {
        int result = 0;
        for (Cord cord : poss) {
            if (cord.value == -1) return -1;
            else result += cord.value;
        }
        return result;
    }
    static void makeVisited(ArrayList<Cord> poss, boolean value) {
        for (Cord cord : poss) {
            visited[cord.x][cord.y] = value;
        }
    }
    // poss 중 value가 하나라도 -1이면 부메랑 놓기가 실패한 것.
    static ArrayList<Cord> makeBoomerang(int[] cord, int idx) {
        int[][] dirCord = dirs[idx];
        ArrayList<Cord> poss = new ArrayList<>();
        for (int[] dir : dirCord) {
            Cord next = new Cord(dir[0] + cord[0], dir[1] + cord[1]);
            if (isIn(next) && !visited[next.x][next.y]) {
                next.setValue(map[next.x][next.y]);
            } else {
                next.setValue(-1);
            }
            poss.add(next);
        }
        return poss;
    }

    static void findSolution(int[] cord, int subSum) {
        if (cord[1] == M) {
            findSolution(new int[]{cord[0] + 1, 0}, subSum);
            return ;
        }
        if (cord[0] == N) { // 종료
            answer = Math.max(subSum, answer);
            return;
        }
        if (!visited[cord[0]][cord[1]]) {
            for (int i = 0; i < dirs.length; i++) {
                ArrayList<Cord> poss = makeBoomerang(cord, i);
                int retValue = isSuccess(poss);
                if (retValue != -1) { // 부메랑 놓기 성공
                    visited[cord[0]][cord[1]] = true;
                    makeVisited(poss, true);
                    findSolution(new int[]{cord[0], cord[1] + 1}, subSum + retValue + (map[cord[0]][cord[1]] * 2));
                    makeVisited(poss, false);
                    visited[cord[0]][cord[1]] = false;
                }
            }
        }
        findSolution(new int[]{cord[0], cord[1] + 1}, subSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        findSolution(new int[]{0, 0}, 0);
        System.out.println(answer);
    }
}