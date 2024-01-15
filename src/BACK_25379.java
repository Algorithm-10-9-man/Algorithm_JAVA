package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_25379 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int oddCnt, evenCnt;
        oddCnt = evenCnt = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if (nums[i] % 2 == 0) evenCnt++;
            else oddCnt++;
        }
        if (evenCnt == 0 || oddCnt == 0) {
            System.out.println("0");
            return ;
        }
        int[] nums2 = nums.clone();
        // 첫 번째 인덱스가 짝이거나, 홀인 경우 두 가지로 나눠서 생각해야 함.
        /* 1. 왼쪽이 짝인가?
         짝이라면 계속햬서 짝을 탐색하다가.. 홀을 만나면 해당 인덱스 기록(oddIdx)
         짝이 나올 때 까지 계속해서 인덱스를 넘김
         짝이 나오면, 해당 인덱스 evenIdx를 oddIdx까지 넘김.
         진짜 넘길 필요는 없고, evenIdx - oddIdx를 횟수에 더해주면 됨.
         홀 인덱스 oddIdx는 oddIdx + 1이되고, 짝 인덱스 evenIdx는 evenIdx + 1이 됨.
         */
        int oIdx, eIdx;
        eIdx = 0;
        oIdx = -1;
        // 왼쪽이 짝이라고 가정
        long evenAnswer = 0;
        while (eIdx < nums.length) {
            if (nums[eIdx] % 2 == 0) eIdx++;
            else {
                if (oIdx == -1) oIdx = eIdx;
                while (eIdx < nums.length && nums[eIdx] % 2 == 1) {
                    eIdx++;
                }
                // 갖고올 짝을 찾았거나, 이미 나눠져서 홀수를 만나 끝났을 듯.
                if (eIdx < nums.length && nums[eIdx] % 2 == 0) { // 짝을 찾았을 경우
                    evenAnswer += eIdx - oIdx;
                    nums[eIdx] = 1;
                    nums[oIdx] = 0;
                    oIdx++;
                }
            }
        }

        oIdx = 0;
        eIdx = -1;

        // 왼쪽이 홀이라고 가정
        long oddAnswer = 0;
        while (oIdx < nums.length) {
            if (nums2[oIdx] % 2 == 1) oIdx++;
            else {
                if (eIdx == -1) eIdx = oIdx;
                while (oIdx < nums2.length && nums2[oIdx] % 2 == 0) {
                    oIdx++;
                }
                // 갖고올 짝을 찾았거나, 이미 나눠져서 홀수를 만나 끝났을 듯.
                if (oIdx < nums2.length && nums2[oIdx] % 2 == 1) { // 홀을 찾았을 경우
                    oddAnswer += oIdx - eIdx;
                    nums2[eIdx] = 1;
                    nums2[oIdx] = 0;
                    eIdx++;
                }
            }
        }

        if (oddAnswer > 0 && evenAnswer > 0) System.out.println(Math.min(oddAnswer, evenAnswer));
        else if (oddAnswer > 0) System.out.println(oddAnswer);
        else System.out.println(evenAnswer);
    }
}
