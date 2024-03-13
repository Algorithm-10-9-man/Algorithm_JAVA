package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BACK_1722 {
    public static long factorial(long N) {
        if (N <= 1) return 1;
        return N * factorial(N - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int com = Integer.parseInt(st.nextToken());
        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        long k = 0;
        for (int i = 0; i < N; i++) numbers.add(i + 1);
        if (com == 1) k = Long.parseLong(st.nextToken());
        else {
            for (int i = 0; i < N; i++) {
                nums.add(Integer.parseInt(st.nextToken()));
            }
        }
        if (com == 1) {
            int[] answer = new int[N];
            int ansIdx = 0;
            for (int i = 1; i < N; i++) {
                long fact = factorial(N - i);
                int div = 0;
                if (k % fact != 0) {
                    div = (int)(k / fact);
                } else div = (int)(k / fact) - 1;
                answer[ansIdx] = numbers.get(div);
                numbers.remove(div);
                k -= div * fact;
                ansIdx++;
            }
            answer[N - 1] = numbers.get(0);
            for (int i = 0; i < N; i++) System.out.print(answer[i] + " ");
        } else {
            long answer = 0;
            int numIdx = 0;
            for (int i = 1; i < N; i++) {
                int idx = numbers.indexOf(nums.get(numIdx));
                answer += factorial(N - i) * idx;
                numbers.remove(idx);
                numIdx++;
            }
            System.out.println(answer + 1);
        }
    }
}
