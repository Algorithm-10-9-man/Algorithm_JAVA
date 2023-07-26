import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] dp;
    static int[][] things;
    static boolean[] visited;
    static int K;
    public static void find_sol(int weight, int sub_sum) {
        for (int i = 0; i < things.length; i++) {
            if (!visited[i] && weight + things[i][0] <= K) {
                visited[i] = true;
                dp[weight + things[i][0]] = Math.max(dp[weight + things[i][0]], sub_sum + things[i][1]);
                find_sol(weight + things[i][0], sub_sum + things[i][1]);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0];
        K = inputs[1];
        things = new int[N][2];
        dp = new int[K + 1];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            things[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        find_sol(0, 0);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= K; i++) if (max < dp[i]) max = dp[i];
        System.out.println(max);
    }
}