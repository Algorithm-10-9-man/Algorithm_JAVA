package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BACK_2342 {
	static ArrayList<Integer> coms;
	static int answer;
	static int [][][] dp; // 0,0 ~ 4,4
	static int count;
	public static int getMPower(int start, int target) {
		if (start == target) return 1;
		if (start == 0) return 2; // 시작 지점에 발이 있는 경우
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
	
	public static int recur(int depth, int left, int right) {
		if (depth == coms.size()) return 0;
		int target = coms.get(depth);
		if (dp[depth][left][right] != 0) return dp[depth][left][right];
		int leftPower = recur(depth + 1, target, right) + getMPower(left, target);
		int rightPower = recur(depth + 1, left, target) + getMPower(right, target);
		dp[depth][left][right] = Math.min(leftPower, rightPower);
		return dp[depth][left][right];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		coms = new ArrayList<>();
		count = 0;
		while (true) {
			int elem = Integer.parseInt(st.nextToken());
			if (elem == 0) break;
			coms.add(elem);
		}
		dp = new int[coms.size()][5][5];
		System.out.println(recur(0, 0, 0));
	}
}
