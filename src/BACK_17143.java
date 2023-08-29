import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_17143 {
	static SharkQueue[][] map;
	static SharkQueue[][] tmpmap;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int answer;
	static class Shark implements Comparable<Shark>{
		int vel;
		int dir;
		int size;
		Shark (int vel, int dir, int size) {
			this.vel = vel;
			this.dir = dir;
			this.size = size;
		}
		
		@Override
		public int compareTo(Shark s) {
			return Integer.compare(s.size, this.size); // 내림차순으로 정렬. 큰거 빼고 나머지 다 버림
		}
	}
	
	static class SharkQueue {
		Queue<Shark> pq;
		SharkQueue() {
			pq = new PriorityQueue<>();
		}
	}
	
//	public static boolean isIn() {
//		
//	}
	
	public static void move() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); // 상어의 수
		map = new SharkQueue[R][C];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) map[i][j] = new SharkQueue();
		}
		answer = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(br.readLine());
			int c = Integer.parseInt(br.readLine());
			int s = Integer.parseInt(br.readLine());
			int d = Integer.parseInt(br.readLine());
			int z = Integer.parseInt(br.readLine());
			map[r - 1][c - 1].pq.offer(new Shark(s, d - 1, z));
		}
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[j][i].pq.size() > 0) answer += map[j][i].pq.poll().size;
			}
			tmpmap = new SharkQueue[R][C];
			for (int j = 0; i < tmpmap.length; i++) {
				for (int k = 0; j < tmpmap[0].length; j++) map[j][k] = new SharkQueue();
			}
			move();
			for (int j = 0; i < tmpmap.length; i++) {
				for (int k = 0; j < tmpmap[0].length; j++) {
					Queue<Shark> elem = tmpmap[j][k].pq;
					if (elem.size() > 0) {
						Shark tmp = elem.poll();
						elem.clear();
						elem.offer(tmp);
					}
				}
			}
			map = tmpmap;
		}
		System.out.println(answer);
	}
}
