import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BACK_2239 {
	static boolean chk;
	static char[][] map;
	public static boolean chkCord(int x, int y) {
		Set<Character> chk = new HashSet<>();
		for (int i = 0; i < map.length; i++) {
			if (map[x][i] != '0') {
				if (!chk.contains(map[x][i])) chk.add(map[x][i]);
				else return false;				
			}
		}
		chk.clear();
		for (int i = 0; i < map.length; i++) {
			if (map[i][y] != '0') {
				if (!chk.contains(map[i][y])) chk.add(map[i][y]);
				else return false;				
			}
		}
		chk.clear();
		int xIdx = (x / 3) * 3;
		int yIdx = (y / 3) * 3;
		for (int i = xIdx; i < xIdx + 3; i++) {
			for (int j = yIdx; j < yIdx + 3; j++) {
				if (map[i][j] != '0') {
					if (!chk.contains(map[i][j])) chk.add(map[i][j]);
					else return false;						
				}
			}
		}
		return true;
	}
	
	public static void find_sol(int x, int y, int depth) {
		if (depth == 81) {
			if (!chk) {
				chk = true;
				for (char[] elem : map) System.out.println(Arrays.toString(elem));
			}
			return;
		}
		if (map[x][y] != '0') {
			if (y + 1 < map[0].length) find_sol(x, y + 1, depth + 1);
			else find_sol(x + 1, 0, depth + 1);
		} else {			
			for (int i = 1; i <= 9; i++) {
				map[x][y] = (char)(i + '0');
				if (y + 1 < map[0].length && chkCord(x, y)) find_sol(x, y + 1, depth + 1);
				else if (chkCord(x, y)) find_sol(x + 1, 0, depth + 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		chk = false;
		map = new char[9][9];
		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) map[i][j] = line.charAt(j);
		}
		find_sol(0, 0, 0);
	}
}
