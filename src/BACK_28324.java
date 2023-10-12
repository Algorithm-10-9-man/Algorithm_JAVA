package src;

import java.io.*;
import java.util.StringTokenizer;

public class BACK_28324 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] V = new int[N];
		int[] ans = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) V[i] = Integer.parseInt(st.nextToken());
		ans[N - 1] = 1;
		for (int i = V.length - 2; i > 0; i--) {
			if (ans[i + 1] + 1 <= V[i]) {
				ans[i] = ans[i + 1] + 1;
			} else if (V[i] >= ans[i + 1] && ans[i + 1] <= V[i - 1] + 1) ans[i] = ans[i + 1];
			else ans[i] = V[i];
		}
		if (N > 1) {
			if (ans[1] + 1 <= V[0]) ans[0] = ans[1] + 1;
			else if (V[0] >= ans[1]) ans[0] = ans[1];
			else ans[0] = ans[1] - 1;
		}
		long answer = 0;
		for (int elem : ans) answer += elem;
		System.out.println(answer);
	}
}
