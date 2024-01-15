package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BACK_25381 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] line = br.readLine().toCharArray();
        /*
        ���� ���� �͵�
        A�տ� �ִ� B
        B�տ� �ִ� C

        ��� ���� ���� ������, ���� ���� ��������
        1. A�� ¦���� B��, �ִ��� �ڿ� C�� ���� B�� �����Ѵ�.
        2. C�� ¦���� B��, �տ� A�� ���� B�� �����Ѵ�. �ִ���.
        AABB
        ABAB
        BBCC
        BCBC

        B AABB C ans : 3


        A BC B/ B A C B/ AB B

        ABABABB
        */

        int answer = 0;
        Queue<Integer> bIdx = new ArrayDeque<>();
        boolean[] visited = new boolean[line.length];
        for (int i = 0; i < line.length; i++) {
            char elem = line[i];
            if (elem == 'B') bIdx.offer(i);
            else if (elem == 'C'){ // C
                if (!bIdx.isEmpty()) {
                    answer++;
                    visited[bIdx.poll()] = true;
                }
            }
        }
        // O(n)
        int Acnt = 0;
        for (int i = 0; i < line.length; i++) {
            char elem = line[i];
            if (elem == 'B' && !visited[i]) {
                if (Acnt > 0) {
                    Acnt--;
                    answer++;
                }
            } else if (elem == 'A') Acnt++;
        }
        System.out.println(answer);
    }
}