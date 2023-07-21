import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
수들을 먼저 int 배열로 만든 다음에
각 재귀마다 4번씩 돌리면서 하나씩 빼는걸로.
끝까지 갔을 때 min과 max와 함께 초기화.
 */
public class BACK_14888 {
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;
    static int[] nums;
    static int[] op_cnts;
    static String[] ops = new String[]{"+", "-", "*", "/"};
    static ArrayList<String> operators;
    static boolean[] visited;
    public static long calc(long sum, int oper, String operator) {
        if (operator.equals("+")) return sum + oper;
        else if (operator.equals("-")) return sum - oper;
        else if (operator.equals("*")) return sum * oper;
        else return sum / oper;
    }
    public static void find_sol(int depth, long sum) {
        if (depth == nums.length) {
            if (sum > max) max = sum;
            if (min > sum) min = sum;
        } else {
            for (int j = 0; j < operators.size(); j++) {
                if (!visited[j]) {
                    long sub_sum = calc(sum, nums[depth], operators.get(j));
                    visited[j] = true;
                    find_sol( depth + 1, sub_sum);
                    visited[j] = false;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException { // + - * /
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        op_cnts = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operators = new ArrayList<>();
        for (int i = 0; i < op_cnts.length; i++) {
            while (op_cnts[i] > 0) {
                op_cnts[i]--;
                switch (i) {
                    case 0:
                        operators.add("+");
                        break;
                    case 1:
                        operators.add("-");
                        break;
                    case 2:
                        operators.add("*");
                        break;
                    case 3:
                        operators.add("/");
                        break;
                }
            }
        }
        visited = new boolean[operators.size()];
        find_sol(  1, nums[0]);
        System.out.println(max);
        System.out.println(min);
    }
}
