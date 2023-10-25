package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BACK_23294 {
    static class Page {
        int num;
        int cash;
        int time;
        boolean isDeleted;

        public Page(int num, int cash, int time, boolean isDeleted) {
            this.num = num;
            this.cash = cash;
            this.time = time;
            this.isDeleted = isDeleted;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int cur = -1;
        int nowCash = 0;
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Page> forwardMinQueue = new PriorityQueue<>(new Comparator<Page>() { // 최근 페이지가 튀어나옴
            @Override
            public int compare(Page o1, Page o2) {
                return Integer.compare(o1.time, o2.time);
            }
        });
        PriorityQueue<Page> forwardMaxQueue = new PriorityQueue<>(new Comparator<Page>() {
            @Override
            public int compare(Page o1, Page o2) {
                return Integer.compare(o2.time, o1.time);
            }
        });
        PriorityQueue<Page> backwardMinQueue = new PriorityQueue<>(new Comparator<Page>() { // 최근 페이지가 튀어나옴
            @Override
            public int compare(Page o1, Page o2) {
                return Integer.compare(o1.time, o2.time);
            }
        });
        PriorityQueue<Page> backwardMaxQueue = new PriorityQueue<>(new Comparator<Page>() {
            @Override
            public int compare(Page o1, Page o2) {
                return Integer.compare(o2.time, o1.time);
            }
        });

        int[] pages = new int[N + 1];
        for (int i = 1; i <= pages.length; i++) pages[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            char command = st.nextToken().charAt(0);
            if (command == 'A') {
                int num = Integer.parseInt(st.nextToken());
                Page tmp;
                while (!forwardMinQueue.isEmpty()) {
                    tmp = forwardMinQueue.poll();
                    if (!tmp.isDeleted) nowCash -= tmp.cash;
                }
                while (!forwardMaxQueue.isEmpty()) {
                    tmp = forwardMaxQueue.poll();
                    if (!tmp.isDeleted) nowCash -= tmp.cash;
                }
                nowCash += pages[num];
                if (cur != -1) { // 처음 접속할 때가 아닌 경우
                    Page page = new Page(num, pages[num], i, false);
                    backwardMaxQueue.offer(page);
                    backwardMinQueue.offer(page);
                }
                cur = num;
            }
        }
    }
}
