package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_17136 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<Integer> sizes;
    static int[] ans;
    static int[] cnts;
    public static boolean makeVisited(int x, int y, int size, boolean type) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i < 0 || j < 0 || i >= 10 || j >= 10) return false;
                if (type && (visited[i][j] || map[i][j] <= 0)) return false;
                visited[i][j] = type;
            }
        }
        return true;
    }
    public static void find(int x, int y, int num, int cur, int subSum) {
        if (cur == sizes.get(num)) {
            if (ans[num] == 0) ans[num] = subSum;
            else ans[num] = Math.min(ans[num], subSum);
            return ;
        }
        if (y == 10) {
            y = 0;
            x = x + 1;
        }
        if (x < 0 || y < 0 || x >= 10 || y >= 10) return;
        if (map[x][y] == num + 1) {
            if (!visited[x][y]) {
                for (int i = cnts.length - 1; i >= 0; i--) {
                    if (makeVisited(x, y, i + 1, true) && cnts[i] > 0) {
                        cnts[i]--;
                        find(x, y + 1, num, cur + (i + 1) * (i + 1), subSum + 1);
                        cnts[i]++;
                    }
                    makeVisited(x, y, i + 1, false);
                }
            } else find(x, y + 1, num, cur, subSum);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        visited = new boolean[10][10];
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<int[]> que = new ArrayDeque<>();
        sizes = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    visited[i][j] = true;
                    map[i][j] = num;
                    que.offer(new int[]{i, j});
                    int size = 1;
                    while (!que.isEmpty()) {
                        int[] cord = que.poll();
                        for (int k = 0; k < dx.length; k++) {
                            int nx = cord[0] + dx[k];
                            int ny = cord[1] + dy[k];
                            if (nx < 0 || ny < 0 || nx >= 10 || ny >= 10) continue;
                            if (!visited[nx][ny] && map[nx][ny] == 1) {
                                visited[nx][ny] = true;
                                que.offer(new int[]{nx, ny});
                                map[nx][ny] = num;
                                size++;
                            }
                        }
                    }
                    sizes.add(size);
                    num++;
                }
            }
        }
        ans = new int[sizes.size()];
        cnts = new int[]{5, 5, 5, 5, 5}; // 1 2 3 4 5
        visited = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] > 0 && ans[map[i][j] - 1] == 0) {
                    find(i, j, map[i][j] - 1, 0, 0);
                    if (ans[map[i][j] - 1] == 0) {
                        System.out.println(-1);
                        return ;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(ans));
    }
}
