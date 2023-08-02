package src;

import java.util.Random;

public class InputMaker {
	public static void main(String[] args) {
		int N = 70;
		int K = 100000;
		Random rand = new Random();
		System.out.println(N + " " + K);
		for (int i = 0; i < N; i++) {
			System.out.println(rand.nextInt(100000) + 1 + " " + rand.nextInt(1001));
		}
	}
}
