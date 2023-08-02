import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_16234 {
	static int N, L, R;
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};
	static int[][] A;
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input2"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N + N - 1][N + N - 1];
		for (int i = 0; i < A.length; i+=2) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < A[0].length; j+= 2) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solution());
	}

	static int solution() {
		for (int t = 0; t <= 2000; t++) {
			// 국경열기 -> boolean true false ret
			if (!openline()) return t;
			// 국가 인구수 정하기
			// 국경선 다 닫기.
		}
	}
	
	static boolean openline() {
		boolean is_executed;
		for (int i = 0; i < A.length; i+=2) {
			for (int j = 0; j < A[0].length; j+=2) {
				for (int k = 0; k < dx.length; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx >= 0 && ny >= 0 && nx)
				}
			}
		}
	}
	
	
}
