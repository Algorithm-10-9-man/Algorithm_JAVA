import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_17406 {
	static int N, M;
	static int[][] array;
	static int[][] commands;
	static int minA;
	static int[][] choosed;
	static boolean[] visited;
	
	public static int getValue(int[][] arr) {
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int sub_sum = 0;
			for (int j = 0; j < arr[0].length; j++) {
				sub_sum += arr[i][j];
			}
			if (sub_sum < answer) answer = sub_sum;
		}
		return answer;
	}
	
	public static void spin(int r, int c, int s, int[][] target) {
		int[] lu = new int[] {r - s, c - s};
		int[] rd = new int[] {r + s, c + s};
        int gr_cnt = Math.min(rd[0] - lu[0] + 1, rd[1] - lu[1] + 1) / 2;
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < gr_cnt; i++) {
        	ArrayList<Integer> arr = new ArrayList<>();
        	for (int j = lu[1] - 1 + i; j <= rd[1] - 2 - i; j++) arr.add(target[lu[0] - 1 + i][j]);
        	for (int j = lu[0] - 1 + i; j <= rd[0] - 2 - i; j++) arr.add(target[j][rd[1] - 1 - i]);
        	for (int j = rd[1] - 1 - i; j > lu[1] - 1 + i; j--) arr.add(target[rd[0] - 1 - i][j]);
        	for (int j = rd[0] - 1 - i; j > lu[0] - 1 + i; j--) arr.add(target[j][i + lu[1] - 1]);
        	groups.add(arr);
        }
        for (int i = 0; i < gr_cnt; i++) {
        	ArrayList<Integer> arr = groups.get(i);
        	int len = arr.size();
        	int index = len - 1;
	    	for (int j = lu[1] - 1 + i; j <= rd[1] - 2 - i; j++, index = (index + 1) % len) array[lu[0] - 1 + i][j] = arr.get(index);
	    	for (int j = lu[0] - 1 + i; j <= rd[0] - 2 - i; j++, index = (index + 1) % len) array[j][rd[1] - 1 - i] = arr.get(index);
	    	for (int j = rd[1] - 1 - i; j > lu[1] - 1 + i; j--, index = (index + 1) % len) array[rd[0] - 1 - i][j] = arr.get(index);
	    	for (int j = rd[0] - 1 - i; j > lu[0] - 1 + i; j--, index = (index + 1) % len) array[j][i + lu[1] - 1] = arr.get(index);
        }
	}
	
	public static void perm(int depth) {
		if (depth == visited.length) {
			System.out.println();
			int[][] original = new int[array.length][array[0].length];
			for (int i = 0; i < array.length; i++) original[i] = array[i].clone();
			for (int i = 0; i < choosed.length; i++) {
				int[] elem = choosed[i];
				spin(elem[0], elem[1], elem[2], original);
				for (int[] tar : original) System.out.println(Arrays.toString(tar));
				System.out.println();
			}
			int sub_answer = getValue(original);
			if (minA > sub_answer) minA = sub_answer;
		} else {
			for (int i = 0; i < commands.length; i++) {
				if (!visited[i]) {
					visited[i] = true;
					choosed[depth] = commands[i];
					perm(depth + 1);
					visited[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        array = new int[N][M];
        minA = Integer.MAX_VALUE;
        commands = new int[K][3];
        choosed = new int[K][3];
        visited = new boolean[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) array[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < K; i++) {
        	commands[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        perm(0);
        System.out.println(minA);
	}
}
