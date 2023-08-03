import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BACK_2138 {
	static boolean[] switchesZero; //0번 스위치 누름
	static boolean[] switchesNZero; //0번 스위치 안 누름
	static boolean[] target;
	public static void turnSwitch(int x, boolean[] switches) {
		if (x == 0) {
			switches[x] = !switches[x];
			if (x + 1 < switches.length) switches[x + 1] = !switches[x + 1];
		} else if (x == switches.length - 1) {
			switches[x] = !switches[x];
			if (x - 1 >= 0) switches[x - 1] = !switches[x - 1];
		} else {
			switches[x] = !switches[x];
			switches[x + 1] = !switches[x + 1];
			switches[x - 1] = !switches[x - 1];
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		switchesNZero = new boolean[N];
		target = new boolean[N];
		int answerZero = Integer.MAX_VALUE;
		int answerNZero = Integer.MAX_VALUE;
		String line = br.readLine();
		for (int i = 0; i < N; i++) {
			switchesNZero[i] = line.charAt(i) == '0' ? false : true;
		}
		switchesZero = switchesNZero.clone();
		line = br.readLine();
		for (int i = 0; i < N; i++) {
			target[i] = line.charAt(i) == '0' ? false : true; 
		}
		turnSwitch(0, switchesZero);
		System.out.println(Arrays.toString(switchesZero));
		for (int i = 1; i < N; i++) {
			if (Arrays.equals(switchesNZero, target)) {
				if (answerNZero > i + 1) answerNZero = i + 1;
			}
			if (Arrays.equals(switchesZero, target)) {
				if (answerZero > i + 1) answerZero = i + 2;
			}
			if (target[i] != switchesNZero[i]) {
				turnSwitch(i, switchesNZero);
			}
			if (target[i] != switchesZero[i]) {
				turnSwitch(i, switchesZero);
			}
			System.out.println(Arrays.toString(switchesNZero));
			System.out.println(Arrays.toString(switchesZero));
			System.out.println();
		}
		int answer = Math.min(answerNZero, answerZero);
		if (answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}
