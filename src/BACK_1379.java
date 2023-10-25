package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_1379 {
    static class Info {
        int[] classInfo;
        int K;
        Info(int[] classInfo, int K) {
            this.classInfo = classInfo;
            this.K = K;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] classes = new int[N][];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            classes[i] = new int[]{Integer.parseInt(st.nextToken()), // num, start, end
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(classes, (a, b) -> Integer.compare(a[1], b[1])); // 시작 시간을 기준으로 정렬
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.classInfo[2], b.classInfo[2]));
        int K = 1;
        pq.offer(new Info(classes[0], K));
        map.put(classes[0][0], K);
        for (int i = 1; i < classes.length; i++) {
            int[] target = classes[i];
            Info info = pq.peek();
            if (info.classInfo[2] <= target[1]) {
                pq.offer(new Info(target, info.K));
                pq.poll();
                map.put(target[0], info.K);
            } else {
                K++;
                pq.offer(new Info(target, K));
                map.put(target[0], K);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < classes.length; i++) sb.append(map.get(i + 1)).append("\n");
        System.out.println(K);
        System.out.print(sb);
    }
}
