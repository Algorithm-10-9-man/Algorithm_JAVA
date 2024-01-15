package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BACK_28215 {
    static ArrayList<int[]> poss;
    static int[][] homes;
    static int N, K;
    public static void findPoss(int[] chosen, int start, int depth) {
        if (depth == K) {
            poss.add(chosen.clone());
            return;
        }
        for (int i = start; i < N; i++) {
            chosen[depth] = i;
            findPoss(chosen, i + 1, depth + 1);
        }
    }

    private static int getSubAnswer(int[] exits) {
        int subAnswer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            boolean isChosen = false;
            for (int j = 0; j < exits.length; j++) {
                if (exits[j] == i) {
                    isChosen = true;
                    break;
                }
            }
            if (!isChosen) { // 대피소 설치 안된 집
                int subMin = Integer.MAX_VALUE;
                for (int j = 0; j < exits.length; j++) {
                    int distX = Math.abs(homes[exits[j]][0] - homes[i][0]);
                    int distY = Math.abs(homes[exits[j]][1] - homes[i][1]);
                    if (subMin > distX + distY) subMin = distX + distY;
                }
                if (subAnswer < subMin) subAnswer = subMin;
            }
        }
        return subAnswer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (N == K) {
            System.out.println("0");
            return ;
        }
        homes = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            homes[i][0] = Integer.parseInt(st.nextToken());
            homes[i][1] = Integer.parseInt(st.nextToken());
        }
        poss = new ArrayList<>();
        findPoss(new int[K], 0, 0);
        int answer = Integer.MAX_VALUE;
        for (int[] exits : poss) {
            int subAnswer = getSubAnswer(exits);
            if (answer > subAnswer) answer = subAnswer;
        }
        System.out.println(answer);
    }
}
