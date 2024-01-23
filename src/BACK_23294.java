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
<<<<<<< HEAD
        PriorityQueue<Page> forwardMinQueue = new PriorityQueue<>(new Comparator<Page>() { // ÃÖ±Ù ÆäÀÌÁö°¡ Æ¢¾î³ª¿È
=======
        PriorityQueue<Page> forwardMinQueue = new PriorityQueue<>(new Comparator<Page>() { // ìµœê·¼ íŽ˜ì´ì§€ê°€ íŠ€ì–´ë‚˜ì˜´
>>>>>>> 04d14b04dd6a9e21bbc3de79190e4f40435bee0e
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
<<<<<<< HEAD
        PriorityQueue<Page> backwardMinQueue = new PriorityQueue<>(new Comparator<Page>() { // ÃÖ±Ù ÆäÀÌÁö°¡ Æ¢¾î³ª¿È
=======
        PriorityQueue<Page> backwardMinQueue = new PriorityQueue<>(new Comparator<Page>() { // ìµœê·¼ íŽ˜ì´ì§€ê°€ íŠ€ì–´ë‚˜ì˜´
>>>>>>> 04d14b04dd6a9e21bbc3de79190e4f40435bee0e
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
<<<<<<< HEAD
                if (cur != -1) { // Ã³À½ Á¢¼ÓÇÒ ¶§°¡ ¾Æ´Ñ °æ¿ì
=======
                if (cur != -1) { // ì²˜ìŒ ì ‘ì†í•  ë•Œê°€ ì•„ë‹Œ ê²½ìš°
>>>>>>> 04d14b04dd6a9e21bbc3de79190e4f40435bee0e
                    Page page = new Page(num, pages[num], i, false);
                    backwardMaxQueue.offer(page);
                    backwardMinQueue.offer(page);
                }
                cur = num;
            }
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 04d14b04dd6a9e21bbc3de79190e4f40435bee0e
