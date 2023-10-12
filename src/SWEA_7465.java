package src;

import java.io.*;
import java.util.Arrays;

/*
   ����ϴ� Ŭ�������� Solution �̾�� �ϹǷ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� �����غ� �� �ֽ��ϴ�.
 */
class SWEA_7465
{
    static boolean[] visited;
    static int[][] relation;
    static int answer;
    public static void find_group(int target) {
        visited[target] = true;
        for (int i = 0; i < relation[0].length; i++) {
            if (relation[target][i] == 1 && !visited[i]) {
                find_group(i);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int[] elem = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = elem[0];
            int M = elem[1];
            relation = new int[N][N];
            visited = new boolean[N];
            answer = 0;
            for (int i = 0; i < M; i++) {
                elem = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                relation[elem[0] - 1][elem[1] - 1] = 1;
                relation[elem[1] - 1][elem[0] - 1] = 1;
            }
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    find_group(i);
                    answer++;
                }
            }
            bw.write("#" + tc + " " + answer + "\n");
        }
        bw.flush();
    }
}