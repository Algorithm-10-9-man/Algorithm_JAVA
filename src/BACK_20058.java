package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_20058 {
    static int[][] map;
    static int size;
    static int allSum;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};
    static boolean[][] visited;

//    public static void rotateSquare(int x, int y, int L) {
//        for (int t = 0; t < L; t++) {
//            int length = (int)Math.pow(2, L - t); // 한 변의 길이
//            Queue<Integer> que = new ArrayDeque<>();
//            int nx = x;
//            int ny = y + length - 1;
//            for (int i = 0; i < length - 1; i++) {
//                que.offer(map[nx + i][ny]);
//                map[nx + i][ny] = map[x][y + i];
//            }
//            nx = x + length - 1;
//            ny = y + length - 1;
//            for (int i = 0; i < length - 1; i++) {
//                que.offer(map[nx][ny - i]);
//                map[nx][ny - i] = que.poll();
//            }
//            nx = x + length - 1;
//            ny = y;
//            for (int i = 0; i < length - 1; i++) {
//                que.offer(map[nx - i][ny]);
//                map[nx - i][ny] = que.poll();
//            }
//            nx = x;
//            ny = y;
//            for (int i = 0; i < length - 1; i++) {
//                map[nx][ny + i] = que.poll();
//            }
//            x++;
//            y++;
//        }
//    }

    public static int getContinentSize(int x, int y) {
        Queue<int[]> que = new ArrayDeque<>();
        int contSize = 1;
        visited[x][y] = true;
        que.offer(new int[]{x, y});
        while (!que.isEmpty()) {
            int[] target = que.poll();
            for (int i = 0; i < dx.length; i++) {
                int nx = target[0] + dx[i];
                int ny = target[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= size || ny >= size || visited[nx][ny]) continue;
                if (map[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    que.offer(new int[]{nx, ny});
                    contSize++;
                }
            }
        }
        return contSize;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        size = (int)Math.pow(2, N);
        map = new int[size][size];
        visited = new boolean[size][size];
        allSum = 0;
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                allSum += map[i][j];
            }
        }
        int[] L = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) L[i] = Integer.parseInt(st.nextToken());
        for (int t = 0; t < Q; t++) {
            int lSize = (int)Math.pow(2, L[t]);
            int[][] newMap = new int[size][size];
            for (int i = 0; i < size; i += lSize) {
                for (int j = 0; j < size; j+= lSize) {
//                    rotateSquare(i, j, L[t]);
                    for (int x = 0; x < lSize; x++) {
                        for (int y = 0; y < lSize; y++) {
                            newMap[i + y][j + lSize - x - 1] = map[i + x][j + y];
                        }
                    }
                }
            }
            map = newMap;
            Queue<int[]> targets = new ArrayDeque<>();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int iceAdjCnt = 0;
                    if (map[i][j] > 0) {
                        for (int k = 0; k < dx.length; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
                            if (map[nx][ny] > 0) {
                                iceAdjCnt++;
                            }
                        }
                        if (iceAdjCnt < 3) {
                            targets.offer(new int[]{i, j});
                            allSum--;
                        }
                    }
                }
            }
            while (!targets.isEmpty()) {
                int[] target = targets.poll();
                map[target[0]][target[1]]--;
            }
        }
        int maxSize = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    maxSize = Math.max(maxSize, getContinentSize(i, j));
                }
            }
        }
        System.out.println(allSum);
        System.out.println(maxSize);
//        for (int[] elem : map) System.out.println(Arrays.toString(elem));
    }
}
