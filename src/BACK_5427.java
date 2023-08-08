import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_5427 {
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int[] start = new int[2];
			String[][] map = new String[w][h];
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().split("");
				for (int j = 0; j < w; j++) {
					if (map[i][j].equals("@"))
				}
			}
			// 불 옮겨붙기
			
		}
	}
}
