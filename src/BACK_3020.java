package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BACK_3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        ArrayList<Integer> ceil = new ArrayList<>();
        ArrayList<Integer> ground = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) ground.add(Integer.parseInt(br.readLine()));
            else ceil.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(ceil);
        Collections.sort(ground);
        int minDestroy = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 1; i <= H; i++) {
            int cIdx = Collections.binarySearch(ceil, H - i + 1);
            int gIdx = Collections.binarySearch(ground, i);
            int cDes;
            if (cIdx < 0) cDes = ceil.size() - (-(cIdx + 1));
            else cDes = ceil.size() - cIdx;
            int gDes;
            if (gIdx < 0) gDes = ground.size() - (-(gIdx + 1));
            else gDes = ground.size() - gIdx;
            int tmpDestroy = cDes + gDes;
            if (cDes + gDes < minDestroy) {
                minDestroy = cDes + gDes;
                count = 1;
            }
            else if (tmpDestroy == minDestroy) count++;
        }
        System.out.println(minDestroy + " " + count);
    }
}
