package src;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_8458 {
    static int time;
    static int[][] vtx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        StringTokenizer st;
        for (int test = 1; test <= T; test++) {
            time = 0;
            int N = Integer.parseInt(br.readLine());
            vtx = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                vtx[i][0] = Integer.parseInt(st.nextToken());
                vtx[i][1] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
