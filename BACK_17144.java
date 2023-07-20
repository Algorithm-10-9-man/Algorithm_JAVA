import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BACK_17144 { // 북 동 남 서
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int R;
    static int C;
    static int T;
    static int[][] map;
    static ArrayList<int[]> targets;

    public static void spread(int x, int y, int subtract) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] != -1) {
                    map[x][y] -= subtract;
                    map[nx][ny] += subtract;
            }
        }
    }

    public static void cleaning(int x, int y, int dir) {
        boolean first = false;
        while (true) {
            int tx = x - dx[dir];
            int ty = y - dy[dir];
            if (tx >= 0 && ty >= 0 && tx < R && ty < C) {
                if (map[tx][ty] == -1 && !first) {
                    first = true;
                    map[x][y] = 0;
                    tx = x + dx[dir];
                    ty = y + dy[dir];
                    if (tx >= 0 && ty >= 0 && tx < R && ty < C) {
                        x = tx;
                        y = ty;
                    } else dir = (dir + 1) % 4;
                } else if (map[tx][ty] == -1) break;
                else {
                    map[x][y] = map[tx][ty];
                    tx = x + dx[dir];
                    ty = y + dy[dir];
                    if (tx >= 0 && ty >= 0 && tx < R && ty < C) {
                        x = tx;
                        y = ty;
                    } else dir = (dir + 1) % 4;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = inputs[0];
        C = inputs[1];
        T = inputs[2];
        map = new int[R][C];
        targets = new ArrayList<>();
        for (int i = 0; i < R; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int k = 0; k < T; k++) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        targets.add(new int[]{i, j, map[i][j]});
                    }
                }
            }
            for (int[] elem : targets) spread(elem[0], elem[1], elem[2] / 5);
            cleaning(1, 0, 0);
            targets.clear();
            for (int[] elem : map) System.out.println(Arrays.toString(elem));
        }
    }
}
