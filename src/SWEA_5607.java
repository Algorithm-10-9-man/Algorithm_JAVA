package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5607 {
    static long NUM = 1_000_000_007;
    public static long fastPow(long a, long b) {
        if (b == 0) return 1;
        long ret = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                ret = (ret * a) % NUM;
            }
            a = (a* a) % NUM;
            b >>= 1;
        }
        return ret;
    }
    public static long factorial(long N) {
        long ret = 1;
        while (N > 0) {
            ret = (ret * N) % NUM;
            N--;
        }
        return ret;
    }

    public static long solve(long N, long R) {
        return (factorial(N) * fastPow((factorial(N - R) * factorial(R)) % NUM, NUM - 2)) % NUM;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            long answer = solve(N, R);
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}