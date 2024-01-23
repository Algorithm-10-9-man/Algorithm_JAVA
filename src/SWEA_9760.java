package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_9760 {
    static String[] cards;
    static Map<Character, Integer> map = new HashMap<>();
    public static boolean isStraight(String[] cards) {
        int before = map.get(cards[0].charAt(1));
        for (int i = 1; i < cards.length; i++) {
            int now = map.get(cards[i].charAt(1));
            if (++before != now) return false;
        }
        return true;
    }

    public static boolean isBackStraight(String[] cards) {
        return cards[0].charAt(1) == 'A'
                && cards[1].charAt(1) == 'T'
                && cards[2].charAt(1) == 'J'
                && cards[3].charAt(1) == 'Q'
                && cards[4].charAt(1) == 'K';
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        cards = new String[5];
        StringTokenizer st;
        map.put('A', 1);
        for (int i = 1; i < 10; i++) {
            map.put((char)('1' + i), i + 1);
        }
        map.put('T', 10);
        map.put('J', 11);
        map.put('Q', 12);
        map.put('K', 13);
        StringBuilder sb = new StringBuilder();
        cards = new String[5];
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 5; i++) cards[i] = st.nextToken();
            Arrays.sort(cards, (a, b) -> Integer.compare(map.get(a.charAt(1)), map.get(b.charAt(1))));
            Set<Character> markSet = new HashSet<>();
            Map<Character, Integer> numMap = new HashMap<>();
            for (int i = 0; i < 5; i++) {
                char mark = cards[i].charAt(0);
                char num = cards[i].charAt(1);
                markSet.add(mark);
                if (!numMap.containsKey(num)) numMap.put(num, 1);
                else numMap.put(num, numMap.get(num) + 1);
            }
            boolean isStraight = isStraight(cards);
            boolean isBackStraight = isBackStraight(cards);
            boolean isFlush = markSet.size() == 1;
            int pairCnt = 0;
            int tripleCnt = 0;
            int fourCnt = 0;
            for (Map.Entry<Character, Integer> entry : numMap.entrySet()) {
                switch (entry.getValue()) {
                    case 2:
                        pairCnt++;
                        break;
                    case 3:
                        tripleCnt++;
                        break;
                    case 4:
                        fourCnt++;
                        break;
                    default:
                        break;
                }
            }
            sb.append("#").append(test).append(" ");
            if ((isStraight || isBackStraight) && isFlush) {
                sb.append("Straight Flush\n");
            } else if (fourCnt == 1) {
                sb.append("Four of a Kind\n");
            } else if (tripleCnt == 1 && pairCnt == 1) {
                sb.append("Full House\n");
            } else if (isFlush) {
                sb.append("Flush\n");
            } else if (isStraight) {
                sb.append("Straight\n");
            } else if (tripleCnt == 1) {
                sb.append("Three of a kind\n");
            } else if (pairCnt == 2) {
                sb.append("Two pair\n");
            } else if (pairCnt == 1) {
                sb.append("One pair\n");
            } else sb.append("High card\n");
        }
        System.out.print(sb);
    }
}
