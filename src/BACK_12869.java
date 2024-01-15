package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_12869 {
    static int N;
    static int[][] poss = new int[][]{{9, 3, 1}, {9, 1, 3}, {1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}};
    static int[] scvs;
    static int[][][] tdp;
    static int[][] sdp;
    public static void bfs() {
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(scvs);
        while (!que.isEmpty()) {
            int[] target = que.poll();
            for (int[] pos : poss) {
                if (N == 2) {
                    int f = Math.max(target[0] - pos[0], 0);
                    int s = Math.max(target[1] - pos[1], 0);
                    if (sdp[f][s] == 0) {
                        sdp[f][s] = sdp[target[0]][target[1]] + 1;
                        que.offer(new int[]{f, s});
                    }
                } else if (N == 3) {
                    int f = Math.max(target[0] - pos[0], 0);
                    int s = Math.max(target[1] - pos[1], 0);
                    int t = Math.max(target[2] - pos[2], 0);
                    if (tdp[f][s][t] == 0) {
                        tdp[f][s][t] = tdp[target[0]][target[1]][target[2]] + 1;
                        que.offer(new int[]{f, s, t});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        scvs = new int[N];
        for (int i = 0; i < N; i++) {
            scvs[i] = Integer.parseInt(st.nextToken());
        }
        if (N == 1) {
            if (scvs[0] % 9 == 0) System.out.println(scvs[0] / 9);
            else System.out.println((scvs[0] / 9) + 1);
        } else if (N == 2) {
            sdp = new int[scvs[0] + 1][scvs[1] + 1];
            bfs();
            System.out.println(sdp[0][0]);
        } else if (N == 3) {
            tdp = new int[scvs[0] + 1][scvs[1] + 1][scvs[2] + 1];
            bfs();
            System.out.println(tdp[0][0][0]);
        }
    }
}
