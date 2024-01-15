package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_28217 {
    static class Edge {
        int x;
        int y;
        Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer;
    public static void getTriangle(boolean[][] T) throws IOException{
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < T[i].length; j++) {
                String elem = st.nextToken();
                T[i][j] = elem.equals("1");
            }
        }
    }

    public static void printArr(boolean[][] T) {
        for (boolean[] elem : T) System.out.println(Arrays.toString(elem));
        System.out.println();
    }

    public static void checkSum(boolean[][] candi, boolean[][] target) {
        int sum = 0;
        for (int i = 0; i < candi.length; i++) {
            for (int j = 0; j < candi[i].length; j++) if (candi[i][j] != target[i][j]) sum++;
        }
        if (sum < answer) answer = sum;
    }
    public static boolean[][] turnClockWise(boolean[][] T) {
        boolean[][] ret = new boolean[T.length][];
        // initializing
        for (int i = 0; i < ret.length; i++) ret[i] = new boolean[T[i].length];
        int repeat = 0;
        if (N >= 4) {
            repeat = N / 3;
            if (N % 3 == 0) repeat -= 1;
        }
        Edge A, B, C; // vertices
        A = new Edge(0, 0);
        B = new Edge(T.length - 1, 0);
        C = new Edge(T.length - 1, T[T.length - 1].length - 1);
        for (int nth = 0; nth <= repeat; nth++) {
            // left -> right
            for (int i = 0; i < C.x - A.x + 1; i++) {
                int retX = A.x + i;
                int retY = A.y + i;
                int Tx = B.x - i;
                int Ty = B.y;
                ret[retX][retY] = T[Tx][Ty];
            }
            // right -> ground
            for (int i = 0; i < C.y - B.y + 1; i++) {
                int retX = B.x;
                int retY = B.y + i;
                int Tx = C.x - i;
                int Ty = C.y - i;
                ret[retX][retY] = T[Tx][Ty];
            }
            // ground -> left
            for (int i = 0; i < B.x - A.x + 1; i++) {
                int retX = A.x + i;
                int retY = A.y;
                int Tx = B.x;
                int Ty = B.y + i;
                ret[retX][retY] = T[Tx][Ty];
            }
            // go to next Coordinate
            A.x += 2; A.y += 1;
            B.x -= 1; B.y += 1;
            C.x -= 1; C.y -= 2;
        }
        return ret;
    }

    public static boolean[][] swap(boolean[][] T) {
        boolean[][] ret = new boolean[T.length][];
        for (int i = 0; i < T.length; i++) ret[i] = new boolean[T[i].length];
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[i].length; j++) {
                ret[i][j] = T[i][T[i].length - 1 - j];
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        answer = Integer.MAX_VALUE;
        boolean[][] A = new boolean[N][];
        boolean[][] B = new boolean[N][];
        for (int i = 0; i < A.length; i++) {
            A[i] = new boolean[i + 1];
            B[i] = new boolean[i + 1];
        }
        getTriangle(A);
        getTriangle(B);
        checkSum(A, B);
        checkSum(swap(A), B);
        for (int i = 0; i < 2; i++) {
            boolean[][] ret = turnClockWise(A);
            checkSum(ret, B);
            checkSum(swap(ret), B);
            A = ret;
        }
        System.out.println(answer);
    }
}
