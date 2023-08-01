import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class BACK_17140 {

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] arr = new int[100][100];
		int answer = -1;
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int row = 3;
		int col = 3;
		Map<Integer, Integer> map = new LinkedHashMap<>();
		for (int sec = 0; sec <= 100; sec++) {
			if (arr[r - 1][c - 1] == k) {
				answer = sec;
				break;
			}
			int new_length = 0;
			int sub_length = 0;
			Set<Map<Integer, Integer>> entrySet = new LinkedHashSet<>();
			if (row >= col) { //R
				for (int i = 0; i < row; i++) {
					map.clear();
					for (int j = 0; j < col; j++) {
						if (arr[i][j] != 0) {
							if (!map.containsKey(arr[i][j])) map.put(arr[i][j], 1);
							else map.put(arr[i][j], map.get(arr[i][j]) + 1);							
						}
					}
//					entrySet.add(new LinkedHashSet<>(map));
					sub_length = map.size() * 2;
					if (sub_length > new_length) new_length = sub_length;
				}
			}
			else { //C
				for (int i = 0; i < col; i++) {
					map.clear();
					for (int j = 0; j < row; j++) {
						if (arr[j][i] != 0) {
							if (!map.containsKey(arr[j][i])) map.put(arr[j][i], 1);
							else map.put(arr[j][i], map.get(arr[j][i]) + 1);							
						}
					}
					entrySet.add(map);
					sub_length = map.size() * 2;
					if (sub_length > new_length) new_length = sub_length;
				}
			}
			for (int i = 0; i < new_length; i++) {
				for (int j = 0; j < new_length; j++) arr[i][j] = 0;
			}
			if (row >= col) {
				int i, j;
				i = j = 0;
				for (Map<Integer, Integer> line : entrySet) {
					System.out.println(line);
					for (Entry<Integer, Integer> elem : line.entrySet()) {
						if (j < arr[0].length) arr[i][j++] = elem.getKey();
						if (j < arr[0].length) arr[i][j++] = elem.getValue();
					}
					i++;
				}
			} else {
				int i, j;
				i = j = 0;
				for (Map<Integer, Integer> line : entrySet) {
					for (Entry<Integer, Integer> elem : line.entrySet()) {
						if (j < arr.length) arr[j++][i] = elem.getKey();
						if (j < arr.length) arr[j++][i] = elem.getValue();
					}
					i++;
				}
			}
			if (row >= col) col = new_length;
			else row = new_length;
//			for (int i = 0; i < row; i++) {
//				for (int j = 0; j < col; j++) System.out.print(arr[i][j] + " ");
//				System.out.println();
//			}
			System.out.println();
		}
		System.out.println(answer);
	}
}
