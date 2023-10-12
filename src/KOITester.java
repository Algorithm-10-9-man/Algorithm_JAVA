package src;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class KOITester {
    public static String solution(String input) {
        return "";
    }
    public static void main(String[] args) throws IOException {
        String dir = "skate";
        for (int i = 1; i <= 9; i++) {
            String myAns = solution(dir + "/0" + i + ".in.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("skate/0" + i + ".out.txt")));
            String ans = br.readLine();
            if (!ans.equals(myAns)) {
                System.out.println("myAns : " + myAns + " realAns : " + ans);
            }
        }
        for (int i = 10; i <= 108; i++) {
            String myAns = solution("skate/" + i + ".in.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("skate/" + i + ".out.txt")));
            String ans = br.readLine();
            if (!ans.equals(myAns)) {
                System.out.println("num : " + i + " myAns : " + myAns + " realAns : " + ans);
            }
        }
    }
}
