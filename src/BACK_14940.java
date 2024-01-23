package src;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_14940 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[] target = new int[2];
        int[][] answer = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    target[0] = i;
                    target[1] = j;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    Queue<int[]> que = new ArrayDeque<>();
                    int[] start = new int[]{i, j, 0};

                    que.offer(start);
                    while (!que.isEmpty()) {
                        int[] elem = que.poll();
                        if (elem[0] == target[0] && elem[1] == target[1]) {
                            answer[start[0]][start[1]] = elem[2];
                            break;
                        }
                        for (int k = 0; k < dx.length; k++) {
                            int nx = elem[0] + dx[k];
                            int ny = elem[1] + dy[k];
                            if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 0) {
                                if (answer[nx][ny] == 0) {
                                    que.offer(new int[]{nx, ny, elem[2] + 1});
                                } else {
                                    que.offer(new int[]{target[0], target[1], elem[2] + 1 + answer[nx][ny]});
                                }
                            }
                        }
                    }
                    if (answer[start[0]][start[1]] == 0) answer[start[0]][start[1]] = -1;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(answer[i][j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }
}
