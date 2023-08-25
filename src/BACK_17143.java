import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_17143 {
	static SharkQueue[][] map;
	static SharkQueue[][] tmpmap;
	static int[] dx = {-1, 1, 0, 0}; // 동 서 남 북
	static int[] dy = {0, 0, 1, -1};
	static int answer;
	static class Shark implements Comparable<Shark>{
		@Override
		public String toString() {
			return "Shark [vel=" + vel + ", dir=" + dir + ", size=" + size + "]";
		}

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
	
	public static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return true;
	}
	
	public static void move() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].pq.size() > 0) {
					Shark tmp = map[i][j].pq.poll();
					int x = i;
					int y = j;
					int cnt = tmp.vel;
					for (int k = 0; k < cnt; k++) {
						int nx = x + dx[tmp.dir];
						int ny = y + dy[tmp.dir];
						if (isIn(nx, ny)) {
							x = nx;
							y = ny;
						} else {
							if (tmp.dir == 1) tmp.dir = 0;
							else if (tmp.dir == 0) tmp.dir = 1;
							else if (tmp.dir == 2) tmp.dir = 3;
							else tmp.dir = 2;
							x = nx + (dx[tmp.dir] * 2);
							y = ny + (dy[tmp.dir] * 2);
						}
					}
					tmpmap[x][y].pq.offer(tmp);
				}
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
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1].pq.offer(new Shark(s, d - 1, z));
		}
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) { // 해당 열에서 상어 한 마리 잡기
				if (map[j][i].pq.size() > 0) {
					Shark target = map[j][i].pq.poll();
					answer += target.size;
					break;
				}
			}
			tmpmap = new SharkQueue[R][C]; // move를 위한 새로운 tmpmap 생성
			for (int j = 0; j < tmpmap.length; j++) {
				for (int k = 0; k < tmpmap[0].length; k++) tmpmap[j][k] = new SharkQueue();
			}
			move(); // 상어 이동
			for (int j = 0; j < tmpmap.length; j++) { // 이동 후 2마리 있는 지역은 제일 큰 상어가 잡아먹기
				for (int k = 0; k < tmpmap[0].length; k++) {
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