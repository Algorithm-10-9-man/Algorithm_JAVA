package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_17136 {
    static char[][] map;
    static boolean[][] visited;
    static int[] papers = new int[]{5, 5, 5, 5, 5}; // 1 2 3 4 5
    static int paperCnt;
    static int answer;

    public static boolean check(int x, int y, int size) {
        if (x + size - 1 >= 10 || y + size - 1 >= 10) return false;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] != '1' || visited[i][j]) return false;
            }
        }
        return true;
    }

    public static void stick(int x, int y, int size, boolean type) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                visited[i][j] = type;
            }
        }
    }

    public static void find(int i, int j, int stickCnt, int depth) {
        if (j == 10) {
            j = 0;
            i++;
        }
        if (i >= 10) {
            if (stickCnt == paperCnt && depth < answer) answer = depth;
            return;
        }
        if (map[i][j] == '1' && !visited[i][j]) {
            for (int k = papers.length - 1; k >= 0; k--) {
                if (papers[k] > 0 && check(i, j, k + 1)) {
                    papers[k]--;
                    stick(i, j, k + 1, true);
                    find(i, j + 1, stickCnt + ((k + 1) * (k + 1)), depth + 1);
                    stick(i, j, k + 1, false);
                    papers[k]++;
                }
            }
        } else find(i, j + 1, stickCnt, depth);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[10][10];
        visited = new boolean[10][10];
        StringTokenizer st;
        paperCnt = 0;
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == '1') paperCnt++;
            }
        }
        find(0, 0, 0, 0);
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
}
