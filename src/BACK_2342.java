package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BACK_2342 {
    static ArrayList<Integer> coms;
    static int left;
    static int right;
    public static int getMPower(int start, int target) {
        if (start == 0) return 2; // 시작 지점에 발이 있는 경우
        if (start == target) {
            return 1;
        }
        if (start == 1) {
            if (target == 2 || target == 4) return 3;
            else return 4;
        } else if (start == 2) {
            if (target == 1 || target == 3) return 3;
            else return 4;
        } else if (start == 3) {
            if (target == 2 || target == 4) return 3;
            else return 4;
        } else if (start == 4) {
            if (target == 1 || target == 3) return 3;
            else return 4;
        } else return -1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        coms = new ArrayList<>();
        while (true) {
            int elem = Integer.parseInt(st.nextToken());
            if (elem == 0) break;
            coms.add(elem);
        }
        int answer = 0;
        left = right = 0;
        answer = Integer.MAX_VALUE;
        int[][] dp = new int[coms.size()][2]; // 첫 번째 인덱스 : i번째 판넬, 두 번째 인덱스 : 왼발, 오른발 idx
        left = coms.get(0);
        System.out.println(answer);
    }
}